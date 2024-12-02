package com.high.end.academia.academia.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "atividades")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String professor;

    @Column(nullable = false)
    private String dataEntrega;

    private int vagas;
    private int vagasDisponiveis;
}