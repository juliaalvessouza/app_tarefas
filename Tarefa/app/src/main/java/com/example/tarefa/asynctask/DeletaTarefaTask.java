package com.example.tarefa.asynctask;

import android.os.AsyncTask;

import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;

public class DeletaTarefaTask extends AsyncTask {

    private final TarefaDAO dao;
    private final Tarefa tarefa;

    public DeletaTarefaTask(TarefaDAO dao, Tarefa tarefa) {
        this.dao = dao;
        this.tarefa = tarefa;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        dao.remove(tarefa);
        return null;
    }
}
