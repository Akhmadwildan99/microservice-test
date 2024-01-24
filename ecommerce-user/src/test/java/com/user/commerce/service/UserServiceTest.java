package com.user.commerce.service;


import com.user.commerce.dao.ReturnMessage;
import com.user.commerce.entity.UserEccomerce;
import com.user.commerce.entity.enumeration.UserType;
import com.user.commerce.repository.UserEccomerceRepository;
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
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
    @MockBean
    private UserEccomerceRepository userEccomerceRepository;

    @Autowired
    private UserEccomerceService userEccomerceService;



    @Test
    void testAddUsetSuccess() {
        UserEccomerce userEccomerce = new UserEccomerce();
        userEccomerce.setUsername("Akhmad");
        userEccomerce.setUserType(UserType.CUSTOMER);

        UserEccomerce userEccomerceSave = new UserEccomerce();
        userEccomerceSave.setId(1L);
        userEccomerceSave.setUsername("Akhmad");
        userEccomerceSave.setUserType(UserType.CUSTOMER);

        when(userEccomerceRepository.findByUsername(anyString())).thenReturn(new ArrayList<>());
        when(userEccomerceRepository.save(any())).thenReturn(userEccomerceSave);

        ReturnMessage returnMessage = userEccomerceService.addUser(userEccomerce);

        assertThat(returnMessage.getId()).isEqualTo(1);
        assertThat(returnMessage.getMessage()).isNotNull();

        verify(userEccomerceRepository, times(1)).findByUsername(anyString());
        verify(userEccomerceRepository, times(1)).save(any());


    }

    @Test
    void testAddUsetFailed() {
        UserEccomerce userEccomerce = new UserEccomerce();
        userEccomerce.setUsername("Akhmad");
        userEccomerce.setUserType(UserType.CUSTOMER);

        UserEccomerce userEccomerceRet = new UserEccomerce();
        userEccomerceRet.setId(1L);
        userEccomerceRet.setUsername("Akhmad");
        userEccomerceRet.setUserType(UserType.CUSTOMER);

        when(userEccomerceRepository.findByUsername(anyString())).thenReturn(List.of(userEccomerceRet));
        ReturnMessage returnMessage = userEccomerceService.addUser(userEccomerce);

        assertThat(returnMessage.getId()).isEqualTo(-1);
        verify(userEccomerceRepository, times(1)).findByUsername(anyString());

    }
}
