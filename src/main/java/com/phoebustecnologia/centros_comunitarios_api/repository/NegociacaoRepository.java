package com.phoebustecnologia.centros_comunitarios_api.repository;

import com.phoebustecnologia.centros_comunitarios_api.model.Negociacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NegociacaoRepository extends MongoRepository<Negociacao, String> {
    List<Negociacao> findByCentroOrigemIdAndDataBetween(String centroOrigemId, LocalDateTime start, LocalDateTime end);
}
