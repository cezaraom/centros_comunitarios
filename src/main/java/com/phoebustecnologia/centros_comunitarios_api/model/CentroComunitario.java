package com.phoebustecnologia.centros_comunitarios_api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "centros_comunitarios")
@Getter
@Setter

public class CentroComunitario {
    @Id
    private String id;
    private String nome;
    private String endereco;
    private String localizacao;
    private int capacidadeMaxima;
    private int ocupacaoAtual;
    private List<Recurso> recursos;


}
