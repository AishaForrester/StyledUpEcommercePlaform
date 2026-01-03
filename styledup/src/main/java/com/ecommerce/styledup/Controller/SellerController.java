package com.ecommerce.styledup.Controller;


import java.io.File;
import java.math.BigDecimal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.ecommerce.styledup.model.Product;
import com.ecommerce.styledup.service.ProductService;
import com.ecommerce.styledup.service.userService;
import com.ecommerce.styledup.service.OrderService;

@Controller
public class SellerController  {

    private ProductService productService;
    private userService userService;
    private OrderService orderService;

    private final String uploadDir = System.getProperty("user.dir") + "/uploads/products/"; 

    public SellerController(ProductService productService,
                            userService userService,
                            OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }
    

    @GetMapping("/dashboardSeller")
    public String sellerdashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) throws Exception {

        // Get seller id from logged-in user
        int sellerId = userService.findUserByUsername(userDetails.getUsername()).getId();

        // Get total earnings
        BigDecimal totalEarnings = orderService.getTotalEarningsForSeller(sellerId);

        int totalOrders = orderService.getTotalOrdersForSeller(sellerId);

        model.addAttribute("totalEarnings", totalEarnings);
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("currentUser", userDetails);
        return "sellerDashboard";  
    }
    

   @GetMapping("/addProduct")
   public String showAddProductForm() {
       return "addProduct"; 
   }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("price") BigDecimal price,
                             @RequestParam("stockQuantity") int stockQuantity,
                             @RequestParam("imageUrl") MultipartFile imageFile,
                             @AuthenticationPrincipal UserDetails userDetails) throws Exception {

        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs(); // create folders if they don't exist
        }

        // Save the image to the folder
        String fileName = imageFile.getOriginalFilename();
        File file = new File(uploadFolder, fileName);
        imageFile.transferTo(file);

        //Define processed image path
        //String processedFileName = "processed_"+ originalFileName;
        //File processedFile = new File(uploadFolder, processedFileName);

        // Replace background with light grey
        //ImageUtils.replaceBackground(originalFile, processedFile, new Color(240, 240, 240));


        Product product = new Product();
        
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);

        //Set seller ID from logged-in user
        int sellerId = userService.findUserByUsername(userDetails.getUsername()).getId();
        product.setsellerId(sellerId);
        

        product.setImageUrl("/uploads/products/" + fileName); // Default image URL

        System.out.println("Saving product image: " + fileName);
        System.out.println("Full file path: " + file.getAbsolutePath());

        productService.saveProduct(product);
        return "redirect:/dashboardSeller"; 
    }
   
}
