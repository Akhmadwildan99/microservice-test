package com.user.commerce.service;

import com.user.commerce.entity.UserEccomerce;
import com.user.commerce.entity.enumeration.UserType;
import com.user.commerce.repository.UserEccomerceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserEccomerceService {

    @Autowired
    private UserEccomerceRepository userEccomerceRepository;

    public Optional<UserEccomerce> findOneById(Long id) {
        log.info("[SERVICE] findOneById, {}", id);
        return userEccomerceRepository.findUserEccomerceById(id);
    }

    public boolean checkUserExistByIdAndType(Long id, UserType userType) {
        return userEccomerceRepository.findUserEccomerceByIdAndUserType(id, userType).isPresent();
    }
}
