package com.ecommerce.styledup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.service.userService;



@Controller
public class loginController {
    @Autowired
    private userService userService;
   

   

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                              @RequestParam String password, 
                              @RequestParam String email,
                        Model model) throws Exception {
        
      
        User user = userService.authenticate(username, password);
        System.out.println("USER RETURNED FROM AUTHENTICATE: " + user.getRole());
        
        System.out.println("ROLE FROM ENTITY: [" + user.getRole() + "]");

        if ("SELLER".equals(user.getRole().trim())) {
            return "redirect:/dashboardSeller";
        }
        return "redirect:/dashboardBuyer";
        
        }

    
}
