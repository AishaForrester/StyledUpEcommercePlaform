package com.ecommerce.styledup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import com.ecommerce.styledup.service.userService;
import com.ecommerce.styledup.model.Order;
import com.ecommerce.styledup.service.OrderService;



import com.ecommerce.styledup.model.User;

@Controller
public class OrderConfirmationGET {
    @Autowired
    private OrderService orderService;

    @Autowired
    private userService userService;
  
    private Order lastOrder;

   
    

    @GetMapping("/order-confirmation")
    public String orderConfirmation(Model model, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        User user = userService.findUserByUsername(userDetails.getUsername());

        lastOrder = orderService.getLatestOrderForUser(user);
        if(lastOrder == null) {
            return "redirect:/";
        }


        model.addAttribute("totalPrice", lastOrder.getTotalAmount());
        model.addAttribute("totalItemCount", lastOrder.getTotalItems());
        model.addAttribute("orderDate", lastOrder.getOrderDate());
        model.addAttribute("orderItems", lastOrder.getItems());

        model.addAttribute("currentUser", userDetails);

        return "orderConfirmation";

    }
}
