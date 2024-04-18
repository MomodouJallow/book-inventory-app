package com.example.jallowbooks.bookOrders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TheOrder, Long> {

    @Query("SELECT o.id, o.userId, o.status, o.createdAt, o.updatedAt FROM TheOrder o WHERE o.userId = :userId")
    List<OrderResponse> findByUserId(Long userId);
}
