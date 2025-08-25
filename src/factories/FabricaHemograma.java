package factories;

import java.util.Date;

import model.Medico;
import model.Paciente;
import model.enums.Prioridade;
import model.exame.Exame;
import model.exame.Hemograma;

public class FabricaHemograma implements FabricaExame {
    @Override
    public Exame criarExame(String codigo, double valorBase, Date dataSolicitacao, 
                            Prioridade prioridade, Paciente paciente, Medico medico) {
        return new Hemograma(codigo, valorBase, dataSolicitacao, prioridade, paciente, medico);
    }
}
