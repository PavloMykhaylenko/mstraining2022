package com.microservice.tranining.service;

import com.microservice.tranining.common.Const;
import com.microservice.tranining.dto.Order;
import com.microservice.tranining.dto.Product;
import com.microservice.tranining.dto.ShoppingCart;
import com.microservice.tranining.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public Order getOrder(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void closeOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(Const.CLOSED);
        }
        orderRepository.save(order);
    }
}
