package states;

import model.exame.Exame;

public interface StatusExameState {
    public void mudarEstadoExame(Exame exame);
    public void cancelarExame(Exame exame);
} 
