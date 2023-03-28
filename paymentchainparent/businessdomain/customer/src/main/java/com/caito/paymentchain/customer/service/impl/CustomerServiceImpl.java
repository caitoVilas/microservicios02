package com.caito.paymentchain.customer.service.impl;

import com.caito.paymentchain.customer.entity.Customer;
import com.caito.paymentchain.customer.entity.CustomerProduct;
import com.caito.paymentchain.customer.repository.CustomerRepository;
import com.caito.paymentchain.customer.service.contract.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author caito Vilas
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private final WebClient.Builder webClientBuilder;

    public CustomerServiceImpl(WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
    }

    HttpClient client = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(EpollChannelOption.TCP_KEEPIDLE, 300)
            .option(EpollChannelOption.TCP_KEEPINTVL, 60)
            .responseTimeout(Duration.ofSeconds(1))
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });


    @Override
    public Customer save(Customer customer) {
        log.info("iniciando servicio guardar cliente");
        log.info("guardando cliente...");
        customer.getProducts().forEach(x -> x.setCustomer(customer));
        return customerRepository.save(customer);
    }

    @Override
    public Customer getOne(Long id) {
        log.info("iniciando servicio buscar cliente por id");
        log.info("buscando cliente...");
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        log.info("iniciando servicio buscar todos los clientes");
        log.info("buscando clientes...");
        return customerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        log.info("iniciando servicio eliminar cliente");
        log.info("eliminando cliente");
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null)
            customerRepository.deleteById(id);
    }

    @Override
    public Customer getFull(String code) {
        log.info("iniciando buscar cliente por codigo");
        log.info("buscando cliente...");
        Customer customer = customerRepository.findBYCode(code);
        if (customer != null && !customer.getProducts().isEmpty()){
            List<CustomerProduct> products = customer.getProducts();
            products.forEach(x -> {
                String producName = this.getProductName(x.getId());
                x.setProductName(producName);
            List<?> transactions = this.getTransaction(customer.getIban());
            customer.setTransactions(transactions);
            });
        }
        return customer;
    }

    private String getProductName(Long id){
        log.info("llamando servicio externo buscar nombre de producto");
        log.info("url : " + "http://PRODUCT-SERVICE/api/v1/paymentchain/products/" + id);
        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl("http://PRODUCT-SERVICE/api/v1/paymentchain/products")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url",
                        "http://PRODUCT-SERVICE/api/v1/paymentchain/products"))
                .build();
        JsonNode block = build.method(HttpMethod.GET).uri("/" + id)
                .retrieve().bodyToMono(JsonNode.class).block();
        String name = block.get("name").asText();
        return name;
    }

    private List<?> getTransaction(String iban){
        log.info("llamado a seervicio externo buscar transacciones por iban");
        log.info("url : " + "http://TRANSACTION-SERVICE/api/v1/paymentchain/transactions/" + iban);
        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl("http://TRANSACTION-SERVICE/api/v1/paymentchain/transactions")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        List<?> transactions = build.method(HttpMethod.GET).uri(uriBuilder ->  uriBuilder
            .path("/customer/transactions")
                .queryParam("ibanAccount", iban)
                .build())
                .retrieve().bodyToFlux(Object.class).collectList().block();
        return transactions;
    }
}
