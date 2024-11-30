package com.high.end.academia.academia.service;
import com.high.end.academia.academia.model.Aviso;
import com.high.end.academia.academia.repository.AvisoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class AvisoService {
    private final AvisoRepository avisoRepository;

    public AvisoService(AvisoRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    public List<Aviso> getAllAvisos() {
        return avisoRepository.findAll();
    }

    public Aviso salvarAviso(Aviso aviso) {
        aviso.setDataPublicacao(LocalDate.now().toString());
        return avisoRepository.save(aviso);
    }


}