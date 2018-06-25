package br.edu.ifrn.todo_http_url;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity {

    private TextView textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textArea = findViewById(R.id.tarefas);

        new HTTPGetTask().execute("http://todo-service-imd.herokuapp.com/tarefa");


    }

    private class HTTPGetTask extends AsyncTask<String,Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuffer data = new StringBuffer();

            try{
                HttpURLConnection con = (HttpURLConnection) new URL(strings[0]).openConnection();
                con.setRequestMethod("GET");
                con.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linha = "";
                while((linha = br.readLine()) != null)
                    data.append(linha+"\n");
                br.close();
                con.disconnect();
            }catch(Exception e){
                Log.e(HTTPGetTask.class.getName(),e.getMessage(),e);
            }

            return data.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            textArea.setText(s);
        }
    }

}
