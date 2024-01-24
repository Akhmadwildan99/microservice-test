package com.user.commerce.entity;

import com.user.commerce.entity.enumeration.UserType;
//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


//import java.io.Serializable;

@Table(name = "user_ecomerce")
@Entity
@Data
@Getter
@Setter
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
