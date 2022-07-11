package com.david.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.david.tarefas.model.Tarefa;
import com.david.tarefas.service.TarefaService;

@RestController
@RequestMapping("/api/v1")
public class TarefaController {
    
    @Autowired
    TarefaService tarefaService;

    @PostMapping("/tarefas")
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @GetMapping("/tarefas")
    @ResponseStatus(HttpStatus.OK)
    public List<Tarefa> buscarTodasTarefas() {
        return tarefaService.listarTodasTarefas();
    }

    @GetMapping("/tarefas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tarefa> buscarTarefaPeloId(@PathVariable (value = "id") Long id) {
        return tarefaService.encontrarTarefaPeloId(id);
    }
}
