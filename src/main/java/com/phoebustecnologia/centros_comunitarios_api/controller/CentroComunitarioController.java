package com.phoebustecnologia.centros_comunitarios_api.controller;

import com.phoebustecnologia.centros_comunitarios_api.model.CentroComunitario;
import com.phoebustecnologia.centros_comunitarios_api.service.CentroComunitarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centros")
public class CentroComunitarioController {

    private final CentroComunitarioService service;

    public CentroComunitarioController(CentroComunitarioService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<CentroComunitario> adicionarCentro(@RequestBody CentroComunitario centro) {
        return ResponseEntity.ok(service.adicionarCentro(centro));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<CentroComunitario>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }


    @GetMapping("/relatorios/ocupacao")
    public ResponseEntity<List<CentroComunitario>> listarCentrosComOcupacaoMaiorQue(@RequestParam int percentual) {
        return ResponseEntity.ok(service.listarCentrosComOcupacaoMaiorQue(percentual));
    }


    @PutMapping("/{id}/ocupacao")
    public ResponseEntity<CentroComunitario> atualizarOcupacao(@PathVariable String id, @RequestParam int novaOcupacao) {
        return ResponseEntity.ok(service.atualizarOcupacao(id, novaOcupacao));
    }


}
