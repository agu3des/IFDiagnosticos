package states;

import model.exame.Exame;

public class ExameSolicitado implements StatusExameState {
    @Override
    public void mudarEstadoExame(Exame exame) {
        System.out.println("Exame solicitado");
        exame.setEstado(new ExameProcessando());
    }    
    
    @Override 
    public void cancelarExame(Exame exame) {
        exame.setEstado(new ExameCancelado());
    }
}
