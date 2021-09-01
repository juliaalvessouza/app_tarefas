package com.example.tarefa.dao;

import com.example.tarefa.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private final static List<Tarefa> tarefas = new ArrayList<>();

    public void salva(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public List<Tarefa> todos() {
        return new ArrayList<>(tarefas);
    }
}
