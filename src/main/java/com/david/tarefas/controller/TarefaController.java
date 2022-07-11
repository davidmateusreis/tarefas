package com.david.tarefas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.david.tarefas.model.Tarefa;
import com.david.tarefas.service.TarefaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TarefaController {
    
    TarefaService tarefaService;

    @ApiOperation(value = "Criando uma nova tarefa.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Tarefa criada com sucesso."),
        @ApiResponse(code = 500, message = "Ocorreu um erro ao criar a tarefa, verifique as informações.")
    })

    @PostMapping("/tarefas")
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        log.info("Criando uma nova tarefa com as informações [{}]", tarefa);
        return tarefaService.criarTarefa(tarefa);
    }

    @ApiOperation(value = "Listando todas as tarefas.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Tarefas listadas com sucesso."),
        @ApiResponse(code = 500, message = "Ocorreu um erro ao listar a tarefa.")
    })

    @GetMapping("/tarefas")
    @ResponseStatus(HttpStatus.OK)
    public List<Tarefa> buscarTodasTarefas() {
        log.info("Listando todas as tarefas cadastradas");
        return tarefaService.listarTodasTarefas();
    }

    @ApiOperation(value = "Buscando uma tarefa pelo id.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Tarefa encontrada com sucesso."),
        @ApiResponse(code = 404, message = "Não foi encontrada nenhuma tarefa com esse id.")
    })

    @GetMapping("/tarefas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tarefa> buscarTarefaPeloId(@PathVariable (value = "id") Long id) {
        log.info("Encontrando uma tarefa pelo id [{}]", id);
        return tarefaService.encontrarTarefaPeloId(id);
    }

    @ApiOperation(value = "Atualizando um tarefa.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso."),
        @ApiResponse(code = 404, message = "Não foi possível atualizar a tarefa, tarefa não encontrada.")
    })

    @PutMapping("/tarefas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tarefa> buscarTarefaPeloId(@PathVariable (value = "id") Long id, @RequestBody Tarefa tarefa) {
        log.info("Atualizando a tarefa com id [{}], as novas informações são [{}]", id, tarefa);
        return tarefaService.atualizarTarefaPeloId(tarefa, id);
    }

    @ApiOperation(value = "Apagando uma tarefa.")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Tarefa apagada com sucesso!"),
        @ApiResponse(code = 404, message = "Não foi possível apagar a tarefa, tarefa não encontrada.")
    })

    @DeleteMapping("/tarefas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> apagarTarefaPeloId(@PathVariable (value = "id") Long id) {
        log.info("Apagando a tarefa com id [{}]", id);
        return tarefaService.apagarPeloId(id);
    }
}
