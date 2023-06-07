package com.project2.ACSchapter4.service;

import com.project2.ACSchapter4.model.DTOconverter;
import	com.project2.ACSchapter4.model.UserDTO;
import	com.project2.ACSchapter4.model.User;
import	com.project2.ACSchapter4.repository.UserRepository;
import	org.junit.jupiter.api.Assertions;
import	org.junit.jupiter.api.Test;
import	org.junit.jupiter.api.extension.ExtendWith;
import	org.mockito.InjectMocks;
import	org.mockito.Mock;
import	org.mockito.Mockito;
import	org.mockito.junit.jupiter.MockitoExtension;
import	java.util.ArrayList;
import	java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testListAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(getUser(1, "Guilherme", "123.456.789-01"));
        users.add(getUser(2, "Araújo", "154.489.298-88"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> usersReturn = userService.getAll();

        Assertions.assertEquals(2, usersReturn.size());
    }

    @Test
    public void testSaveUser(){
        User userDB = getUser(1, "Guilherme", "123.456.789-01");
        UserDTO userDTO = DTOconverter.convert(userDB);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDB);

        UserDTO user = userService.save(userDTO);
        Assertions.assertEquals("Guilherme", user.getNome());
        Assertions.assertEquals("123.456.789-01", user.getCpf());
    }

    @Test
    public void editSaveUser(){
        User userDB = getUser(1, "Guilherme", "123.456.789-01");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userDB));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDB);

        UserDTO userDTO = DTOconverter.convert(userDB);
        userDTO.setEndereco("Rua da Imprensa");
        userDTO.setTelefone("91234-5678");

        UserDTO user = userService.editUser(1L, userDTO);
        Assertions.assertEquals("Rua da Imprensa", user.getEndereco());
        Assertions.assertEquals("91234-5678", user.getTelefone());
    }

    public static User getUser(Integer id, String nome, String cpf){
        User user = new User();
        user.setId(id);
        user.setNome(nome);
        user.setCpf(cpf);
        user.setEndereco("Rua da Imprensa");
        user.setTelefone("91234-5678");
        return user;
    }
}
