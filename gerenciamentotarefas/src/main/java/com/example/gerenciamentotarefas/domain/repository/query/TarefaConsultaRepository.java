package com.example.gerenciamentotarefas.domain.repository.query;

import com.example.gerenciamentotarefas.domain.common.PageResult;
import com.example.gerenciamentotarefas.domain.model.PrioridadeTarefa;
import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import com.example.gerenciamentotarefas.domain.model.Tarefa;

public interface TarefaConsultaRepository {

    PageResult<Tarefa> buscar(String titulo, StatusTarefa status, PrioridadeTarefa prioridade, int page, int size);
}
