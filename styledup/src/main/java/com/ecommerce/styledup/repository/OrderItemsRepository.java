package com.ecommerce.styledup.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.styledup.model.OrderItems;



@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    @Query("SELECT SUM(oi.price * oi.quantity) " +
           "FROM OrderItems oi " +
           "WHERE oi.product.sellerId = :sellerId")
    BigDecimal getTotalEarningsBySeller(@Param("sellerId") int sellerId);
}


/***************************EXPLANATION**************************************
 
    oi.price * oi.quantity → total for each item.

    oi.product.sellerId = :sellerId → filter only this seller’s products.

    Returns BigDecimal which is perfect for money.

 ****************************************************************************/
