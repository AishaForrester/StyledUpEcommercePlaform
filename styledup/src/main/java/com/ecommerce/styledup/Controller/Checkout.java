package com.ecommerce.styledup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.service.cartService;
import com.ecommerce.styledup.service.userService;

@Controller
public class Checkout {
    @Autowired
    private userService userService;

    @Autowired
    private cartService cartService;
    
    @PostMapping("/checkout")
    public String checkout(Model model, @AuthenticationPrincipal UserDetails userDetails) throws Exception{

        User user = userService.findUserByUsername(userDetails.getUsername());
        model.addAttribute("cartItems", cartService.getCartItemsByUser(user));
        model.addAttribute("totalPrice", cartService.calculateCartTotal(user));
        model.addAttribute("totalItemCount", cartService.getTotalItemCount(user));

        model.addAttribute("currentUser", userDetails);

        return "checkout";
    }
}
