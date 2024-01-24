package com.product.commerce.repository;

import com.product.commerce.entity.Inventory;
import com.product.commerce.entity.enumeration.InventoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.ws.rs.QueryParam;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
//    @QueryParam("SELECT COALESCE(SUM(i.quantity), 0) FROM Inventory i WHERE i.product.id = :productId AND i.inventoryType = :inventoryType")
//    Long countQuantityProductByInventoryType(Long productId, InventoryType inventoryType);

    List<Inventory> getInventoriesByProductIdAndInventoryType(Long productId, InventoryType inventoryType);
}
