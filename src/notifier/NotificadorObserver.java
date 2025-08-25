package notifier;

import model.exame.Exame;

public interface NotificadorObserver {
    void atualizar(Exame exame, String caminhoLaudo);
}
