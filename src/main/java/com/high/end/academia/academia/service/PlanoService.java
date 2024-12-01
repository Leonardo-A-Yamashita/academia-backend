package com.high.end.academia.academia.service;

import com.high.end.academia.academia.model.Plano;
import com.high.end.academia.academia.repository.PlanoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> getPlanosDisponiveis() {
        return planoRepository.findByAtivoFalse();
    }

    public Plano getPlanoAtivo() {
        Plano planoAtivo = planoRepository.findByAtivoTrue()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum plano ativo encontrado"));

        planoAtivo.setVencimento(LocalDate.now().plusDays(30));

        return planoAtivo;
    }


    public Plano comprarPlano(Long planoId) {
        Plano novoPlano = planoRepository.findById(planoId)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));
        Plano planoAtivo = planoRepository.findByAtivoTrue().orElse(null);

        LocalDate novoVencimento;

        if (planoAtivo != null && planoAtivo.getVencimento() != null) {
            novoVencimento = planoAtivo.getVencimento().plusDays(calcularDiasPlano(novoPlano));
        } else {
            novoVencimento = LocalDate.now().plusDays(calcularDiasPlano(novoPlano));
        }

        planoAtivo = new Plano();
        planoAtivo.setTitulo(novoPlano.getTitulo());
        planoAtivo.setDescricao(novoPlano.getDescricao());
        planoAtivo.setPreco(novoPlano.getPreco());
        planoAtivo.setAtivo(true);
        planoAtivo.setVencimento(novoVencimento);

        return planoAtivo;
    }

    private int calcularDiasPlano(Plano plano) {
        switch (plano.getTitulo().toLowerCase()) {
            case "plano mensal":
                return 30;
            case "plano trimestral":
                return 90;
            case "plano anual":
                return 365;
            default:
                throw new IllegalArgumentException("Plano desconhecido");
        }
    }
}
