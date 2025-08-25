package core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import model.exame.Exame;

public class SistemaFacade {

    private final CargaDadosFacade carga = new CargaDadosFacade();
    private final ExameFacade exameFacade = new ExameFacade();
    private final ProcessamentoFacade procFacade = new ProcessamentoFacade();
    private final NotificadorFacade notificadores = new NotificadorFacade();
    private final LaudoFacade laudoFacade = new LaudoFacade(notificadores);

    private final List<Exame> examesCarregados = new ArrayList<>();
    private final List<Exame> examesProcessados = new ArrayList<>();

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
            examesProcessados.add(exameProcessado);
            System.out.println("Laudo gerado em: " + caminhoLaudo);
        } catch (Exception e) {
            System.err.println("Erro ao processar exame " + exameProcessado.getCodigo() + ": " + e.getMessage());
        }
    }

    public List<Exame> getExames(String caminhoCsv) {
        examesCarregados.clear();
        List<Exame> lidos = carga.carregarDados(caminhoCsv);
        examesCarregados.addAll(lidos);
        return new ArrayList<>(examesCarregados);
    }

    public List<Exame> getTodosExames() {
        return new ArrayList<>(examesCarregados);
    }

    public List<Exame> getExamesProcessados() {
        return new ArrayList<>(examesProcessados);
    }

    public void executarFluxoUI(Consumer<Exame> onUpdate) {
        for (Exame exame : examesCarregados) {
            try {
                exameFacade.pagarExame(exame);
                procFacade.enfileirarExame(exame);
            } catch (Exception ex) {
                System.err.println("Falha ao preparar exame " + exame.getCodigo() + ": " + ex.getMessage());
            }
        }

        procFacade.processarExames(exameProcessado -> {
            try {
                String caminhoLaudo = laudoFacade.gerarLaudo(exameProcessado, "pdf", true);
                examesProcessados.add(exameProcessado);
                if (onUpdate != null) onUpdate.accept(exameProcessado);
                System.out.println("Laudo gerado em: " + caminhoLaudo);
            } catch (Exception e) {
                System.err.println("Erro ao processar exame " + exameProcessado.getCodigo() + ": " + e.getMessage());
            }
        });
    }

    public NotificadorFacade getNotificadorFacade() {
        return notificadores;
    }
}
