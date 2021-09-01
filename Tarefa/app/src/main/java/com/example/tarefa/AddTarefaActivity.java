package com.example.tarefa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefa.dao.TarefaDAO;

public class AddTarefaActivity extends AppCompatActivity {

    public static final String TITLE_TOOLBAR = "Adicionar Tarefa";
    private EditText field_title, field_description;
    private Button btn_save;
    private TarefaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);
        componentsView();
        setTitle(TITLE_TOOLBAR);
        dao = new TarefaDAO();
        btn_save();

    }

    private void btn_save() {
        btn_save= findViewById(R.id.button);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa tarefa = getTarefa();
                save_tarefa(tarefa);
            }
        });
    }

    private void save_tarefa(Tarefa tarefa) {
        dao.salva(tarefa);
        finish();
    }

    private Tarefa getTarefa() {
        String title = field_title.getText().toString();
        String description = field_description.getText().toString();
        return new Tarefa(title, description);
    }

    private void componentsView() {
        field_title = findViewById(R.id.field_title);
        field_description= findViewById(R.id.field_description);
    }

}