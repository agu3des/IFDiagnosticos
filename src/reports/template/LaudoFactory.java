package reports.template;

import model.exame.Exame;
import model.exame.Hemograma;
import model.exame.Ressonancia;
import model.laudo.Laudo;
import model.laudo.LaudoHemograma;
import model.laudo.LaudoRessonancia;

public class LaudoFactory {
    public static Laudo criarLaudo(Exame exame, LaudoTemplate template) {
        if (exame instanceof Hemograma) {
            return new LaudoHemograma(template);
        } else if (exame instanceof Ressonancia) {
            return new LaudoRessonancia(template);
        }
    
        throw new IllegalArgumentException("Tipo de exame não suportado para geração de laudo.");
    }
    
}
