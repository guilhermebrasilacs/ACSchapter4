package com.project2.ACSchapter4.model;

import com.project2.ACSchapter4.model.UserDTO;
import com.project2.ACSchapter4.model.User;
public class DTOconverter {
    public static UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setCpf(user.getCpf());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setKey(user.getKey());
        userDTO.setEmail(user.getEmail());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }
}
