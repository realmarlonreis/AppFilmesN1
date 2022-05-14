package com.marlon.appfilmes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class FormsActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spCategorias, spGeneros;
    private Button btnSalvar;
    private String acao;
    private Filme filme;
    private RadioGroup idGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);

        etNome = findViewById(R.id.etNome);
        spGeneros = findViewById(R.id.spGeneros);
        spCategorias = findViewById(R.id.spCategorias);
        btnSalvar = findViewById(R.id.btnSalvar);
        idGroup = findViewById(R.id.idGroup);

        acao = getIntent().getStringExtra("acao");
        if(acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void carregarFormulario(){
        int id = getIntent().getIntExtra("idFilme", 0);
        filme = FilmeDAO.getFilmeById(this, id);
        etNome.setText(filme.getNome());

        String[] generos = getResources().getStringArray(R.array.generos);
        for (int i = 1; i < generos.length ;i++){
            if(filme.getGenero().equals(generos[i])){
                spGeneros.setSelection(i);
                break;
            }
        }
        String[] categorias = getResources().getStringArray(R.array.categorias);
        for (int i = 1; i < categorias.length ;i++){
            if(filme.getCategoria().equals(categorias[i])){
                spCategorias.setSelection(i);
                break;
            }
        }
    }
    private void salvar(){

        String nome = etNome.getText().toString();
        int rdButton =idGroup.getCheckedRadioButtonId();

        if(nome.isEmpty() || spGeneros.getSelectedItemPosition() == 0 || spCategorias.getSelectedItemPosition() == 0 || rdButton == -1){
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG ).show();
        }else{
            if(acao.equals("inserir")) {
                filme = new Filme();
            }
            filme.setNome(nome);
            filme.setGenero((spGeneros.getSelectedItem().toString()));
            filme.setCategoria(spCategorias.getSelectedItem().toString());
            RadioButton radioButton = findViewById(rdButton);
            String rdText = radioButton.getText().toString();
            filme.setNacionalidade(rdText);
            if(acao.equals("inserir")) {
                FilmeDAO.inserir(this, filme);
                etNome.setText("");
                spGeneros.setSelection(0, true);
                spCategorias.setSelection(0, true);
                idGroup.clearCheck();
            }else{
                FilmeDAO.editar(this, filme);
                finish();
            }
        }
    }
}