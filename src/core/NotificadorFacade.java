package core;

import model.exame.Exame;
import notifier.NotificadorEmail;
import notifier.NotificadorObserver;
import java.util.ArrayList;
import java.util.List;

public class NotificadorFacade {

    private final List<NotificadorObserver> notificadores = new ArrayList<>();

    public NotificadorFacade() {
        notificadores.add(new NotificadorEmail());
    }


    public void notificarPaciente(Exame exame, String caminhoLaudo) {
        for (NotificadorObserver notificador : notificadores) {
            notificador.atualizar(exame, caminhoLaudo);
        }
    }

    public void adicionarNotificador(NotificadorObserver notificador) {
        notificadores.add(notificador);
    }
}
