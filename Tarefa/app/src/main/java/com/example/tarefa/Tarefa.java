package com.example.tarefa;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private int id = 0;
    private  String title;
    private  String description;

    public Tarefa(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Tarefa() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }

    public boolean idValido() {
        return id > 0;
    }
}
