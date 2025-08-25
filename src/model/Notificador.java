package model;

import model.exame.Exame;
import java.time.LocalDateTime;

public class Notificador {
    private final String id;
    private final Exame exame;
    private final String destino; 
    private final String mensagem;
    private final LocalDateTime dataEnvio;
    private boolean sucesso;

    public Notificador(Exame exame, String destino, String mensagem, boolean sucesso) {
        this.id = java.util.UUID.randomUUID().toString();
        this.exame = exame;
        this.destino = destino;
        this.mensagem = mensagem;
        this.dataEnvio = LocalDateTime.now();
        this.sucesso = sucesso;
    }

    public String getId() { return id; }
    public Exame getExame() { return exame; }
    public String getDestino() { return destino; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public boolean isSucesso() { return sucesso; }
    public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }
}

