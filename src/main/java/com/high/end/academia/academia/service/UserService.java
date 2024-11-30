package com.high.end.academia.academia.service;

import com.high.end.academia.academia.enuns.Role;
import com.high.end.academia.academia.model.User;
import com.high.end.academia.academia.model.UserDto;
import com.high.end.academia.academia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public void registrar(UserDto userDto) {
        User user = new User();
        user.setNome(userDto.getNome());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getSenha()));
        user.setRole(Role.ALUNO);

        userRepository.save(user);
    }

    public User registerProfessor(String nome, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        User professor = new User();
        professor.setNome(nome);
        professor.setEmail(email);
        professor.setPassword(passwordEncoder.encode(password));
        professor.setRole(Role.PROFESSOR);

        return userRepository.save(professor);
    }
}