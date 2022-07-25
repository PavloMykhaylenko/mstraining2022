package com.microservice.tranining.service;

import com.microservice.tranining.dto.ShoppingCart;
import com.microservice.tranining.dto.Product;
import com.microservice.tranining.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<ShoppingCart> getAllCarts() {
        List<ShoppingCart> carts = new ArrayList<>();
        cartRepository.findAll().forEach(carts::add);
        return carts;
    }

    public long getMaxCartId() {
        List<ShoppingCart> carts = new ArrayList<>();
        cartRepository.findAll().forEach(carts::add);
        return carts.stream().mapToLong(ShoppingCart::getId).max().getAsLong();
    }

    public ShoppingCart getCart(long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public void addProductToTheCart(long id, Product newProduct) {
        ShoppingCart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Such cart %s does not exists", id)));
        Product existingProduct = cart.getProduct(newProduct.getId());
        if (existingProduct == null) {
            cart.getProducts().add(newProduct);
        } else {
            newProduct.setQuantity(existingProduct.getQuantity() + newProduct.getQuantity());
        }
        cartRepository.save(cart);
    }

    public void deleteProductFromTheCart(long cardId, long productId) {
        ShoppingCart cart = cartRepository.findById(cardId).orElseThrow(() -> new RuntimeException(String.format("Such cart %s does not exists", cardId)));
        cart.getProducts().removeIf(p -> p.getId() == productId);
        cartRepository.save(cart);
    }

    public void saveCart(ShoppingCart cart) {
        cartRepository.save(cart);
    }

    public void updateCart(ShoppingCart cart) {
        cartRepository.save(cart);
    }

    public void deleteCart(long cartId) {
        cartRepository.deleteById(cartId);
    }
}
