package com.product.commerce.dao;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {

    private Long id;
    private String name;
    private BigDecimal price;
    private Long quantity;
}
