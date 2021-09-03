package com.example.tarefa.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tarefa.R;
import com.example.tarefa.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class ListaTarefaAdapter extends BaseAdapter {

    private final List<Tarefa> tarefas = new ArrayList<>();
    private final Context context;

    public ListaTarefaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Tarefa getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View inflate = LayoutInflater.from(context)
                .inflate(R.layout.card_tarefa, parent, false);
        Tarefa tarefa = tarefas.get(position);
        setDados(inflate, tarefa);
        return inflate;
    }

    private void setDados(View inflate, Tarefa tarefa) {
        TextView title = inflate.findViewById(R.id.title_card);
        title.setText(tarefa.getTitulo());
        TextView description = inflate.findViewById(R.id.description_card);
        description.setText(tarefa.getDescricao());
        TextView data = inflate.findViewById(R.id.data_card);
        data.setText(tarefa.formatData());
    }

    public void atualiza(List<Tarefa> tarefas){
        this.tarefas.clear();
        this.tarefas.addAll(tarefas);
        notifyDataSetChanged();
    }

    public void remove(Tarefa tarefa) {
        tarefas.remove(tarefa);
        notifyDataSetChanged();
    }
}
