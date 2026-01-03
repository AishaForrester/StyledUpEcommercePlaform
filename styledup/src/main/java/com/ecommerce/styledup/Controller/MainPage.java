package com.ecommerce.styledup.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.styledup.service.ProductService;


@Controller
public class MainPage {

    @Autowired
    private final ProductService productService;

    public MainPage(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        System.out.println("Home page accessed");
        model.addAttribute("products", productService.getAllProducts());
        
        return "home"; // Thymeleaf template
    }
}
