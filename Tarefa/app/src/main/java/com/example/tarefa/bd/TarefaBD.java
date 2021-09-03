package com.example.tarefa.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.tarefa.bd.converter.ConverterData;
import com.example.tarefa.bd.dao.TarefaDAO;
import com.example.tarefa.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 2, exportSchema = false)
@TypeConverters({ConverterData.class})
public abstract class TarefaBD extends RoomDatabase {

    private static final String NOME_BD = "tarefa.bd";

    public abstract TarefaDAO getTarefaDAO();

    public static TarefaBD getInstance(Context context){
        return Room.databaseBuilder(context, TarefaBD.class, NOME_BD)
                .build();
    }
}
