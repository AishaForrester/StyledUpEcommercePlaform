package com.ecommerce.styledup.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Payment {
    
    @PostMapping("/payment")
    public String payment() {
        return "payment";
    }
}
