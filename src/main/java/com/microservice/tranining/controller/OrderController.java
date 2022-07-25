package com.microservice.tranining.controller;

import com.microservice.tranining.common.Const;
import com.microservice.tranining.dto.Order;
import com.microservice.tranining.dto.Product;
import com.microservice.tranining.dto.ShoppingCart;
import com.microservice.tranining.service.CartService;
import com.microservice.tranining.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<Order> getAllOrders() {
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


}
