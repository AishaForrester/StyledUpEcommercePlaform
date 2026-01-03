package com.ecommerce.styledup.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.styledup.model.Product;
import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.model.Cart;
import com.ecommerce.styledup.repository.cartRepository;

@Service
public class cartService {
    private final cartRepository cartRepository;

    public cartService(cartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllProducts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id) throws Exception {
        return cartRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new Exception("Cart not found with id: " + id));

}

    public List<Cart> getCartItemsByUser(User user) {
        return cartRepository.findByUserId(user.getId());
    }


    // method to save/insert into cart
    public Cart saveToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart addProductToCart(User user, Product product) {  //add product to cart based on user
        Cart cart = cartRepository             //check database to make sure product is not already there
        .findByUserAndProduct(user, product)
        .orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setProduct(product);
            newCart.setQuantity(0);
            return newCart;
        });
        cart.setQuantity(cart.getQuantity() + 1);
        cart.setAddedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public int getTotalItemCount(User user) {
    return getCartItemsByUser(user)
            .stream()
            .mapToInt(Cart::getQuantity)
            .sum();
}


    

    public BigDecimal calculateCartTotal(User user) {
    List<Cart> cartItems = getCartItemsByUser(user);

    BigDecimal total = BigDecimal.ZERO;

    for (Cart item : cartItems) {
        BigDecimal itemTotal =
            item.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));

        total = total.add(itemTotal);
    }

    return total;
}

public void clearCart(User user) {
    List<Cart> cartItems = getCartItemsByUser(user);
    cartRepository.deleteAll(cartItems);
}

}
