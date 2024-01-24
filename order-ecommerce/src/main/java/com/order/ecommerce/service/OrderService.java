package com.order.ecommerce.service;

import com.order.ecommerce.client.ProductClient;
import com.order.ecommerce.dao.InventoryDao;
import com.order.ecommerce.dao.OrderDao;
import com.order.ecommerce.dao.Product;
import com.order.ecommerce.dao.ReturnMessage;
import com.order.ecommerce.entity.Order;
import com.order.ecommerce.entity.enumeration.InventoryType;
import com.order.ecommerce.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class OrderService {
    private final static  String ORDER_LABEL = "OR";
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    public ReturnMessage saveOrder(OrderDao orderDao) {
        log.info("[START] save order, {}" , orderDao);
        ReturnMessage ret= new ReturnMessage();
        ret.setId(-1);
        Long qtyProduct = productClient.getQtyProduct(orderDao.getProductId());
        if(qtyProduct.equals(0L) || qtyProduct.compareTo(orderDao.getQty()) < 0){
            ret.setMessage("Qty Product is empty");
            ret.setId(-1);
            return ret;
        }



        Order order = new Order();
        order.setAmount(orderDao.getAmount());
        order.setProductName(orderDao.getProductName());
        order.setProductId(orderDao.getProductId());
        order.setCreatedDate(Instant.now());
        order.setQty(orderDao.getQty());

        Order orderSave = orderRepository.saveAndFlush(order);
        orderSave.setOrderNumber(ORDER_LABEL+orderSave.getId());
        orderSave = orderRepository.save(orderSave);

        if(orderSave.getId() != null) {
            InventoryDao inventory = new InventoryDao();
            Product p = new Product();
            p.setId(orderSave.getProductId());
            inventory.setQuantity(orderSave.getQty());
            inventory.setInventoryType(InventoryType.ORDER);
            inventory.setProduct(p);

            productClient.addInventory(inventory);

            ret.setId(1);
            ret.setMessage("SUCCESS with order number "+ orderSave.getOrderNumber());
        }

        log.info("[END] save order, {}" , ret);

        return ret;
    }
}
