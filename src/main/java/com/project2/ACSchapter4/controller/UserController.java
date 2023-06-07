package com.project2.ACSchapter4.controller;

import com.project2.ACSchapter4.model.UserDTO;
import com.project2.ACSchapter4.service.UserService;
import jakarta.transaction.UserTransaction;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/id")
    public UserDTO findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/data")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO){
        return userService.save(userDTO);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(@RequestParam(name="key", required=true) String key, @PathVariable String cpf){
        return userService.findByCpf(cpf, key);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws UserPrincipalNotFoundException{
        userService.delete(id);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true)String nome){
        return userService.queryByName(nome);
    }

    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.editUser(id, userDTO);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable){
        return userService.getAllPage((org.springframework.data.domain.Pageable) pageable);
    }

}
