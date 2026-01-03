package com.ecommerce.styledup.Controller;

import org.springframework.web.bind.annotation.GetMapping;

//import com.ecommerce.styledup.model.Order;
import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.service.OrderService;
import com.ecommerce.styledup.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class ViewOrders {
    @Autowired
    private OrderService orderService;

    @Autowired
    private userService userService;
  
    //private Order lastOrder;
    

    @GetMapping("/view-orders")
    public String viewOrders(Model model, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
         User user = userService.findUserByUsername(userDetails.getUsername());

        
        model.addAttribute("orders", orderService.getOrderByUser(user));
        //model.addAttribute("orderItems", lastOrder.getItems());

        model.addAttribute("currentUser", userDetails);
        return "viewOrders";
    }
}
