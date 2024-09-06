package com.phoebustecnologia.centros_comunitarios_api.service;

import com.phoebustecnologia.centros_comunitarios_api.model.CentroComunitario;
import com.phoebustecnologia.centros_comunitarios_api.repository.CentroComunitarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CentroComunitarioServiceTest {

    @InjectMocks
    private CentroComunitarioService centroComunitarioService;

    @Mock
    private CentroComunitarioRepository centroComunitarioRepository;

    private CentroComunitario centro;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        centro = new CentroComunitario();
        centro.setId("1");
        centro.setNome("Centro A");
        centro.setEndereco("Rua 123");
        centro.setLocalizacao("Cidade Y");
        centro.setCapacidadeMaxima(200);
        centro.setOcupacaoAtual(150);
    }

    // Teste para adicionar um centro
    @Test
    public void testAdicionarCentro() {
        when(centroComunitarioRepository.save(centro)).thenReturn(centro);

        CentroComunitario resultado = centroComunitarioService.adicionarCentro(centro);

        assertNotNull(resultado);
        assertEquals("Centro A", resultado.getNome());
        verify(centroComunitarioRepository, times(1)).save(centro);
    }

    // Teste para atualizar ocupação
    @Test
    public void testAtualizarOcupacao() {
        when(centroComunitarioRepository.findById("1")).thenReturn(Optional.of(centro));

        CentroComunitario atualizado = centroComunitarioService.atualizarOcupacao("1", 180);

        assertEquals(180, atualizado.getOcupacaoAtual());
        verify(centroComunitarioRepository, times(1)).save(centro);
    }

    // Teste para listar centros com ocupação maior que 90%
    @Test
    public void testListarCentrosComOcupacaoMaiorQue90() {
        List<CentroComunitario> centros = List.of(centro);
        when(centroComunitarioRepository.findAll()).thenReturn(centros);

        List<CentroComunitario> resultado = centroComunitarioService.listarCentrosComOcupacaoMaiorQue(90);

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getOcupacaoAtual() > (centro.getCapacidadeMaxima() * 0.9));
    }

    // Teste para listar todos os centros
    @Test
    public void testListarTodos() {
        List<CentroComunitario> centros = List.of(centro);
        when(centroComunitarioRepository.findAll()).thenReturn(centros);

        List<CentroComunitario> resultado = centroComunitarioService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}
