package com.microservice.tranining.repository;

import com.microservice.tranining.dto.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<ShoppingCart, Long> {
}
