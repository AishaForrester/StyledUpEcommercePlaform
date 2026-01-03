package com.ecommerce.styledup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ecommerce.styledup.model.Cart;
import com.ecommerce.styledup.model.Product;
import com.ecommerce.styledup.model.User;

@Repository
public interface cartRepository extends JpaRepository<Cart, Integer> {
    
    //List<Cart> findByUser(User user);
     @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    List<Cart> findByUserId(@Param("userId") Integer userId);

    //used in cartService to check whether product already exists befores adding to cart
    Optional<Cart> findByUserAndProduct(User user, Product product);  

    //used in cartService to get the cart items for a specific
    List<Cart> findByUser(User user);
}







