package com.ecommerce.styledup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.styledup.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    //List<Cart> findByUser(User user);
     @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findByUserId(@Param("userId") Integer userId);

    Order findTopByUser_IdOrderByOrderDateDesc(Integer userId);

    @Query("SELECT DISTINCT oi.order " +
           "FROM OrderItems oi " +
           "WHERE oi.product.sellerId = :sellerId")
    List<Order> findOrdersBySellerId(@Param("sellerId") int sellerId);
}


/************************************************EXPLANATION*******************************************************
    oi.order → gets the order each item belongs to.

    DISTINCT → ensures the same order isn’t counted multiple times if it has multiple products from the seller.

    oi.product.sellerId = :sellerId → only items from this seller
 ******************************************************************************************************************/
