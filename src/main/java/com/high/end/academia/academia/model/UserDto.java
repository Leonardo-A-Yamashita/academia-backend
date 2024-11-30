package com.high.end.academia.academia.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
}
