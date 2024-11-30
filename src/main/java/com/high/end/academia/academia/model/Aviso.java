package com.high.end.academia.academia.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "avisos")
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String dataPublicacao;
}