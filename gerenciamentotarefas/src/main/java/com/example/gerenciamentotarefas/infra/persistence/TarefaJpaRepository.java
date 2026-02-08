package com.example.gerenciamentotarefas.infra.persistence;

import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.model.PrioridadeTarefa;
import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import com.example.gerenciamentotarefas.domain.repository.TarefaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TarefaJpaRepository extends JpaRepository<Tarefa, Long>, TarefaRepository {
    Page<Tarefa> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    Page<Tarefa> findByStatus(StatusTarefa status, Pageable pageable);

    Page<Tarefa> findByPrioridade(PrioridadeTarefa prioridade, Pageable pageable);

    Page<Tarefa> findByStatusAndPrioridade(StatusTarefa status, PrioridadeTarefa prioridade, Pageable pageable);

    Page<Tarefa> findByTituloContainingIgnoreCaseAndStatus(String titulo, StatusTarefa status, Pageable pageable);

    Page<Tarefa> findByTituloContainingIgnoreCaseAndPrioridade(String titulo, PrioridadeTarefa prioridade, Pageable pageable);

    Page<Tarefa> findByTituloContainingIgnoreCaseAndStatusAndPrioridade(
            String titulo,
            StatusTarefa status,
            PrioridadeTarefa prioridade,
            Pageable pageable
    );
}
