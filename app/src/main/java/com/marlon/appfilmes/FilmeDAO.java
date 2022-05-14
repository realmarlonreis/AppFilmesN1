package com.marlon.appfilmes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    public static void inserir(Context context, Filme filme){
        ContentValues values = new ContentValues();
        values.put("nome", filme.getNome());
        values.put("genero", filme.getGenero());
        values.put("categoria", filme.getCategoria());
        values.put("nacionalidade", filme.getNacionalidade());

        Conexao conexao = new Conexao(context);
        SQLiteDatabase db = conexao.getWritableDatabase();

        db.insert("filmes", null , values);
    }

    public static void editar(Context context, Filme filme){
        ContentValues values = new ContentValues();
        values.put("nome", filme.getNome());
        values.put("genero", filme.getGenero());
        values.put("categoria", filme.getCategoria());
        values.put("nacionalidade", filme.getNacionalidade());

        Conexao conexao = new Conexao(context);
        SQLiteDatabase db = conexao.getWritableDatabase();

        db.update("filmes", values, " id = " + filme.getId(), null);
    }

    public static void excluir(Context context, int idFilme){

        Conexao conexao = new Conexao(context);
        SQLiteDatabase db = conexao.getWritableDatabase();

        db.delete("filmes", " id = " + idFilme, null);
    }

        public static List<Filme> getFilmes(Context context){
        List<Filme> list = new ArrayList<>();

        Conexao conexao = new Conexao(context);
        SQLiteDatabase db = conexao.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM filmes ORDER BY nome ", null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Filme filme = new Filme();
                filme.setId(cursor.getInt(0));
                filme.setNome(cursor.getString(1));
                filme.setGenero(cursor.getString(2));
                filme.setCategoria(cursor.getString(3));
                filme.setNacionalidade(cursor.getString(4));
                list.add(filme);

            }while(cursor.moveToNext());
        }
        return list;
    }

    public static Filme getFilmeById(Context context, int idFilme){

        Conexao conexao = new Conexao(context);
        SQLiteDatabase db = conexao.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM filmes WHERE id =  " + idFilme, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            Filme filme = new Filme();
            filme.setId(cursor.getInt(0));
            filme.setNome(cursor.getString(1));
            filme.setGenero(cursor.getString(2));
            filme.setCategoria(cursor.getString(3));
            filme.setNacionalidade(cursor.getString(4));

            return filme;

        }else{
            return null;
        }
    }
}