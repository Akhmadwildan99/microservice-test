package com.user.commerce.controller;

import com.user.commerce.entity.UserEccomerce;
import com.user.commerce.entity.enumeration.UserType;
import com.user.commerce.service.UserEccomerceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserEccomerceResource {

    @Autowired
    private UserEccomerceService userEccomerceService;

    @GetMapping("/user-eccommerces/{id}")
    public ResponseEntity<UserEccomerce> findById(@PathVariable Long id) {
        log.info("[REST]findById, {} ", id);
        Optional<UserEccomerce> user = userEccomerceService.findOneById(id);

        return user.map(userEccomerce -> ResponseEntity.ok().body(userEccomerce)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user-eccommerces/is-exist/{id}/{userType}")
    public ResponseEntity<Long> isExistByIdAndUserType(@PathVariable Long id, @PathVariable UserType userType) {
        log.info("[REST]findById, {}, {} ", id, userType);
        if (userEccomerceService.checkUserExistByIdAndType(id, userType)) {
            return  ResponseEntity.ok().body(1L);
        }

        return  ResponseEntity.ok().body(0L);
    }
}
