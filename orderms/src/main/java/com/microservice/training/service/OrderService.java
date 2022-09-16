package com.microservice.training.service;

import com.microservice.training.common.Const;
import com.microservice.training.dto.Order;
import com.microservice.training.util.RequestEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderService {

//    private static final String SUPPLIERS_URL = "http://localhost:8081/crud/orders/";
//    private static final String SUPPLIERS_URL = "http://monolith-service:8081/crud/orders/";.
//private static final String SUPPLIERS_URL = "http://gateway-service:8083/gateway/crud/orders/";
//    private static final String SUPPLIERS_URL = "http://localhost:8083/gateway/crud/orders/";

    @Value("${gateway.orders.url}")
    private String GATEWAY_ORDERS_URL;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping
    public Iterable<Order> getAllOrders() {
        return restTemplate.exchange(GATEWAY_ORDERS_URL,
                                        HttpMethod.GET,
                             null,
                                        new ParameterizedTypeReference<Iterable<Order>>() {})
                             .getBody();
    }

    @GetMapping("/{id}")
    public Order getOrder(long id) {
        return restTemplate.exchange(GATEWAY_ORDERS_URL + "/" + id,
                                    HttpMethod.GET,
                         null,
                                    new ParameterizedTypeReference<Order>() {})
                            .getBody();
    }

    @PostMapping
    public void saveOrder(Order order) {
        restTemplate.exchange(GATEWAY_ORDERS_URL,
                                HttpMethod.POST,
                                RequestEntityUtil.getRequestEntity(order),
                                new ParameterizedTypeReference<Order>() {});
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable long id, @RequestBody Order order) {
        restTemplate.exchange(GATEWAY_ORDERS_URL + "/" + id,
                            HttpMethod.PUT,
                            RequestEntityUtil.getRequestEntity(order),
                            new ParameterizedTypeReference<Order>() {});
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(long id) {
        restTemplate.exchange(GATEWAY_ORDERS_URL + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Order>() {});
    }

    @PostMapping("/close/{id}")
    public void closeOrder(long id) {
        Order order = getOrder(id);
        if (order != null) {
            order.setStatus(Const.CLOSED);
        }
        saveOrder(order);
    }
}
