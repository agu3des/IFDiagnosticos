package validators;

import model.enums.FaixaEtaria;
import model.exame.Exame;
import model.exame.Ressonancia;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ValidadorRessonancia extends ValidadorBase {

    private final List<String> areasValidas = Arrays.asList(
        "cerebro", "coluna", "joelho", "torax", "abdomen"
    );

    @Override
    public List<String> validar(Exame exame) {
        List<String> erros = new ArrayList<>();

        if (!(exame instanceof Ressonancia)) {
            erros.add("Exame não é do tipo Ressonância");
            return erros;
        }

        Ressonancia r = (Ressonancia) exame;

        erros.addAll(validarExameBase(r));
        if (r.getAreaCorpo() == null || r.getAreaCorpo().isBlank()) {
            erros.add("Área do corpo nao pode ser vazia");
        } else if (!areasValidas.contains(r.getAreaCorpo().toLowerCase())) {
            erros.add("Area do corpo invalida: " + r.getAreaCorpo());
        }

        if (r.getComContraste() && r.getPaciente().getFaixaEtaria() == FaixaEtaria.CRIANCA) {
            erros.add("Pacientes criancas nao podem fazer ressonancia com contraste");
        }

        return erros;
    }
}