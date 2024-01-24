package com.user.commerce.controller;

import com.user.commerce.dao.ReturnMessage;
import com.user.commerce.entity.UserEccomerce;
import com.user.commerce.entity.enumeration.UserType;
import com.user.commerce.service.UserEccomerceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/user-eccommerces")
    public ResponseEntity<ReturnMessage> addUser(@RequestBody UserEccomerce userEccomerce) throws URISyntaxException {
        log.info("[REST] add user, {}", userEccomerce);
        ReturnMessage ret = new ReturnMessage();
        ret.setId(-1);

        if(userEccomerce.getId() != null) {
            ret.setMessage("id must null");
            return ResponseEntity.badRequest().body(ret);
        }

        ret = userEccomerceService.addUser(userEccomerce);

        if(ret.getId() == -1) {
            return ResponseEntity.badRequest().body(ret);
        }

        return  ResponseEntity.created(new URI("/api/v1/user-eccommerces")).body(ret);
    }
}
