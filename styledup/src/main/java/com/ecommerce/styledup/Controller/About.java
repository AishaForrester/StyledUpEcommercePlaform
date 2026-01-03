package com.ecommerce.styledup.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class About {
    
    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
}
