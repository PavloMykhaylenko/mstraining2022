package com.microservice.tranining.controller;

import com.microservice.tranining.common.Const;
import com.microservice.tranining.dto.Product;
import com.microservice.tranining.dto.ShoppingCart;
import com.microservice.tranining.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping()
    public List<ShoppingCart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public ShoppingCart getCart(@PathVariable long cartId) {
        return cartService.getCart(cartId);
    }

    @GetMapping("/maxCartId")
    public long getMaxCartId() {
        return cartService.getMaxCartId();
    }

    @PostMapping("/create")
    public ShoppingCart createCart(@RequestBody ShoppingCart shoppingCart) {
        if (shoppingCart.getId() == 0L) {
            shoppingCart.setId(cartService.getMaxCartId());
        }
        if (StringUtils.isEmpty(shoppingCart.getStatus())) {
            shoppingCart.setStatus(Const.OPEN);
        }
        cartService.saveCart(shoppingCart);
        return shoppingCart;
    }

    @PostMapping("/{cartId}")
    public void addProductToTheCart(@PathVariable("cartId") long cartId, @RequestBody Product product) {
        cartService.addProductToTheCart(cartId, product);
    }

    @DeleteMapping("/{cartId}/{productId}")
    public void deleteProductFromTheCart(@PathVariable long cartId, @PathVariable long productId) {
        cartService.deleteProductFromTheCart(cartId, productId);
    }

    @PutMapping("/{cartId}")
    public void updateShoppingCart(@PathVariable long cartId, @RequestBody ShoppingCart cart) {
        cartService.updateCart(cart);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable long cartId) {
        cartService.deleteCart(cartId);
    }
}
