package com.example.tarefa.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1, exportSchema = false)
public abstract class TarefaBD extends RoomDatabase {

    private static final String nome_bd = "tarefa.bd";

    public abstract TarefaDAO getTarefaDAO();

    public static TarefaBD getInstance(Context context){
        return Room.databaseBuilder(context, TarefaBD.class, nome_bd)
                .allowMainThreadQueries()
                .build();
    }
}
