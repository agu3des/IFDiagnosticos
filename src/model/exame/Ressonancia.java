package model.exame;

import java.util.Date;

import model.Medico;
import model.Paciente;
import model.enums.Prioridade;

public class Ressonancia extends Exame {
    private String areaCorpo;
    private boolean comContraste;

    public Ressonancia(String codigo, double valorBase, Date dataSolicitacao, Prioridade prioridade, Paciente paciente, Medico medico) {
        super(codigo, valorBase, dataSolicitacao, prioridade, paciente, medico);
        
    }
    
    public void setAreaCorpo(String areaCorpo) {
        this.areaCorpo = areaCorpo;
    }

    public String getAreaCorpo() {
        return areaCorpo;
    }
    
    public void setComContraste(boolean comContraste) {
        this.comContraste = comContraste;
    }

    public boolean getComContraste() {
        return comContraste;
    }
}
