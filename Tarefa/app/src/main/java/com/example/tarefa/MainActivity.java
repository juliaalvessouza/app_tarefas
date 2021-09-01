package com.example.tarefa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tarefa.dao.TarefaDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {

    public static final String PUT_TAREFA = "tarefa";
    private TarefaDAO dao;
    private ArrayAdapter<Tarefa> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new TarefaDAO();
        btn_add_tarefa();
        configList();
       for(int i = 0; i<10; i++){
            dao.salva(new Tarefa("titulo" + i, "descrição" + i));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_remove){
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
            Tarefa tarefaSelecionada = adapter.getItem(menuInfo.position);
            remove(tarefaSelecionada);
        }

        return super.onContextItemSelected(item);
    }

    private void atualizaList() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configList() {
        ListView list = findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        cliqueList(list);
        registerForContextMenu(list);
    }

    private void remove(Tarefa tarefa) {
        dao.remove(tarefa);
        adapter.remove(tarefa);
    }

    private void cliqueList(ListView list) {
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefaSelecionada = (Tarefa) parent.getItemAtPosition(position);
                intentParaEdicao(tarefaSelecionada);
            }
        });
    }

    private void intentParaEdicao(Tarefa tarefaSelecionada) {
        Intent in = new Intent(MainActivity.this, AddTarefaActivity.class);
        in.putExtra(PUT_TAREFA, tarefaSelecionada);
        startActivity(in);
    }

    private void btn_add_tarefa() {
        FloatingActionButton fab_add;
        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTarefaActivity.class);
                startActivity(i);
            }
        });
    }
}
