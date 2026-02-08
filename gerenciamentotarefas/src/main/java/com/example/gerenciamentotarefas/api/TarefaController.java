package com.example.gerenciamentotarefas.api;

import com.example.gerenciamentotarefas.api.dto.PageResponse;
import com.example.gerenciamentotarefas.api.dto.TarefaRequest;
import com.example.gerenciamentotarefas.api.dto.TarefaResponse;
import com.example.gerenciamentotarefas.api.mapper.TarefaMapper;
import com.example.gerenciamentotarefas.application.usecase.ConcluirTarefaUseCase;
import com.example.gerenciamentotarefas.application.usecase.AtualizarTarefaUseCase;
import com.example.gerenciamentotarefas.application.usecase.BuscarTarefaPorIdUseCase;
import com.example.gerenciamentotarefas.application.usecase.CriarTarefaUseCase;
import com.example.gerenciamentotarefas.application.usecase.ExcluirTarefaUseCase;
import com.example.gerenciamentotarefas.application.usecase.ListarTarefasUseCase;
import com.example.gerenciamentotarefas.domain.model.PrioridadeTarefa;
import com.example.gerenciamentotarefas.domain.model.StatusTarefa;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final ListarTarefasUseCase listarTarefasUseCase;
    private final BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase;
    private final CriarTarefaUseCase criarTarefaUseCase;
    private final AtualizarTarefaUseCase atualizarTarefaUseCase;
    private final ExcluirTarefaUseCase excluirTarefaUseCase;
    private final ConcluirTarefaUseCase concluirTarefaUseCase;
    private final TarefaMapper tarefaMapper;

    public TarefaController(
            ListarTarefasUseCase listarTarefasUseCase,
            BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase,
            CriarTarefaUseCase criarTarefaUseCase,
            AtualizarTarefaUseCase atualizarTarefaUseCase,
            ExcluirTarefaUseCase excluirTarefaUseCase,
            ConcluirTarefaUseCase concluirTarefaUseCase,
            TarefaMapper tarefaMapper
    ) {
        this.listarTarefasUseCase = listarTarefasUseCase;
        this.buscarTarefaPorIdUseCase = buscarTarefaPorIdUseCase;
        this.criarTarefaUseCase = criarTarefaUseCase;
        this.atualizarTarefaUseCase = atualizarTarefaUseCase;
        this.excluirTarefaUseCase = excluirTarefaUseCase;
        this.concluirTarefaUseCase = concluirTarefaUseCase;
        this.tarefaMapper = tarefaMapper;
    }

    @GetMapping
    public PageResponse<TarefaResponse> listarTarefas(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) StatusTarefa status,
            @RequestParam(required = false) PrioridadeTarefa prioridade,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var resultado = listarTarefasUseCase.executar(titulo, status, prioridade, page, size);
        List<TarefaResponse> content = resultado.getContent()
                .stream()
                .map(tarefaMapper::toResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                resultado.getPage(),
                resultado.getSize(),
                resultado.getTotalElements(),
                resultado.getTotalPages()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscarTarefaPorId(@PathVariable Long id) {
        return buscarTarefaPorIdUseCase.executar(id)
                .map(value -> ResponseEntity.ok(tarefaMapper.toResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> criarTarefa(@Valid @RequestBody TarefaRequest request) {
        var novaTarefa = criarTarefaUseCase.executar(tarefaMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaMapper.toResponse(novaTarefa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TarefaRequest request) {
        return atualizarTarefaUseCase.executar(id, tarefaMapper.toEntity(request))
                .map(value -> ResponseEntity.ok(tarefaMapper.toResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        boolean excluida = excluirTarefaUseCase.executar(id);
        if (!excluida) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponse> concluirTarefa(@PathVariable Long id) {
        return concluirTarefaUseCase.executar(id)
                .map(value -> ResponseEntity.ok(tarefaMapper.toResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
