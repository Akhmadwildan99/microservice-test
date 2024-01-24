package com.product.commerce.controller;

import com.product.commerce.dao.ProductDao;
import com.product.commerce.dao.ReturnMessage;
import com.product.commerce.entity.Inventory;
import com.product.commerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ReturnMessage> addProduct(@RequestBody ProductDao productDao) throws URISyntaxException {
        log.info("REQUEST POST ADD PRODUCT, {}", productDao);
        ReturnMessage ret = new ReturnMessage();
        ret.setId(-1);

        if(productDao.getId() != null ) {
            return  ResponseEntity.badRequest().body(ret);
        }

         ret = productService.saveProductDao(productDao);

        if(ret.getId() == -1) {
            return  ResponseEntity.badRequest().body(ret);
        }

        return  ResponseEntity.created(new URI("/api/v1/products")).body(ret);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDao> getProductById(@PathVariable Long id) {
        log.info("REQUEST GET  PRODUCT BY ID, {}", id);

        ProductDao product = productService.getProductById(id);
        return  ResponseEntity.ok().body(product);
    }

    @GetMapping("/products/quantities/{id}")
    public ResponseEntity<Long> getProductQtyById(@PathVariable Long id) {
        log.info("REQUEST GET  PRODUCT QTY BY ID, {}", id);
        return  ResponseEntity.ok().body(productService.getQuantityProduct(id));
    }

    @PostMapping("/products/inventories")
    public ResponseEntity<ReturnMessage> addProductInventory(@RequestBody Inventory inventory) throws URISyntaxException {
        log.info("REQUEST POST ADD PRODUCT, {}", inventory);
        ReturnMessage ret = new ReturnMessage();
        ret.setId(-1);

        if(inventory.getId() != null ) {
            return  ResponseEntity.badRequest().body(ret);
        }

        ret = productService.saveProductInventory(inventory);

        if(ret.getId() == -1) {
            return  ResponseEntity.badRequest().body(ret);
        }

        return  ResponseEntity.created(new URI("/api/v1/products/inventories")).body(ret);
    }
}
