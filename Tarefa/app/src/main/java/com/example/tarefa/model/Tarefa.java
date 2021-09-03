package com.example.tarefa.model;

import android.annotation.SuppressLint;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String descricao;
    private Calendar data = Calendar.getInstance();

    public Tarefa() {

    }

    @Ignore
    public Tarefa(String title, String description) {
        this.titulo = title;
        this.descricao = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getData() { return data; }

    public void setData(Calendar data) { this.data = data; }

    public boolean idValido() {
        return id > 0;
    }

    public String formatData(){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        return simpleDateFormat.format(data.getTime());
    }
}

