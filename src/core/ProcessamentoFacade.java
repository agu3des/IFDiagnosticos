package core;

import maneger.GerenciadorDeProcessamentoDeExames;
import model.exame.Exame;
import validators.ValidadorExame;
import validators.ValidadorFactory;

import java.util.function.Consumer;

public class ProcessamentoFacade {

    private final GerenciadorDeProcessamentoDeExames gerenciador;

    public ProcessamentoFacade() {
        this.gerenciador = new GerenciadorDeProcessamentoDeExames();
    }

    public void enfileirarExame(Exame exame) {
        gerenciador.adicionarExame(exame);
    }

    public void adicionarNotificadorGenerico() {
        if (gerenciador.getNotificadores().isEmpty()) {
            gerenciador.adicionarNotificador(new notifier.NotificadorEmail());
        }
    }

    public void processarExames(Consumer<Exame> callback) {
        while (true) {
            Exame processado = gerenciador.processarProximoExame();
            if (processado == null) break;

            ValidadorExame validador = ValidadorFactory.criarValidador(processado);
            System.out.println("Validação: " + validador.validar(processado));

            callback.accept(processado);
        }
    }

    public GerenciadorDeProcessamentoDeExames getGerenciador() {
        return gerenciador;
    }
}
