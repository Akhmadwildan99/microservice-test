package com.order.ecommerce.service;

import com.order.ecommerce.client.ProductClient;
import com.order.ecommerce.dao.OrderDao;
import com.order.ecommerce.dao.ReturnMessage;
import com.order.ecommerce.entity.Order;
import com.order.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ProductClient productClient;

    @Test
    void testAddOrderSuccess() {

        OrderDao orderDao = new OrderDao();
        orderDao.setProductId(1L);
        orderDao.setProductName("Milki");
        orderDao.setAmount(new BigDecimal(900000));
        orderDao.setQty(22L);

        Order orderSave = new Order();
        orderSave.setId(1L);
        orderSave.setProductId(1L);
        orderSave.setProductName("Milki");
        orderSave.setAmount(new BigDecimal(900000));
        orderSave.setQty(22L);

        Order orderSave2 = new Order();
        orderSave2.setId(1L);
        orderSave2.setOrderNumber("OR"+ 1L);
        orderSave2.setProductId(1L);
        orderSave2.setProductName("Milki");
        orderSave2.setAmount(new BigDecimal(900000));
        orderSave2.setQty(22L);

        when(productClient.getQtyProduct(anyLong())).thenReturn(22L);
        when(orderRepository.saveAndFlush(any())).thenReturn(orderSave);
        when(orderRepository.save(any())).thenReturn(orderSave2);
        doNothing().when(productClient).addInventory(any());

        ReturnMessage returnMessage = orderService.saveOrder(orderDao);

        verify(productClient, times(1)).getQtyProduct(anyLong());
        verify(orderRepository, times(1)).saveAndFlush(any());
        verify(orderRepository, times(1)).save(any());
        verify(productClient, times(1)).addInventory(any());

        assertThat(returnMessage.getId()).isEqualTo(1);
        assertThat(returnMessage.getMessage()).isNotNull();
    }

    @Test
    void testAddOrderFailed() {
        OrderDao orderDao = new OrderDao();
        orderDao.setProductId(1L);
        orderDao.setProductName("Milki");
        orderDao.setAmount(new BigDecimal(900000));
        orderDao.setQty(22L);

        when(productClient.getQtyProduct(anyLong())).thenReturn(0L);

        ReturnMessage returnMessage = orderService.saveOrder(orderDao);
        verify(productClient, times(1)).getQtyProduct(anyLong());
        assertThat(returnMessage.getId()).isEqualTo(-1);
        assertThat(returnMessage.getMessage()).isNotNull();
    }


}
