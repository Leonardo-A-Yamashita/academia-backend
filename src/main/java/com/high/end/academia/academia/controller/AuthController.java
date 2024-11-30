package com.high.end.academia.academia.controller;


import com.high.end.academia.academia.model.LoginDto;
import com.high.end.academia.academia.model.User;
import com.high.end.academia.academia.model.UserDto;
import com.high.end.academia.academia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Optional<User> loggedInUser = userService.login(loginDto.getEmail(), loginDto.getPassword());
        if (loggedInUser.isPresent()) {
            return ResponseEntity.ok(loggedInUser.get());
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        if (!userDto.getSenha().equals(userDto.getConfirmarSenha())) {
            return ResponseEntity.badRequest().body("As senhas não coincidem.");
        }

        userService.registrar(userDto);
        return ResponseEntity.ok(Map.of("message", "Registro realizado com sucesso"));
    }  

    @PostMapping("/registrar/professor")
    public ResponseEntity<?> registerProfessor(@RequestBody UserDto userDto) {
        if (!userDto.getSenha().equals(userDto.getConfirmarSenha())) {
            return ResponseEntity.badRequest().body("As senhas não coincidem.");
        }

        var professor = userService.registerProfessor(userDto.getNome(), userDto.getEmail(), userDto.getSenha());
        return ResponseEntity.ok("Professor registrado com sucesso!" + professor.getEmail());
    }


}