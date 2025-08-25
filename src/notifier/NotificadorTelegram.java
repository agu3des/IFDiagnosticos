package notifier;

import model.exame.Exame;

public class NotificadorTelegram implements NotificadorObserver {

    private String chatId;

    public NotificadorTelegram(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public void atualizar(Exame exame, String caminhoLaudo) {
        String mensagem = "O exame " + exame.getCodigo() + " do paciente " 
                        + exame.getPaciente().getNome() + " foi processado e o laudo está pronto.\n" +
                        "Acesse o laudo em: " + caminhoLaudo;
        enviarMensagem(chatId, mensagem);
    }

    private void enviarMensagem(String chatId, String mensagem) {
        System.out.println("📲 [Telegram Simulado] Para chatId: " + chatId);
        System.out.println("Mensagem: " + mensagem);
    }
}
