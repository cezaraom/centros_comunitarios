package com.phoebustecnologia.centros_comunitarios_api.service;

import com.phoebustecnologia.centros_comunitarios_api.model.CentroComunitario;
import com.phoebustecnologia.centros_comunitarios_api.model.Recurso;
import com.phoebustecnologia.centros_comunitarios_api.repository.CentroComunitarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroComunitarioService {

    @Autowired
    private CentroComunitarioRepository repository;

    public CentroComunitario adicionarCentro(CentroComunitario centro) {
        return repository.save(centro);
    }

    // Atualiza a ocupação e gera uma notificação, caso atinja a capacidade máxima
    public CentroComunitario atualizarOcupacao(String id, int novaOcupacao) {
        CentroComunitario centro = repository.findById(id).orElseThrow();

        centro.setOcupacaoAtual(novaOcupacao);

        // Verifica se a ocupação atual é maior ou igual à capacidade máxima
        if (centro.getOcupacaoAtual() >= centro.getCapacidadeMaxima()) {
            // Gera notificação de capacidade máxima atingida
            System.out.println("Centro Comunitário atingiu capacidade máxima! Notificar outros centros.");

        }

        return repository.save(centro);
    }

    // Lista centros com ocupação maior que um determinado percentual
    public List<CentroComunitario> listarCentrosComOcupacaoMaiorQue(int percentual) {
        return repository.findAll().stream()
                .filter(c -> ((c.getOcupacaoAtual() * 100) / c.getCapacidadeMaxima()) > percentual)
                .toList();
    }

    // Lista todos os centros comunitários
    public List<CentroComunitario> listarTodos() {
        return repository.findAll();
    }

    // Lista centros com ocupação acima de 90%
    public List<CentroComunitario> listarCentrosComOcupacaoAlta() {
        return repository.findAll().stream()
                .filter(c -> c.getOcupacaoAtual() >= (c.getCapacidadeMaxima() * 0.9))
                .toList();
    }

    // Calcula média de recursos por centro
    public double calcularMediaRecursos(String tipoRecurso) {
        List<CentroComunitario> centros = repository.findAll();
        int totalRecursos = 0;
        int totalCentros = centros.size();

        for (CentroComunitario centro : centros) {
            // Percorre a lista de recursos para encontrar o tipo solicitado
            for (Recurso recurso : centro.getRecursos()) {
                if (recurso.getTipo().equals(tipoRecurso)) {
                    totalRecursos += recurso.getQuantidade();
                    break;
                }
            }
        }

        return totalCentros > 0 ? (double) totalRecursos / totalCentros : 0.0;
    }
}
