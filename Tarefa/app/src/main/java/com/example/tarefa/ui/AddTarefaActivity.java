package com.example.tarefa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefa.R;
import com.example.tarefa.asynctask.EditaTarefaTask;
import com.example.tarefa.asynctask.SalvaTarefaTask;
import com.example.tarefa.bd.TarefaBD;
import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;

import static com.example.tarefa.ui.MainActivity.PUT_TAREFA;

public class AddTarefaActivity extends AppCompatActivity {

    private static final String TITULO_TOOLBAR = "Adicionar Tarefa";
    private static final String TITULO_TOOLBAR_EDITA = "Editar Tarefa";
    private EditText field_title, field_description;
    private TarefaDAO dao;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);
        dao = TarefaBD.getInstance(this).getTarefaDAO();
        componentsView();
        dadosTarefa();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if( item.getItemId() == R.id.menu_done){
           finalizaFormulario();
       }
        return super.onOptionsItemSelected(item);
    }

    private void dadosTarefa() {
        Intent dados = getIntent();
        if(dados.hasExtra(PUT_TAREFA)){
            setTitle(TITULO_TOOLBAR_EDITA);
            tarefa = (Tarefa) dados.getSerializableExtra(PUT_TAREFA);
            preencheCampos();
        }else{
            setTitle(TITULO_TOOLBAR);
            tarefa = new Tarefa();
        }
    }

    private void preencheCampos() {
        field_title.setText(tarefa.getTitulo());
        field_description.setText(tarefa.getDescricao());
    }

    private void finalizaFormulario() {
        getTarefa();
        if(tarefa.idValido()){
           new EditaTarefaTask(dao, tarefa).execute();
        }else {
            new SalvaTarefaTask(dao, tarefa).execute();
        }
        finish();
    }

    private void getTarefa() {
        String title = field_title.getText().toString();
        String description = field_description.getText().toString();
        tarefa.setTitulo(title);
        tarefa.setDescricao(description);
    }

    private void componentsView() {
        field_title = findViewById(R.id.field_title);
        field_description= findViewById(R.id.field_description);
    }
}