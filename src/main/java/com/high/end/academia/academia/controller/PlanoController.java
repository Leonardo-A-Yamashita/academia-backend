package com.high.end.academia.academia.controller;


import com.high.end.academia.academia.model.Plano;
import com.high.end.academia.academia.service.PlanoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Plano>> getPlanosDisponiveis() {
        List<Plano> planos = planoService.getPlanosDisponiveis();
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/ativo")
    public ResponseEntity<Plano> getPlanoAtivo() {
        Plano plano = planoService.getPlanoAtivo();
        return plano != null ? ResponseEntity.ok(plano) : ResponseEntity.noContent().build();
    }

    @PostMapping("/comprar")
    public ResponseEntity<Map<String, Object>> comprarPlano(@RequestBody Long planoId) {
        Plano plano = planoService.comprarPlano(planoId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Plano adquirido com sucesso");
        response.put("plano", plano);

        return ResponseEntity.ok(response);
    }
}