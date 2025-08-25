package core;

import java.util.Date;

import factories.ExameFactoryRegistry;
import factories.FabricaExame;
import model.Medico;
import model.Paciente;
import model.enums.Prioridade;
import model.exame.Exame;
import payments.DescontoConvenio;
import payments.DescontoStrategy;
import payments.ProcessadorPagamento;

public class ExameFacade {

    public Exame agendarExame(String tipoExame, String codigo, double valorBase,
                              Date dataSolicitacao, Prioridade prioridade,
                              Paciente paciente, Medico medico) {
        FabricaExame factory = ExameFactoryRegistry.getFactory(tipoExame);
        if (factory == null) {
            throw new IllegalArgumentException("Exame não suportado: " + tipoExame);
        }

        Exame exame = factory.criarExame(codigo, valorBase, dataSolicitacao, prioridade, paciente, medico);
        paciente.adicionarExame(exame);
        return exame;
    }

    public void pagarExame(Exame exame) {
        DescontoStrategy desconto = exame.getPaciente().getConvenio()
                ? new DescontoConvenio()
                : (valor) -> valor;

        new ProcessadorPagamento(exame, desconto).processarPagamento();
    }
}
