package com.order.ecommerce.controller;

import com.order.ecommerce.dao.OrderDao;
import com.order.ecommerce.dao.ReturnMessage;
import com.order.ecommerce.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<ReturnMessage> addOrder(@RequestBody OrderDao orderDao) throws URISyntaxException {
        log.info("[REST] ADD ORDER, {}",orderDao );

        ReturnMessage ret = new ReturnMessage();
        ret.setId(-1);

        if(orderDao.getId() != null || orderDao.getProductId() == null) {
            ret.setMessage("id must null or product id must be not null");
            ret.setId(-1);
            return  ResponseEntity.badRequest().body(ret);
        }

         ret = orderService.saveOrder(orderDao);

        if(ret.getId() == -1) {
            return  ResponseEntity.badRequest().body(ret);
        }

        return  ResponseEntity.created(new URI("/api/v1/orders")).body(ret);
    }
}
