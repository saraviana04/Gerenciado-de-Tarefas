package com.example.gerenciamentotarefas.domain.repository;

import com.example.gerenciamentotarefas.domain.model.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository {

    List<Tarefa> findAll();

    Optional<Tarefa> findById(Long id);

    Tarefa save(Tarefa tarefa);

    boolean existsById(Long id);

    void deleteById(Long id);
}
