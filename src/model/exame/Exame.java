package model.exame;

import java.util.Date;
import java.util.UUID;

import model.Medico;
import model.Paciente;
import model.enums.Prioridade;
import states.ExameSolicitado;
import states.StatusExameState;

public abstract class Exame {
    private String codigo;
    private double valorBase;
    private Date dataSolicitacao;
    private Prioridade prioridade;
    private Paciente paciente;
    private Medico medico;
    private StatusExameState estado;
    //private LaudoTemplate laudo;
    private String caminhoLaudo;




    public Exame(String codigo, double valorBase, Date dataSolicitacao, Prioridade prioridade, Paciente paciente, Medico medico) {//, LaudoTemplate laudo) {
        this.codigo = setCodigoUnico();
        this.valorBase = valorBase;
        this.dataSolicitacao = dataSolicitacao;
        this.prioridade = prioridade;
        this.paciente = paciente;
        this.medico = medico;
        this.estado = new ExameSolicitado();
        //this.laudo = laudo;
    }

    public String getCodigo() {
        if (codigo == null || codigo.isEmpty()) {
            codigo = setCodigoUnico();
        }
        return codigo;
    }

    private String setCodigoUnico() {
        return "EX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getCaminhoLaudo() { 
        return caminhoLaudo; 
    }

    public void setCaminhoLaudo(String caminho) { 
        this.caminhoLaudo = caminho; 
    }

    public StatusExameState getEstado() {
        return estado;
    }

    public void setEstado(StatusExameState estado) {
        this.estado = estado;
    }

    public void avancarEstado() {
        estado.mudarEstadoExame(this);
    }

    public void cancelarExame() {
        estado.cancelarExame(this);
    }

    // public LaudoTemplate getLaudo() {
    //     return laudo;
    // }

    // public void setLaudo(LaudoTemplate laudo) {
    //     this.laudo = laudo;
    // }

    

}
