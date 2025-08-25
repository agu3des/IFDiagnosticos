package model;

import factories.FabricaExame;
import model.exame.Exame;

public class Medico {
    private String nome;
    private String crm;

    public Medico(String nome, String crm) {
        this.nome = nome;
        this.crm = crm;
    }

    public void setNome(String nome)  {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCrm(String crm)  {
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }


    public Exame solicitarExame(Paciente paciente, String tipoExame, FabricaExame fabrica) {
        return fabrica.criarExame(tipoExame, 0, null, null, paciente, this);
    }
}
