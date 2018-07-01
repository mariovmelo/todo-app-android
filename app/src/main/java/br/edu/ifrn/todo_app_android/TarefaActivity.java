package br.edu.ifrn.todo_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifrn.todo_app_android.dominio.Tarefa;

public class TarefaActivity extends AppCompatActivity {


    private Tarefa t;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        Intent intent = getIntent();

        t = (Tarefa) intent.getSerializableExtra("Tarefa");
        pos = intent.getIntExtra("position", -1);
    }

    public void inserir(View v) throws Exception {
        EditText tDesc = findViewById(R.id.editTextDesc);
        EditText tData = findViewById(R.id.editTextData);
        CheckBox cFeito = findViewById(R.id.checkBoxFeito);

        if(t == null)
            t = new Tarefa();

        t.setDescricao(tDesc.getText().toString());
        t.setDataLimite(tData.getText().toString()) ;
        t.setFeito(cFeito.isChecked());

        Intent intent = new Intent();
        intent.putExtra("tarefa", t);
        intent.putExtra("position", pos);

        setResult(MainActivity.ACAO_ADD_EDIT_TAREFA, intent);

        finish();
    }

    public void cancelar(View v){
        setResult(0, null);

        finish();
    }
}
