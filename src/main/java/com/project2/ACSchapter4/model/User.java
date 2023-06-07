package com.project2.ACSchapter4.model;

import	jakarta.persistence.Entity;
import	jakarta.persistence.GeneratedValue;
import	jakarta.persistence.GenerationType;
import	jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import	java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String key;
    private String email;
    private String endereco;
    private String telefone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCadastro;

    public static User convert(UserDTO userDTO){
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setCpf(userDTO.getCpf());
        user.setTelefone(userDTO.getTelefone());
        user.setKey(userDTO.getKey());
        user.setEmail(userDTO.getEmail());
        user.setEndereco(userDTO.getEndereco());
        user.setDataCadastro(userDTO.getDataCadastro());
        return user;
    }
}
