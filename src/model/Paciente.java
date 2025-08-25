package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import model.enums.FaixaEtaria;
import model.enums.Sexo;
import model.exame.Exame;

public class Paciente {
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private Sexo sexo;
    private FaixaEtaria faixaEtaria;
    private boolean temConvenio;
    private ArrayList<Exame> exames = new ArrayList<Exame>();

    public Paciente(String nome, String cpf, Date dataNascimento, String email, Sexo sexo, FaixaEtaria faixaEtaria, boolean temConvenio) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.sexo = sexo;
        this.faixaEtaria = faixaEtaria;
        this.temConvenio = temConvenio;
    }

    public void setNome(String nome)  {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf)  {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setEmail(String email)  {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setConvenio(boolean convenio)  {
        this.temConvenio = convenio;
    }

    public boolean getConvenio() {
        return temConvenio;
    }

    public void setSexo(Sexo sexo)  {
        this.sexo = sexo;
    }

    public Sexo getSexo() { 
        return sexo; 
    }

    public void setFaixaEtaria(FaixaEtaria faixaEtaria)  {
        this.faixaEtaria = faixaEtaria;
    }
    
    public FaixaEtaria getFaixaEtaria() { 
        return faixaEtaria; 
    }

    public int getIdade() {
        LocalDate nascimento = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoje = LocalDate.now();
        int idade = Period.between(nascimento, hoje).getYears();
        return idade;

    }

    public void adicionarExame(Exame exame) {
        exames.add(exame);
    }
}