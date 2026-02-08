package com.example.gerenciamentotarefas.application.usecase;

import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarTarefaUseCase {

    private final TarefaRepository tarefaRepository;

    public AtualizarTarefaUseCase(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Optional<Tarefa> executar(Long id, Tarefa dados) {
        return tarefaRepository.findById(id)
                .map(existente -> {
                    existente.setTitulo(dados.getTitulo());
                    existente.setDescricao(dados.getDescricao());
                    existente.setStatus(dados.getStatus());
                    existente.setPrioridade(dados.getPrioridade());
                    return tarefaRepository.save(existente);
                });
    }
}
