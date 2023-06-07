package com.project2.ACSchapter4.controller;

import com.project2.ACSchapter4.model.DTOconverter;
import com.project2.ACSchapter4.model.UserDTO;
import com.project2.ACSchapter4.service.UserService;
import com.project2.ACSchapter4.service.UserServiceTest;
import org.junit.jupiter.api.Assertions;
import	org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import	org.junit.jupiter.api.extension.ExtendWith;
import	org.mockito.InjectMocks;
import	org.mockito.Mock;
import org.mockito.Mockito;
import	org.mockito.junit.jupiter.MockitoExtension;
import	org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import	org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;

    @Test
    public void testListUsers() throws Exception{
        List<UserDTO> users = new ArrayList<>();
        users.add(DTOconverter.convert(UserServiceTest.getUser(1, "Guilherme","123.456.789-01")));
        Mockito.when(userService.getAll()).thenReturn(users);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(("[{\"nome\":\"Nome	1\","
                + "\"cpf\":\"123\",\"endereco\":\"endereco\",\"key\":null,"
                + "\"email\":null,\"telefone\":\"5432\",\"dataCadastro\":null}]"),
                resp);
    }

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
}
