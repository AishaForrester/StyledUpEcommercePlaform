package com.ecommerce.styledup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.service.cartService;
import com.ecommerce.styledup.service.userService;

import org.springframework.ui.Model;

/***************************NOTE:********************************
 
If refreshing the page should NOT repeat the action → use GET
If refreshing would repeat a harmful action → use POST

 ****************************************************************/
@Controller
public class ViewCartController {
    @Autowired
    private userService userService;

    @Autowired
    private cartService cartService;

    @GetMapping("/view-cart")
    public String viewCart(Model model, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        User user = userService.findUserByUsername(userDetails.getUsername());
        
        model.addAttribute("cartItems", cartService.getCartItemsByUser(user));
        model.addAttribute("totalPrice", cartService.calculateCartTotal(user));
        model.addAttribute("totalItemCount", cartService.getTotalItemCount(user));
        model.addAttribute("currentUser", userDetails);

        return "viewCart";
    }

}
