package com.microservice.tranining.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    private long id;
    private String customerTn;
    private String status;

    @OneToMany
    private List<Product> products;


    public ShoppingCart() {
    }

    public ShoppingCart(long id, String customerTn, List<Product> products, String status) {
        this.id = id;
        this.customerTn = customerTn;
        this.products = products;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerTn() {
        return customerTn;
    }

    public void setCustomerTn(String customerTn) {
        this.customerTn = customerTn;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProduct(long productId) {
        return products.stream().filter(p -> p.getId() == productId).findFirst().orElse(null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", customerTn='" + customerTn + '\'' +
                ", products=" + products +
                '}';
    }
}