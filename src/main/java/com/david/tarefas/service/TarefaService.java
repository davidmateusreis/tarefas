package com.david.tarefas.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.david.tarefas.model.Tarefa;
import com.david.tarefas.repository.TarefaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TarefaService {
    
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

    public ResponseEntity<Tarefa> atualizarTarefaPeloId(Tarefa tarefa, Long id) {
        return tarefaRepository.findById(id)
        .map(tarefaParaAtualizar -> {
            tarefaParaAtualizar.setTitulo(tarefa.getTitulo());
            tarefaParaAtualizar.setDescricao(tarefa.getDescricao());
            tarefaParaAtualizar.setDataLimite(tarefa.getDataLimite());
            Tarefa atualizada = tarefaRepository.save(tarefaParaAtualizar);
            return ResponseEntity.ok().body(atualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> apagarPeloId(Long id) {
        return tarefaRepository.findById(id)
        .map(tarefaParaApagar -> {
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
