package br.edu.ifrn.todo_app_android.dominio;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private long id;
    private String descricao;
    private boolean feito;
    private String dataLimite;

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFeito() {
        return feito;
    }

    public void setFeito(boolean feito) {
        this.feito = feito;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString(){
        String saida = "Descrição: %s, Data Limite: %s";
        return String.format(saida,descricao,dataLimite);
    }
}
