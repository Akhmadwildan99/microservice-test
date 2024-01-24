package com.product.commerce.service;

import com.product.commerce.dao.ProductDao;
import com.product.commerce.dao.ReturnMessage;
import com.product.commerce.entity.Inventory;
import com.product.commerce.entity.Product;
import com.product.commerce.entity.enumeration.InventoryType;
import com.product.commerce.repository.InventoryRepository;
import com.product.commerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    public ReturnMessage saveProductDao(ProductDao productDao) {
        log.info("[START SERVICE] save product, {}", productDao.getName());

        ReturnMessage ret = new ReturnMessage();
        ret.setId(-1);

        Long countByName = productRepository.countAllByName(productDao.getName());

        if(countByName.compareTo(0L) > 0) {
            ret.setMessage("product with name "+ productDao.getName() + " is exists");
            return ret;
        }

        Product product = new Product();
        product.setName(productDao.getName());
        product.setPrice(productDao.getPrice());

        Product productSave = productRepository.save(product);
        Long counInventory = 0L;

        if(productDao.getQuantity() != null && productDao.getQuantity().compareTo(0L) > 0 && productSave.getId() != null) {
            Inventory inventory = new Inventory();
            inventory.setProduct(productSave);
            inventory.setInventoryType(InventoryType.INVENTORY);
            inventory.setQuantity(productDao.getQuantity());

            Inventory inventorySave = inventoryRepository.save(inventory);
            counInventory = inventorySave.getQuantity();
        }

        if(productSave.getId() != null) {
            ret.setId(1);
            ret.setMessage("successfully store product with quantity "+ counInventory);
        }
        log.info("[END SERVICE] save product, {}", ret.getId() );

        return ret;



    }


    public ProductDao getProductById(Long id) {
        Product product = productRepository.getById(id);


        ProductDao productDao = new ProductDao();
        productDao.setId(product.getId());
        productDao.setName(product.getName());
        productDao.setPrice(product.getPrice());
        productDao.setQuantity(quantityProduct(id));

        return  productDao;
    }

    public Long getQuantityProduct(Long id) {
        if(productRepository.existsById(id)) {
            return  quantityProduct(id);
        }

        return 0L;

    }

    public ReturnMessage saveProductInventory(Inventory inventory) {
        log.info("[START SERVICE]  save inventory, {}", inventory);

        ReturnMessage ret = new ReturnMessage();
        ret.setId(-1);
        if(!productRepository.existsById(inventory.getProduct().getId())) {
            ret.setMessage("product not exist");
            return  ret;
        }
        Inventory saved = inventoryRepository.save(inventory);

        if(saved.getId() != null) {

            long currentQty = quantityProduct(inventory.getProduct().getId());
            ret.setMessage("success save "+ inventory.getInventoryType().toString().toLowerCase()+ " and current qty is "+ currentQty);

            ret.setId(1);
        }
        log.info("[END SERVICE] save inventory, {}", inventory);

        return  ret;

    }


    public Long quantityProduct(Long idProduct) {
        List<Inventory> inventories = inventoryRepository.getInventoriesByProductIdAndInventoryType(idProduct, InventoryType.INVENTORY);
        List<Inventory> order = inventoryRepository.getInventoriesByProductIdAndInventoryType(idProduct, InventoryType.ORDER);

        Long countIn = 0L;
        Long countOut= 0L;

        if(!inventories.isEmpty()) {
            for (Inventory i : inventories) {
                countIn+= i.getQuantity();
            }
        }

        if(!order.isEmpty()) {
            for (Inventory i : order) {
                countOut+= i.getQuantity();
            }
        }

        return countIn - countOut;
    }


}
