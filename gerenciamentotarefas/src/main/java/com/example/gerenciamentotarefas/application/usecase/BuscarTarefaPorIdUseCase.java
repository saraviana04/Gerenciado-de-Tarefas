package com.example.gerenciamentotarefas.application.usecase;

import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarTarefaPorIdUseCase {

    private final TarefaRepository tarefaRepository;

    public BuscarTarefaPorIdUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Optional<Tarefa> executar(Long id) {
        return tarefaRepository.findById(id);
    }
}
