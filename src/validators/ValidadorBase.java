package validators;

import java.util.ArrayList;
import java.util.List;

import model.exame.Exame;

public abstract class ValidadorBase implements ValidadorExame {

    protected List<String> validarExameBase(Exame exame) {
        List<String> erros = new ArrayList<>();
        if (exame.getCodigo() == null || exame.getCodigo().isBlank())
            erros.add("Codigo do exame nao pode ser vazio");
        if (exame.getValorBase() <= 0)
            erros.add("Valor base do exame deve ser maior que 0");
        if (exame.getDataSolicitacao() == null)
            erros.add("Data de solicitacao nao pode ser nula");
        if (exame.getPaciente() == null)
            erros.add("Paciente nao pode ser nulo");
        if (exame.getMedico() == null)
            erros.add("Medico nao pode ser nulo");
        return erros;
    }
}