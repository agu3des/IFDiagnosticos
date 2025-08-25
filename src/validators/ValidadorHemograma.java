package validators;

import model.enums.Sexo;
import model.exame.Exame;
import model.exame.Hemograma;
import java.util.ArrayList;
import java.util.List;

public class ValidadorHemograma extends ValidadorBase {

    @Override
    public List<String> validar(Exame exame) {
        List<String> erros = new ArrayList<>();

        if (!(exame instanceof Hemograma)) {
            erros.add("Exame não é do tipo Hemograma");
            return erros;
        }

        Hemograma h = (Hemograma) exame;

        erros.addAll(validarExameBase(h));

        double hmin = 12, hmax = 18;
        if (h.getPaciente().getSexo() == Sexo.FEMININO) {
            hmax = 16;
        }
        double lmin = 4000, lmax = 11000;

        if (h.getHemoglobina() < hmin || h.getHemoglobina() > hmax)
            erros.add("Hemoglobina fora da faixa normal: " + h.getHemoglobina());
        if (h.getLeucocitos() < lmin || h.getLeucocitos() > lmax)
            erros.add("Leucocitos fora da faixa normal: " + h.getLeucocitos());

        return erros;
    }

    public String getStatusHemoglobina(double valor, Sexo sexo) {
        double min = (sexo == Sexo.MASCULINO) ? 13.8 : 12.1;
        double max = (sexo == Sexo.MASCULINO) ? 17.2 : 15.1;

        if (valor < min - 2.0 || valor > max + 2.0) return "CRÍTICO";
        if (valor < min || valor > max) return "ALTERADO";
        return "NORMAL";
    }

    public String getStatusLeucocitos(double valor) {
        double min = 4500;
        double max = 11000;

        if (valor < min * 0.5 || valor > max * 2.0) return "CRÍTICO";
        if (valor < min || valor > max) return "ALTERADO";
        return "NORMAL";
    }

    public String getStatusHematocrito(double valor, Sexo sexo) {
        double min = (sexo == Sexo.MASCULINO) ? 40.0 : 36.0;
        double max = (sexo == Sexo.MASCULINO) ? 54.0 : 48.0;

        if (valor < min - 5.0 || valor > max + 5.0) return "CRÍTICO";
        if (valor < min || valor > max) return "ALTERADO";
        return "NORMAL";
    }

    public String getStatusPlaquetas(double valor) {
        double min = 150000.0;
        double max = 450000.0;

        if (valor < min * 0.3 || valor > max * 2.0) return "CRÍTICO";
        if (valor < min || valor > max) return "ALTERADO";
        return "NORMAL";
    }
}
