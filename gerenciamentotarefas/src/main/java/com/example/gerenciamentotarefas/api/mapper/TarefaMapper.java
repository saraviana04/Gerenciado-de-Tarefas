package com.example.gerenciamentotarefas.api.mapper;

import com.example.gerenciamentotarefas.api.dto.TarefaRequest;
import com.example.gerenciamentotarefas.api.dto.TarefaResponse;
import com.example.gerenciamentotarefas.domain.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public Tarefa toEntity(TarefaRequest request) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setStatus(request.getStatus());
        tarefa.setPrioridade(request.getPrioridade());
        return tarefa;
    }

    public TarefaResponse toResponse(Tarefa tarefa) {
        return new TarefaResponse(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getPrioridade()
        );
    }
}
