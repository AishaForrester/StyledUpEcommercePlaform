package com.ecommerce.styledup.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.styledup.service.cartService;
import com.ecommerce.styledup.service.userService;
import com.ecommerce.styledup.service.OrderService;
import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.model.Cart;
import com.ecommerce.styledup.model.Order;
import com.ecommerce.styledup.model.OrderItems;
import com.ecommerce.styledup.repository.OrderItemsRepository;
import java.util.List;

@RestController
public class OrderConfirmationPost {
    @Autowired
    private userService userService;

    @Autowired
    private cartService cartService;

    @Autowired
    private OrderService OrderService;

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    
    @PostMapping("/orders/confirm")
    public ResponseEntity<Void> confirmOrder(@AuthenticationPrincipal UserDetails userDetails) throws Exception {

    User user = userService.findUserByUsername(userDetails.getUsername());

    // 1. Get cart items
    List<Cart> cartItems = cartService.getCartItemsByUser(user);

    // 2. Create Order
    Order order = new Order();
    order.setUser(user);
    order.setTotalAmount(cartService.calculateCartTotal(user));
    order.setOrderDate(LocalDateTime.now());

    OrderService.saveOrder(order);

    // 3. Create OrderItems
    for (Cart cart : cartItems) {
        OrderItems item = new OrderItems();
        item.setOrder(order);
        item.setProduct(cart.getProduct());
        item.setQuantity(cart.getQuantity());
        item.setPrice(cart.getProduct().getPrice());

        orderItemsRepository.save(item);  //should not speak to datebase directly from controller will fix later!!
    }

    // 4. Clear cart
    cartService.clearCart(user);

    return ResponseEntity.ok().build();
}

}
