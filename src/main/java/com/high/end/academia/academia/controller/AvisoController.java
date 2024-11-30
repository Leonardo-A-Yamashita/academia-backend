package com.high.end.academia.academia.controller;


import com.high.end.academia.academia.model.Aviso;
import com.high.end.academia.academia.service.AvisoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avisos")
public class AvisoController {
    private final AvisoService avisoService;

    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    @GetMapping
    public List<Aviso> getAvisos() {
        return avisoService.getAllAvisos();
    }

    @PostMapping
    public ResponseEntity<Aviso> criarAviso(@RequestBody Aviso aviso) {
        Aviso novoAviso = avisoService.salvarAviso(aviso);
        return ResponseEntity.ok(novoAviso);
    }
}