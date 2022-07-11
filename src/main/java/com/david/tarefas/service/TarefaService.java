package com.david.tarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.david.tarefas.model.Tarefa;
import com.david.tarefas.repository.TarefaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public ResponseEntity<Tarefa> encontrarTarefaPeloId(Long id) {
        return tarefaRepository.findById(id)
        .map(tarefa -> ResponseEntity.ok().body(tarefa))
        .orElse(ResponseEntity.notFound().build());
    }
}
