package com.marlon.appfilmes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "AppFilmes";
    private static final int VERSAO = 1;

    public Conexao(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS filmes (  " +
                "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,  " +
                "  nome TEXT NOT NULL , " +
                "  genero TEXT, " +
                "  nacionalidade TEXT, " +
                "  categoria TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}