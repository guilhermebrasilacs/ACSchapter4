package com.project2.ACSchapter4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO	{
    private	String	nome;
    private	String	cpf;
    private	String	endereco;
    private	String	email;
    private	String	telefone;
    private LocalDateTime dataCadastro;

    public static User convert(UserDTO userDTO){
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setCpf(userDTO.getCpf());
        user.setTelefone(userDTO.getTelefone());
        user.setEmail(userDTO.getEmail());
        user.setEndereco(userDTO.getEndereco());
        user.setDataCadastro(userDTO.getDataCadastro());
        return user;
    }
}
