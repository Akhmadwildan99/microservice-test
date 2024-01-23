package com.user.commerce.repository;

import com.user.commerce.entity.UserEccomerce;
import com.user.commerce.entity.enumeration.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEccomerceRepository extends JpaRepository<UserEccomerce, Long> {
    Optional<UserEccomerce> findUserEccomerceById(Long id);

    Optional<UserEccomerce> findUserEccomerceByIdAndUserType(Long id, UserType userType);
}
