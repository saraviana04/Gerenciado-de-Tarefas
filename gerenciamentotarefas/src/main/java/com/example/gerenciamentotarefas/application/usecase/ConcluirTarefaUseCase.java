package com.example.gerenciamentotarefas.application.usecase;

import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConcluirTarefaUseCase {

    private final TarefaRepository tarefaRepository;

    public ConcluirTarefaUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Optional<Tarefa> executar(Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setStatus(StatusTarefa.CONCLUIDA);
                    return tarefaRepository.save(tarefa);
                });
    }
}
