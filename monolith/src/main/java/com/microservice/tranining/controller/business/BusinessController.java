package com.microservice.tranining.controller.business;

import com.microservice.tranining.dto.Order;
import com.microservice.tranining.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController()
@RequestMapping("/business")
public class BusinessController {

    //    private static final String ORDER_SERVICE_URL = "http://localhost:8082/orders/";
//        private static final String ORDER_SERVICE_URL = "http://orderms-service:8082/orders/";
//        private static final String ORDER_SERVICE_URL = "http://gateway-service:8083/gateway/orders/";
//        private static final String ORDER_SERVICE_URL = "http://localhost:8083/gateway/orders/";

        @Value("${gateway.service.url}")
        private String ORDER_SERVICE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping
    public Iterable<Order> getAllOrders() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        return circuitBreaker.run(() -> restTemplate.exchange(ORDER_SERVICE_URL,
                                        HttpMethod.GET,
                                        null,
                                        new ParameterizedTypeReference<Iterable<Order>>() {
                                        })
                                        .getBody(),
                                  throwable -> getAllOrdersBkp());
    }


    public Iterable<Order> getAllOrdersBkp() {
        List<Order> allOrders = orderService.getAllOrders();
        getBkpOrder(allOrders);
        return allOrders;
    }

    private void getBkpOrder(List<Order> allOrders) {
        Order bkpOrder = new Order();
        bkpOrder.setId(999L);
        bkpOrder.setStatus("BKP_ORDER_STATUS");
        allOrders.add(bkpOrder);
    }
}
