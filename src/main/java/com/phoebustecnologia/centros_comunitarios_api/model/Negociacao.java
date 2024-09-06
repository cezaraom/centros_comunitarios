package com.phoebustecnologia.centros_comunitarios_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "negociacoes")
@Data
public class Negociacao {
    @Id
    private String id;
    private String centroOrigemId;
    private String centroDestinoId;
    private List<Recurso> recursosOrigem;
    private List<Recurso> recursosDestino;
    private LocalDateTime data;
}
