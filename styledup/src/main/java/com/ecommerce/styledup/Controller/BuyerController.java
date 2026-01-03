package com.ecommerce.styledup.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.ecommerce.styledup.service.ProductService;
import com.ecommerce.styledup.service.cartService;
import com.ecommerce.styledup.service.userService;
import com.ecommerce.styledup.model.Cart;
import com.ecommerce.styledup.model.User;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class BuyerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private userService userService;

    @Autowired
    private cartService cartService;
    
    
    @GetMapping("/dashboardBuyer") 
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) throws Exception {  //added userDetails to get current user info
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("currentUser", userDetails); 

        User user = userService.findUserByUsername(userDetails.getUsername());
        List<Cart> cartItems = cartService.getCartItemsByUser(user);
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartItemCount", cartItems.size());

        System.out.println("User ID: " + user.getId());
        System.out.println("Cart rows: " + cartItems.size());
        

        return "buyerDashboard"; 

    }
}
