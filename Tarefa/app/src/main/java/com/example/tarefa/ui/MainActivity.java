package com.example.tarefa.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefa.ui.adapter.ListaTarefaAdapter;
import com.example.tarefa.R;
import com.example.tarefa.bd.TarefaBD;
import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.widget.AdapterView.AdapterContextMenuInfo;
import static android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    public static final String PUT_TAREFA = "tarefa";
    private TarefaDAO dao;
    private ListaTarefaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add_tarefa();
        configList();
        dao = TarefaBD.getInstance(this).getTarefaDAO();
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
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        if(item.getItemId() == R.id.menu_remove){
            new AlertDialog.Builder(this)
                    .setTitle("Deletar Tarefa")
                    .setMessage("Deseja deletar a tarefa? ")
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
                            Tarefa tarefaSelecionada = adapter.getItem(menuInfo.position);
                            remove(tarefaSelecionada);
                        }
                    })
                    .setNegativeButton("NÃO", null)
                    .show();
        }

        return super.onContextItemSelected(item);
    }

    private void atualizaList() {
        adapter.atualiza(dao.todos());
    }

    private void configList() {
        ListView list = findViewById(R.id.listview);
        adapter = new ListaTarefaAdapter(this);
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
