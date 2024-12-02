package com.high.end.academia.academia.controller;
import com.high.end.academia.academia.model.Atividade;
import com.high.end.academia.academia.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<Atividade> cadastrarAtividade(@RequestBody Atividade atividade) {
        Atividade novaAtividade = atividadeService.cadastrarAtividade(atividade);
        return ResponseEntity.ok(novaAtividade);
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> listarAtividades() {
        return ResponseEntity.ok(atividadeService.listarAtividades());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAtividade(@PathVariable Long id) {
        atividadeService.excluirAtividade(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/atividades/{id}/cadastrar")
    public ResponseEntity<?> cadastrarEmAtividade(@PathVariable Long id) {
        Optional<Atividade> optionalAtividade = atividadeService.getAtividade(id);
        if (optionalAtividade.isEmpty()) {
            return ResponseEntity.badRequest().body("Atividade não encontrada.");
        }

        Atividade atividade = optionalAtividade.get();
        if (atividade.getVagasDisponiveis() <= 0) {
            return ResponseEntity.badRequest().body("Não há vagas disponíveis nesta atividade.");
        }

        atividade.setVagasDisponiveis(atividade.getVagasDisponiveis() - 1);
        atividadeService.cadastrarAtividade(atividade);

        return ResponseEntity.ok("Cadastro realizado com sucesso!");
    }
}