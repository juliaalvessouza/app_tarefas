package com.example.tarefa.asynctask;

import android.os.AsyncTask;

import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;
import com.example.tarefa.ui.adapter.ListaTarefaAdapter;

import java.util.List;

public class BuscaTodasTarefasTask extends AsyncTask<Void, Void, List<Tarefa>>{

    private final TarefaDAO dao;
    private final ListaTarefaAdapter adapter;

    public BuscaTodasTarefasTask(TarefaDAO dao, ListaTarefaAdapter adapter){
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Tarefa> doInBackground(Void[] objects) {
        List<Tarefa> tarefas = dao.todos();
        return tarefas;
    }

    @Override
    protected void onPostExecute( List<Tarefa> tarefas) {
        super.onPostExecute(tarefas);
        adapter.atualiza(tarefas);
    }
}
