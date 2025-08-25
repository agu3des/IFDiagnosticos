package model.exame;

import java.util.Date;

import model.Medico;
import model.Paciente;
import model.enums.Prioridade;

public class Hemograma extends Exame {
    private Double hemoglobina;
    private Double leucocitos;
    private Double hematocrito;
    private Double plaquetas;

    public Hemograma(String codigo, double valorBase, Date dataSolicitacao, Prioridade prioridade, Paciente paciente, Medico medico) {
        super(codigo, valorBase, dataSolicitacao, prioridade, paciente, medico);
        
    }
    
    public void setHemoglobina(Double hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public Double getHemoglobina() {
        return hemoglobina;
    }

    public void setHematocrito(Double hematocrito) {
        this.hematocrito = hematocrito;
    }

    public Double getHematocrito() {
        return hematocrito;
    }
    
    public void setLeucocitos(Double leucocitos) {
        this.leucocitos = leucocitos;
    }

    public Double getLeucocitos() {
        return leucocitos;
    }
    public void setPlaquetas(Double plaquetas) {
        this.plaquetas = plaquetas;
    }

    public Double getPlaquetas() {
        return plaquetas;
    }
}
