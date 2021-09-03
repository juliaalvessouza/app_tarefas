package com.example.tarefa.asynctask;

import android.os.AsyncTask;

import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;

public class EditaTarefaTask extends AsyncTask {

    private TarefaDAO dao;
    private Tarefa tarefa;

    public EditaTarefaTask(TarefaDAO dao, Tarefa tarefa) {
        this.dao = dao;
        this.tarefa = tarefa;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        dao.edita(tarefa);
        return null;
    }
}
