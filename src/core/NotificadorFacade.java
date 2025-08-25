package core;

import model.exame.Exame;
import model.Notificador;
import notifier.NotificadorEmail;
import notifier.NotificadorObserver;

import java.util.ArrayList;
import java.util.List;

public class NotificadorFacade {

    private final List<NotificadorObserver> notificadores = new ArrayList<>();
    private final List<Notificador> historico = new ArrayList<>();

    public NotificadorFacade() {
        notificadores.add(new NotificadorEmail());
    }

    public void notificarPaciente(Exame exame, String caminhoLaudo) {
        for (NotificadorObserver notificador : notificadores) {
            try {
                notificador.atualizar(exame, caminhoLaudo);
                historico.add(new Notificador(
                        exame,
                        exame.getPaciente().getEmail(),
                        "Laudo disponível em: " + caminhoLaudo,
                        true
                ));
            } catch (Exception e) {
                historico.add(new Notificador(
                        exame,
                        exame.getPaciente().getEmail(),
                        "Falha ao enviar notificação para laudo " + exame.getCodigo(),
                        false
                ));
            }
        }
    }

    public void adicionarNotificador(NotificadorObserver notificador) {
        notificadores.add(notificador);
    }

    public List<Notificador> getHistorico() {
        return new ArrayList<>(historico);
    }
}
