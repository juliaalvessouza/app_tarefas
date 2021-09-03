package com.example.tarefa.asynctask;

import android.os.AsyncTask;

import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;
import com.example.tarefa.ui.adapter.ListaTarefaAdapter;

import java.util.List;

public class BuscaTodasTarefasTask extends AsyncTask {

    private TarefaDAO dao;
    private ListaTarefaAdapter adapter;

    public BuscaTodasTarefasTask(TarefaDAO dao, ListaTarefaAdapter adapter){
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        List<Tarefa> tarefas = dao.todos();
        return tarefas;
    }

    @Override
    protected void onPostExecute(Object tarefas) {
        super.onPostExecute(tarefas);
        adapter.atualiza((List<Tarefa>) tarefas);
    }
}
