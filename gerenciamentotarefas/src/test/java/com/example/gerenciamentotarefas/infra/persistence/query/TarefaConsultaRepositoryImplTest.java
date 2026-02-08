package com.example.gerenciamentotarefas.infra.persistence.query;

import com.example.gerenciamentotarefas.domain.model.PrioridadeTarefa;
import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import com.example.gerenciamentotarefas.domain.model.Tarefa;
import com.example.gerenciamentotarefas.domain.repository.query.TarefaConsultaRepository;
import com.example.gerenciamentotarefas.infra.persistence.TarefaJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TarefaConsultaRepositoryImpl.class)
class TarefaConsultaRepositoryImplTest {

    @Autowired
    private TarefaJpaRepository tarefaJpaRepository;

    @Autowired
    private TarefaConsultaRepository tarefaConsultaRepository;

    @Test
    void filtraPorStatusEPrioridade() {
        tarefaJpaRepository.saveAndFlush(criarTarefa("A", StatusTarefa.PENDENTE, PrioridadeTarefa.ALTA));
        tarefaJpaRepository.saveAndFlush(criarTarefa("B", StatusTarefa.CONCLUIDA, PrioridadeTarefa.ALTA));
        tarefaJpaRepository.saveAndFlush(criarTarefa("C", StatusTarefa.PENDENTE, PrioridadeTarefa.BAIXA));

        var resultado = tarefaConsultaRepository.buscar(null, StatusTarefa.PENDENTE, PrioridadeTarefa.ALTA, 0, 10);

        assertThat(resultado.getContent()).hasSize(1);
        assertThat(resultado.getContent().get(0).getTitulo()).isEqualTo("A");
    }

    private Tarefa criarTarefa(String titulo, StatusTarefa status, PrioridadeTarefa prioridade) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao("Descricao");
        tarefa.setStatus(status);
        tarefa.setPrioridade(prioridade);
        return tarefa;
    }
}
