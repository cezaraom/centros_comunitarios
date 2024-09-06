package com.phoebustecnologia.centros_comunitarios_api.controller;

import com.phoebustecnologia.centros_comunitarios_api.model.CentroComunitario;
import com.phoebustecnologia.centros_comunitarios_api.service.CentroComunitarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CentroComunitarioControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CentroComunitarioController centroComunitarioController;

    @Mock
    private CentroComunitarioService centroComunitarioService;

    private CentroComunitario centro;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(centroComunitarioController).build();

        centro = new CentroComunitario();
        centro.setId("1");
        centro.setNome("Centro A");
        centro.setEndereco("Rua 123");
        centro.setLocalizacao("Cidade Y");
        centro.setCapacidadeMaxima(200);
        centro.setOcupacaoAtual(150);
    }


    @Test
    public void testAdicionarCentro() throws Exception {
        when(centroComunitarioService.adicionarCentro(any(CentroComunitario.class))).thenReturn(centro);

        mockMvc.perform(post("/api/centros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Centro A\", \"endereco\": \"Rua 123\", \"localizacao\": \"Cidade Y\", \"capacidadeMaxima\": 200, \"ocupacaoAtual\": 150 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Centro A"));
    }


    @Test
    public void testListarTodos() throws Exception {
        when(centroComunitarioService.listarTodos()).thenReturn(List.of(centro));

        mockMvc.perform(get("/api/centros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Centro A"));
    }


    @Test
    public void testAtualizarOcupacao() throws Exception {
        when(centroComunitarioService.atualizarOcupacao(anyString(), anyInt())).thenReturn(centro);

        mockMvc.perform(put("/api/centros/1/ocupacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"novaOcupacao\": 180 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ocupacaoAtual").value(180));
    }
}
