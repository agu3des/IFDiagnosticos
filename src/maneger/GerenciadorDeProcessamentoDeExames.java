package maneger;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import model.exame.Exame;
import notifier.NotificadorObserver;

public class GerenciadorDeProcessamentoDeExames {

    private PriorityQueue<Exame> filaExames;
    private List<NotificadorObserver> notificadores;

    public GerenciadorDeProcessamentoDeExames() {
        this.filaExames = new PriorityQueue<>(
            (e1, e2) -> e1.getPrioridade().compareTo(e2.getPrioridade())
        );
        this.notificadores = new ArrayList<>();
    }

    public void adicionarNotificador(NotificadorObserver notificador) {
        notificadores.add(notificador);
    }

    public List<NotificadorObserver> getNotificadores() {
        return notificadores;
    }

    public void adicionarExame(Exame exame) {
        filaExames.add(exame);
    }

    public Exame processarProximoExame() {
        Exame exame = filaExames.poll();
        if (exame != null) {
            exame.avancarEstado(); // Solicitação -> Processando
            exame.avancarEstado(); // Processando -> Concluído
        }
        return exame;
    }


    public void notificarLaudoPronto(Exame exame, String caminhoLaudo) {
        for (NotificadorObserver notificador : notificadores) {
            notificador.atualizar(exame, caminhoLaudo);
        }
    }
}
