package com.ecommerce.styledup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.styledup.service.userService;

@Controller
public class signupController {
    @Autowired
    private userService userService;
   
    @GetMapping("/signup")
    public String showsignupPage() {
        return "signup"; 
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam String usernameNew, 
                                @RequestParam String passwordNew, 
                                @RequestParam String emailNew,
                                @RequestParam String role,
                                Model model) {
                          
            
                    try{
                        boolean success = userService.registerUser(usernameNew, passwordNew, emailNew, role);

                        if (success) {
                            return "redirect:/login";
                        } else {
                            model.addAttribute("error", "Username already exists");
                            return "login";
                        }



                    } catch(Exception e){
                        model.addAttribute("error", "Something went wrong");
                        return "signup";

                    }

                   
                        
                }


}

