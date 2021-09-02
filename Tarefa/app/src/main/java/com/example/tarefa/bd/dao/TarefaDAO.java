package com.example.tarefa.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tarefa.model.Tarefa;

import java.util.List;

@Dao
public interface TarefaDAO {

    @Insert
    void salva(Tarefa tarefa);

    @Query("SELECT * FROM tarefa")
    List<Tarefa> todos();

    @Delete
    void remove(Tarefa tarefa);

    @Update
    void edita(Tarefa tarefa);
}
