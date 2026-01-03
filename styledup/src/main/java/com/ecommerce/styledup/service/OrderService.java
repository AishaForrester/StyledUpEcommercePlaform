package com.ecommerce.styledup.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.styledup.model.Order;
import com.ecommerce.styledup.model.User;
import com.ecommerce.styledup.repository.OrderRepository;
import com.ecommerce.styledup.repository.OrderItemsRepository;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemsRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getAllProducts() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrderByUser(User user) {
        return orderRepository.findByUserId(user.getId());
    }
    
   public List<Order> getOrdersForSeller(int sellerId) {
        return orderRepository.findOrdersBySellerId(sellerId);
    }

    public int getTotalOrdersForSeller(int sellerId) {
        return getOrdersForSeller(sellerId).size();
    }

    public BigDecimal getTotalEarningsForSeller(int sellerId) {
        BigDecimal total = orderItemRepository.getTotalEarningsBySeller(sellerId);
        return total != null ? total : BigDecimal.ZERO;
    }

    public Order getLatestOrderForUser(User user) {
        return orderRepository.findTopByUser_IdOrderByOrderDateDesc(user.getId());
    }


}
