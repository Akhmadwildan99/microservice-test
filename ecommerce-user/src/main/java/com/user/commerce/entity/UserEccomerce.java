package com.user.commerce.entity;

import com.user.commerce.entity.enumeration.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "user_ecomerce")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEccomerce implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String username;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
}
