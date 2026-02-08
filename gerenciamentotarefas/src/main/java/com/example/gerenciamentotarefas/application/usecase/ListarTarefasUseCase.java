package com.example.gerenciamentotarefas.application.usecase;

import com.example.gerenciamentotarefas.domain.common.PageResult;
import com.example.gerenciamentotarefas.domain.model.PrioridadeTarefa;
import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.repository.query.TarefaConsultaRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarTarefasUseCase {

    private final TarefaConsultaRepository tarefaConsultaRepository;

    public ListarTarefasUseCase(TarefaConsultaRepository tarefaConsultaRepository) {
        this.tarefaConsultaRepository = tarefaConsultaRepository;
    }

    public PageResult<Tarefa> executar(String titulo, StatusTarefa status, PrioridadeTarefa prioridade, int page, int size) {
        return tarefaConsultaRepository.buscar(titulo, status, prioridade, page, size);
    }
}
