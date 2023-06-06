package com.project2.ACSchapter4.service;

import com.project2.ACSchapter4.model.DTOconverter;
import com.project2.ACSchapter4.model.User;
import com.project2.ACSchapter4.model.UserDTO;
import com.project2.ACSchapter4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import  java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<UserDTO> getAll(){
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId){
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return DTOconverter.convert(usuario);
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(UserDTO.convert(userDTO));
        return DTOconverter.convert(user);
    }

    public  UserDTO delete(long userId){
        User user = userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException());
        userRepository.delete(user);
        return DTOconverter.convert(user);
    }

    public UserDTO findByCpf(String cpf){
        User user = userRepository.findByCpf(cpf);
        if(user != null){
            DTOconverter.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name){
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(DTOconverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId,  UserDTO userDTO){
        User user = userRepository
                .findById(userId).orElseThrow(() -> new RuntimeException());
        if(userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())){
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())){
            user.setTelefone(userDTO.getTelefone());
        }
        if(userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())){
            user.setEndereco(userDTO.getEndereco());
        }
        user = userRepository.save(user);
        return DTOconverter.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page){
        Page<User>  users = userRepository.findAll(page);
        return users.map(DTOconverter::convert);
    }
}
