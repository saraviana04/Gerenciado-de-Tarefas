package com.example.gerenciamentotarefas.application.usecase;

import com.example.gerenciamentotarefas.domain.repository.TarefaRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirTarefaUseCase {

    private final TarefaRepository tarefaRepository;

    public ExcluirTarefaUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public boolean executar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            return false;
        }
        tarefaRepository.deleteById(id);
        return true;
    }
}
