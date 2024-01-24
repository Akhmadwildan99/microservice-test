package com.order.ecommerce.client;

import com.order.ecommerce.dao.InventoryDao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ECOMMCERCE-PRODUCT")
public interface ProductClient{
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/products/inventories")
    void addInventory(@RequestBody InventoryDao inventoryDao);

    @RequestMapping(method= RequestMethod.GET ,value = "/api/v1/products/quantities/{id}")
    Long getQtyProduct(@PathVariable("id") Long id);
}
