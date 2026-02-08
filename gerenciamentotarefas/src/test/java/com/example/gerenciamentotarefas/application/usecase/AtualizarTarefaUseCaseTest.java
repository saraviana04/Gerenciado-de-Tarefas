package com.example.gerenciamentotarefas.application.usecase;

import com.example.gerenciamentotarefas.domain.model.PrioridadeTarefa;
import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.infra.persistence.TarefaJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AtualizarTarefaUseCase.class)
class AtualizarTarefaUseCaseTest {

    @Autowired
    private TarefaJpaRepository tarefaJpaRepository;

    @Autowired
    private AtualizarTarefaUseCase atualizarTarefaUseCase;

    @Test
    void atualizaDadosDaTarefa() {
        Tarefa original = new Tarefa();
        original.setTitulo("Titulo");
        original.setDescricao("Descricao");
        original.setStatus(StatusTarefa.PENDENTE);
        original.setPrioridade(PrioridadeTarefa.MEDIA);
        original = tarefaJpaRepository.saveAndFlush(original);

        Tarefa novosDados = new Tarefa();
        novosDados.setTitulo("Novo titulo");
        novosDados.setDescricao("Nova descricao");
        novosDados.setStatus(StatusTarefa.EM_ANDAMENTO);
        novosDados.setPrioridade(PrioridadeTarefa.ALTA);

        var atualizado = atualizarTarefaUseCase.executar(original.getId(), novosDados);

        assertThat(atualizado).isPresent();
        assertThat(atualizado.get().getTitulo()).isEqualTo("Novo titulo");
        assertThat(atualizado.get().getStatus()).isEqualTo(StatusTarefa.EM_ANDAMENTO);
    }
}
