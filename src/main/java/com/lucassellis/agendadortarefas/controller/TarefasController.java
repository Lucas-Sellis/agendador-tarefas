package com.lucassellis.agendadortarefas.controller;

import com.lucassellis.agendadortarefas.business.TarefaService;
import com.lucassellis.agendadortarefas.business.dto.TarefasDTORecord;
import com.lucassellis.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucassellis.agendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
    // essa parte aqui é o swagger "a tal da documentação"
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTORecord> gravarTarefas(@RequestBody TarefasDTORecord dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por Período", description = "Busca tarefas cadastradas por período")
    // essa parte aqui é o swagger "a tal da documentação"
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário  não autorizado")
    public ResponseEntity<List<TarefasDTORecord>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email de usuário",
            description = "Busca tarefas cadastradas por usuário")
    // essa parte aqui é o swagger "a tal da documentação"
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Email  não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário  não autorizado")
    public ResponseEntity<List<TarefasDTORecord>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        List<TarefasDTORecord> tarefas = tarefaService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id",
            description = "Deleta tarefas cadastradas por Id") // essa parte aqui é o swagger "a tal da documentação"
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário  não autorizado")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {

        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();

    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    // essa parte aqui é o swagger "a tal da documentação"
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário  não autorizado")
    public ResponseEntity<TarefasDTORecord> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id) {

        return ResponseEntity.ok(tarefaService.alteraStatus(status, id));
    }




    @PutMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    // essa parte aqui é o swagger "a tal da documentação"
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário  não autorizado")
    public ResponseEntity<TarefasDTORecord> updateTarefas(@RequestBody TarefasDTORecord dto,
                                                @RequestParam("id") String id) {
    return ResponseEntity.ok(tarefaService.updateTarefas(dto, id));
}

}