package com.product.commerce.repository;

import com.product.commerce.entity.Inventory;
import com.product.commerce.entity.enumeration.InventoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("select coalesce( sum(inventory.quantiy) over (partition by inventory.product.id, inventory.inventoryType), 0) from Inventory inventory where inventory.product.id = :productId and inventory.inventoryType = :inventoryType ")
    Long countQuantityProductByInventoryType(Long productId, InventoryType inventoryType);
}
