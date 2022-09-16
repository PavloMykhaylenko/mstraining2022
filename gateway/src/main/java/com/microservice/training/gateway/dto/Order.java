package com.microservice.training.gateway.dto;

import java.util.List;

public class Order {

    private long id;
    private String status;
    private List<ShoppingCart> carts;

    public Order() {
    }

    public Order(long id, String status, List<ShoppingCart> carts) {
        this.id = id;
        this.status = status;
        this.carts = carts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ShoppingCart> getCarts() {
        return carts;
    }

    public void setCarts(List<ShoppingCart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", carts=" + carts +
                '}';
    }
}
