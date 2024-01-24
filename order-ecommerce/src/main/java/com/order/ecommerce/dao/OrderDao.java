package com.order.ecommerce.dao;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDao {
    private Long id;
    private String orderNumber;
    private Long productId;
    private String productName;
    private BigDecimal amount;
    private Long qty;
    private Instant createdDate;
}
