package factories;

import model.Medico;
import model.Paciente;
import model.enums.Prioridade;
import model.exame.Exame;

import java.util.Date;

public interface FabricaExame {
    Exame criarExame(String codigo, double valorBase, Date dataSolicitacao, 
                     Prioridade prioridade, Paciente paciente, Medico medico);
}
