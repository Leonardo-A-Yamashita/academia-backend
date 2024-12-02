package com.high.end.academia.academia.service;


import com.high.end.academia.academia.model.Atividade;
import com.high.end.academia.academia.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade cadastrarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public List<Atividade> listarAtividades() {
        return atividadeRepository.findAll();
    }

    public Optional<Atividade> getAtividade(long id) {
        return atividadeRepository.findById(id);
    }

    public void excluirAtividade(Long id) {
        atividadeRepository.deleteById(id);
    }
}