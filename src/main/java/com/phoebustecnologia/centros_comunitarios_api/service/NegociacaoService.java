package com.phoebustecnologia.centros_comunitarios_api.service;

import com.phoebustecnologia.centros_comunitarios_api.model.Negociacao;
import com.phoebustecnologia.centros_comunitarios_api.repository.NegociacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NegociacaoService {
    private final NegociacaoRepository repository;

    public NegociacaoService(NegociacaoRepository repository) {
        this.repository = repository;
    }

    public Negociacao registrarNegociacao(Negociacao negociacao) {
        return repository.save(negociacao);
    }

    public List<Negociacao> obterNegociacoesPorCentroEPeriodo(String centroId, LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByCentroOrigemIdAndDataBetween(centroId, inicio, fim);
    }
}
