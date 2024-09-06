package com.phoebustecnologia.centros_comunitarios_api.repository;

import com.phoebustecnologia.centros_comunitarios_api.model.CentroComunitario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CentroComunitarioRepository extends MongoRepository<CentroComunitario, String> {

    List<CentroComunitario> findByOcupacaoAtualGreaterThan(int ocupacao);
}
