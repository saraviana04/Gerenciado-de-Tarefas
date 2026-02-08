package com.example.gerenciamentotarefas.application.usecase;

import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.repository.TarefaRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarTarefaUseCase {

    private final TarefaRepository tarefaRepository;

    public CriarTarefaUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa executar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
}
