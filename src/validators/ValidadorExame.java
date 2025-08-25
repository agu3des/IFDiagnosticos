package validators;

import java.util.List;

import model.exame.Exame;

public interface ValidadorExame {
    List<String> validar(Exame exame);

}