package br.edu.ifrn.todo_app_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.edu.ifrn.todo_app_android.dominio.Tarefa;

public class MainActivity extends AppCompatActivity {

    public static final int ACAO_ADD_EDIT_TAREFA = 1;

    private ArrayAdapter<Tarefa> tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tarefaAdapter = new ArrayAdapter<Tarefa>(this,android.R.layout.simple_list_item_1);

        ListView list = findViewById(R.id.list_view);
        list.setAdapter(tarefaAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,TarefaActivity.class), MainActivity.ACAO_ADD_EDIT_TAREFA);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == this.ACAO_ADD_EDIT_TAREFA
                && resultCode != 0){
            Tarefa t = (Tarefa) data.getSerializableExtra("tarefa");
            int pos = data.getIntExtra("position", -1);

            if(pos == -1){
                tarefaAdapter.add(t);
            }else{
                tarefaAdapter.remove(t);
                tarefaAdapter.insert(t,pos);
            }
            tarefaAdapter.notifyDataSetChanged();
        }
    }
}
