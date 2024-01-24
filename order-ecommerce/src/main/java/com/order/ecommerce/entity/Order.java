package com.order.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eccommerce_order")
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "amaount")
    private BigDecimal amount;

    @Column(name = "qty")
    private Long qty;

    @Column(name = "created_date")
    private Instant createdDate;

}
