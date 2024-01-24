package com.product.commerce.entity;

import com.product.commerce.entity.enumeration.InventoryType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "inventory")
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private Long quantiy;

    @Column(name = "inventory_type")
    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType;

    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
}
