package com.microservice.tranining.controller;

import com.microservice.tranining.dto.Order;
import com.microservice.tranining.service.OrderServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crud/orders/")
public class OrderControllerCrud {

    @Autowired
    private OrderServiceCrud orderService;

    @GetMapping()
    public Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable long orderId) {
        return orderService.getOrder(orderId);
    }


    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable long orderId, @RequestBody Order order) {
        orderService.updateOrder(order);
    }


    @PostMapping()
    public void createOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable long orderId) {
        orderService.deleteOrder(orderId);
    }


}
