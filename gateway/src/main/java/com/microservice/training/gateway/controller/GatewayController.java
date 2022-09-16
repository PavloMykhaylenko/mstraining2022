package com.microservice.training.gateway.controller;

import com.microservice.training.gateway.dto.Order;
import com.microservice.training.gateway.util.RequestEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    @Value("${gateway.orderms.url}")
    private String ORDER_MS_URL;

    @Value("${gateway.monolith.url}")
    private String MONOLITH_MS_URL;

    @Autowired
    private RestTemplate restTemplate;

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("/orders")
    public Iterable<Order> getAllOrdersOrderMs() {
        return getOrders(ORDER_MS_URL);
    }

    @GetMapping("/orders/{id}")
    public Order getOrderOrderMs(long id) {
        return getSingleOrder(ORDER_MS_URL + "/" + id);
    }

    @PostMapping("/orders")
    public void saveOrderOrderMs(Order order) {
        post(ORDER_MS_URL, order);
    }

    @PutMapping("/orders/{id}")
    public void updateOrderOrderMs(@PathVariable long id, @RequestBody Order order) {
        update(ORDER_MS_URL, id, order);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrderOrderMs(long id) {
        delete(ORDER_MS_URL + "/" + id);
    }

    //------------------------------------------------------------------------------------------------------------------

    @GetMapping("/crud/orders/")
    public Iterable<Order> getAllOrders() {
        return getOrders(MONOLITH_MS_URL);
    }

    @GetMapping("/crud/orders/{orderId}")
    public Order getOrder(@PathVariable long orderId) {
        return getSingleOrder(MONOLITH_MS_URL + "/" + orderId);
    }


    @PutMapping("/crud/orders/{orderId}")
    public void updateOrder(@PathVariable long orderId, @RequestBody Order order) {
        update(MONOLITH_MS_URL, orderId, order);
    }

    @PostMapping("/crud/orders/")
    public void createOrder(@RequestBody Order order) {
        post(MONOLITH_MS_URL, order);
    }


    @DeleteMapping("/crud/orders/{id}")
    public void deleteOrder(@PathVariable long orderId) {
        delete(MONOLITH_MS_URL + "/" + orderId);
    }

    //------------------------------------------------------------------------------------------------------------------


    @GetMapping("/business")
    public Iterable<Order> getAllOrdersBusiness() {
        return getOrders(ORDER_MS_URL);
    }

    //------------------------------------------------------------------------------------------------------------------

    private Order getSingleOrder(String url) {
        return restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Order>() {
                })
                .getBody();
    }

    private Iterable<Order> getOrders(String order_ms_url) {
        return restTemplate.exchange(order_ms_url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Order>>() {
                })
                .getBody();
    }

    private void update(String baseUrl, long orderId, Order order) {
        restTemplate.exchange(baseUrl + "/" + orderId,
                HttpMethod.PUT,
                RequestEntityUtil.getRequestEntity(order),
                new ParameterizedTypeReference<Order>() {
                });
    }

    private void post(String url, Order order) {
        restTemplate.exchange(url,
                HttpMethod.POST,
                RequestEntityUtil.getRequestEntity(order),
                new ParameterizedTypeReference<Order>() {
                });
    }

    private void delete(String url) {
        restTemplate.exchange(url,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Order>() {});
    }
}
