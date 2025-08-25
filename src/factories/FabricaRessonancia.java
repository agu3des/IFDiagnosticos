package factories;
import java.util.Date;

import model.Medico;
import model.Paciente;
import model.enums.Prioridade;
import model.exame.Exame;
import model.exame.Ressonancia;


public class FabricaRessonancia implements FabricaExame {
    @Override
    public Exame criarExame(String codigo, double valorBase, Date dataSolicitacao, 
                            Prioridade prioridade, Paciente paciente, Medico medico) {
        return new Ressonancia(codigo, valorBase, dataSolicitacao, prioridade, paciente, medico);
    }
}
