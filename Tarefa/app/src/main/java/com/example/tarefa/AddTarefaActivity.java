package com.example.tarefa;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefa.dao.TarefaDAO;

import java.io.Serializable;

import static com.example.tarefa.MainActivity.PUT_TAREFA;

public class AddTarefaActivity extends AppCompatActivity {

    private static final String TITLE_TOOLBAR = "Adicionar Tarefa";
    private static final String TITLE_TOOLBAR_EDITA = "Edita Aluno";
    private EditText field_title, field_description;
    private TarefaDAO dao;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);
        dao = new TarefaDAO();
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
            setTitle(TITLE_TOOLBAR_EDITA);
            tarefa = (Tarefa) dados.getSerializableExtra(PUT_TAREFA);
            preencheCampos();
        }else{
            setTitle(TITLE_TOOLBAR);
            tarefa = new Tarefa();
        }
    }

    private void preencheCampos() {
        field_title.setText(tarefa.getTitle());
        field_description.setText(tarefa.getDescription());
    }

    private void finalizaFormulario() {
        getTarefa();
        if(tarefa.idValido()){
            dao.edita(tarefa);
        }else {
            dao.salva(tarefa);
        }
        finish();
    }

    private void getTarefa() {
        String title = field_title.getText().toString();
        String description = field_description.getText().toString();
        tarefa.setTitle(title);
        tarefa.setDescription(description);
    }

    private void componentsView() {
        field_title = findViewById(R.id.field_title);
        field_description= findViewById(R.id.field_description);
    }
}