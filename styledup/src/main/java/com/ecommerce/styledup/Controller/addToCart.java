package com.ecommerce.styledup.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.styledup.model.Product;
import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.service.ProductService;
import com.ecommerce.styledup.service.cartService;
import com.ecommerce.styledup.service.userService;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;


@Controller
public class addToCart {
    public final cartService cartService;
    public final userService userService;
    private final ProductService productService;

    public addToCart(cartService cartService, userService userService, ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

      
        @PostMapping("/addToCart")
        public String addToUserCart(@RequestParam Long productId,
                                    @AuthenticationPrincipal UserDetails userDetails  
        ) throws Exception {
            User user = userService.findUserByUsername(userDetails.getUsername());
            Product product = productService.getProductByID(productId);


            cartService.addProductToCart(user, product);
            System.out.println("Items in carts: " + cartService.getAllProducts().size());

            
            //testing List<Cart> carts = cartService.getCartItemsByUser(user);

            //System.out.println("User ID from add cart: " + user.getId());
            //System.out.println("Cart rows from add cart: " + carts.size());

        return "redirect:/dashboardBuyer";
     
    }
}
