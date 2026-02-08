package com.example.gerenciamentotarefas.infra.persistence.query;

import com.example.gerenciamentotarefas.domain.common.PageResult;
import com.example.gerenciamentotarefas.domain.model.PrioridadeTarefa;
import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.repository.query.TarefaConsultaRepository;
import com.example.gerenciamentotarefas.infra.persistence.TarefaJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class TarefaConsultaRepositoryImpl implements TarefaConsultaRepository {

    private final TarefaJpaRepository tarefaJpaRepository;

    public TarefaConsultaRepositoryImpl(TarefaJpaRepository tarefaJpaRepository) {
        this.tarefaJpaRepository = tarefaJpaRepository;
    }

    @Override
    public PageResult<Tarefa> buscar(String titulo, StatusTarefa status, PrioridadeTarefa prioridade, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        boolean hasTitulo = titulo != null && !titulo.isBlank();
        boolean hasStatus = status != null;
        boolean hasPrioridade = prioridade != null;

        Page<Tarefa> resultado;
        if (hasTitulo && hasStatus && hasPrioridade) {
            resultado = tarefaJpaRepository.findByTituloContainingIgnoreCaseAndStatusAndPrioridade(
                    titulo,
                    status,
                    prioridade,
                    pageable
            );
        } else if (hasTitulo && hasStatus) {
            resultado = tarefaJpaRepository.findByTituloContainingIgnoreCaseAndStatus(titulo, status, pageable);
        } else if (hasTitulo && hasPrioridade) {
            resultado = tarefaJpaRepository.findByTituloContainingIgnoreCaseAndPrioridade(titulo, prioridade, pageable);
        } else if (hasStatus && hasPrioridade) {
            resultado = tarefaJpaRepository.findByStatusAndPrioridade(status, prioridade, pageable);
        } else if (hasTitulo) {
            resultado = tarefaJpaRepository.findByTituloContainingIgnoreCase(titulo, pageable);
        } else if (hasStatus) {
            resultado = tarefaJpaRepository.findByStatus(status, pageable);
        } else if (hasPrioridade) {
            resultado = tarefaJpaRepository.findByPrioridade(prioridade, pageable);
        } else {
            resultado = tarefaJpaRepository.findAll(pageable);
        }

        return new PageResult<>(
                resultado.getContent(),
                resultado.getNumber(),
                resultado.getSize(),
                resultado.getTotalElements(),
                resultado.getTotalPages()
        );
    }
}
