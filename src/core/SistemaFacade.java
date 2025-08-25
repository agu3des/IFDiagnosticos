package core;

import java.util.List;
import model.exame.Exame;

public class SistemaFacade {
 
    private final CargaDadosFacade carga = new CargaDadosFacade();
    private final ExameFacade exameFacade = new ExameFacade();
    private final ProcessamentoFacade procFacade = new ProcessamentoFacade();
    private final NotificadorFacade notificadores = new NotificadorFacade();
    private final LaudoFacade laudoFacade = new LaudoFacade(notificadores);

    public void executarFluxo(String caminhoCsv) {
        List<Exame> exames = carga.carregarDados(caminhoCsv);

        for (Exame exame : exames) {
            exameFacade.pagarExame(exame);
            procFacade.enfileirarExame(exame);
        }

        procFacade.processarExames(this::processarExame);
    }

    private void processarExame(Exame exameProcessado) {
        try {
            String caminhoLaudo = laudoFacade.gerarLaudo(exameProcessado, "pdf", true);
            System.out.println("Laudo gerado em: " + caminhoLaudo);
        } catch (Exception e) {
            System.err.println("Erro ao processar exame " + exameProcessado.getCodigo() + ": " + e.getMessage());
        }
    }
}

