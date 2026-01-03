package com.ecommerce.styledup.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Contacts {
    
    @GetMapping("/contacts")
    public String getContact(){
        return "contacts";
    }
}
