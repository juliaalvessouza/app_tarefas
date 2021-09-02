package com.example.tarefa.dao;

import com.example.tarefa.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private final static List<Tarefa> tarefas = new ArrayList<>();
    private static int ids = 1;

    public void salva(Tarefa tarefa) {
        tarefa.setId(ids);
        tarefas.add(tarefa);
        ids++;
    }

    public void edita(Tarefa tarefa){
        Tarefa tarefaEncontrada = getTarefaID(tarefa);
        if(tarefaEncontrada != null){
            int posicaoTarefa = tarefas.indexOf(tarefaEncontrada);
            tarefas.set(posicaoTarefa, tarefa);
        }
    }

    private Tarefa getTarefaID(Tarefa tarefa) {
        Tarefa tarefaEncontrada = null;
        for (Tarefa t:
                tarefas) {
            if (t.getId() == tarefa.getId()) {
                tarefaEncontrada = t;
            }
        }
        return tarefaEncontrada;
    }

    public List<Tarefa> todos() {
        return new ArrayList<>(tarefas);
    }

    public void remove(Tarefa tarefa) {
        Tarefa tarefaID = getTarefaID(tarefa);
        if(tarefaID != null){
            tarefas.remove(tarefaID);
        }
    }
}
