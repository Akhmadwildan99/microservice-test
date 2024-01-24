package com.product.commerce.service;

import com.product.commerce.dao.ProductDao;
import com.product.commerce.dao.ReturnMessage;
import com.product.commerce.entity.Inventory;
import com.product.commerce.entity.Product;
import com.product.commerce.entity.enumeration.InventoryType;
import com.product.commerce.repository.InventoryRepository;
import com.product.commerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;



@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private InventoryRepository inventoryRepository;


    @Autowired
    private ProductService productService;

    @Test
    void testSaveProductDaoSuccess() {

        ProductDao productDao = new ProductDao(null, "Milki", new BigDecimal(70000), 90L);
        Product productDaoSave = new Product(1L, "Milki", new BigDecimal(70000));

        Product p = new Product();
        p.setId(1L);
        Inventory i = new Inventory();
        i.setId(1L);
        i.setInventoryType(InventoryType.INVENTORY);
        i.setQuantity(productDao.getQuantity());
        i.setProduct(p);

        when(productRepository.countAllByName(anyString())).thenReturn(0L);
        when(productRepository.save(any())).thenReturn(productDaoSave);
        when(inventoryRepository.save(any())).thenReturn(i);

        ReturnMessage returnMessage = productService.saveProductDao(productDao);

        assertThat(returnMessage.getId()).isEqualTo(1);
        assertThat(returnMessage.getMessage()).contains("successfully store product with quantity");
    }

    @Test
    void testSaveProductDaoFailed() {
        ProductDao productDao = new ProductDao(null, "Milki", new BigDecimal(70000), 90L);
        when(productRepository.countAllByName(anyString())).thenReturn(1L);

        ReturnMessage returnMessage = productService.saveProductDao(productDao);

        assertThat(returnMessage.getId()).isEqualTo(-1);
        assertThat(returnMessage.getMessage()).contains("product with name "+ productDao.getName() + " is exists");
    }

    @Test
    void testSaveProductInventorySuccess() {
        
        Product p = new Product();
        p.setId(1L);
        Inventory inventory = new Inventory();
        inventory.setInventoryType(InventoryType.INVENTORY);
        inventory.setQuantity(90L);
        inventory.setProduct(p);


        Inventory inventorySave = new Inventory();
        inventorySave.setId(1L);
        inventorySave.setInventoryType(InventoryType.INVENTORY);
        inventorySave.setQuantity(90L);
        inventorySave.setProduct(p);
        
        when(productRepository.existsById(anyLong())).thenReturn(true);
        when(inventoryRepository.save(any())).thenReturn(inventorySave);
        when(inventoryRepository.getInventoriesByProductIdAndInventoryType(anyLong(), eq(InventoryType.INVENTORY))).thenReturn(Arrays.asList(inventorySave));
        when(inventoryRepository.getInventoriesByProductIdAndInventoryType(anyLong(), eq(InventoryType.ORDER))).thenReturn(new ArrayList<>());


        ReturnMessage returnMessage = productService.saveProductInventory(inventory);

        assertThat(returnMessage.getId()).isEqualTo(1);

    }

    @Test
    void testSaveProductInventoryFailed() {
        Product p = new Product();
        p.setId(1L);
        Inventory inventory = new Inventory();
        inventory.setInventoryType(InventoryType.INVENTORY);
        inventory.setQuantity(90L);
        inventory.setProduct(p);

        when(productRepository.existsById(anyLong())).thenReturn(false);
        ReturnMessage returnMessage = productService.saveProductInventory(inventory);

        assertThat(returnMessage.getId()).isEqualTo(-1);
    }
}
