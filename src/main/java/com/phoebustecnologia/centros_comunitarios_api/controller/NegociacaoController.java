package com.phoebustecnologia.centros_comunitarios_api.controller;

import com.phoebustecnologia.centros_comunitarios_api.model.Negociacao;
import com.phoebustecnologia.centros_comunitarios_api.service.NegociacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/negociacoes")
public class NegociacaoController {
    private final NegociacaoService service;

    public NegociacaoController(NegociacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Negociacao> registrarNegociacao(@RequestBody Negociacao negociacao) {
        return ResponseEntity.ok(service.registrarNegociacao(negociacao));
    }

    @GetMapping
    public ResponseEntity<List<Negociacao>> obterNegociacoesPorCentroEPeriodo(
            @RequestParam String centroId,
            @RequestParam String inicio,
            @RequestParam String fim) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);
        return ResponseEntity.ok(service.obterNegociacoesPorCentroEPeriodo(centroId, dataInicio, dataFim));
    }
}
