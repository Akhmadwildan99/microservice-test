package com.order.ecommerce.dao;

import com.order.ecommerce.entity.enumeration.InventoryType;
import lombok.*;

import javax.persistence.*;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDao {
    private Long quantity;
    private InventoryType inventoryType;
    private Product product;
}
