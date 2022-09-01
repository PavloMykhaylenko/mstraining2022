package com.microservice.tranining.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "order_inst")
public class Order {

    @Id
    private long id;
    private String status;

    @OneToMany
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
