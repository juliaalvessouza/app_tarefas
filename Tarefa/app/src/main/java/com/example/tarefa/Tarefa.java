package com.example.tarefa;

public class Tarefa {

    private final String title;
    private final String description;

    public Tarefa(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }
}
