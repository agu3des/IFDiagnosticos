package model.laudo;

import model.enums.Sexo;
import model.exame.Exame;
import model.exame.Hemograma;
import reports.template.LaudoTemplate;
import validators.ValidadorHemograma;

public class LaudoHemograma extends Laudo {
    private final ValidadorHemograma validador = new ValidadorHemograma();

    public LaudoHemograma(LaudoTemplate modelo) {
        super(modelo);
    }

    @Override
    protected String getNomeExame() {
        return "Hemograma";
    }

    @Override
    public String gerarCorpo(Exame exame) {
        if (!(exame instanceof Hemograma)) {
            throw new IllegalArgumentException("Esse exame nao e um Hemograma!");
        }

        Hemograma h = (Hemograma) exame;

        if (!validador.validar(exame).isEmpty()) {
            return "Dados invalidos para o exame de Hemograma.";
        }

        // Valores tratados com null check
        String hemoglobina = h.getHemoglobina() != null ? h.getHemoglobina().toString() : "N/A";
        String leucocitos = h.getLeucocitos() != null ? h.getLeucocitos().toString() : "N/A";
        String hematocrito = h.getHematocrito() != null ? h.getHematocrito().toString() : "N/A";
        String plaquetas = h.getPlaquetas() != null ? h.getPlaquetas().toString() : "N/A";

        StringBuilder corpo = new StringBuilder();

        // Cabecalho da empresa
        corpo.append("============================================\n");
        corpo.append("IF DIAGNOSTICOS - LAUDO MEDICO\n");
        corpo.append("============================================\n");
        corpo.append("Exame: Hemograma\n");
        corpo.append("Codigo do Exame: ").append(h.getCodigo()).append("\n");
        corpo.append("Data do Exame: ").append(h.getDataSolicitacao()).append("\n");
        corpo.append("------------------------------------------------------------\n");

        // Dados do paciente
        corpo.append("PACIENTE: ").append(h.getPaciente().getNome()).append("\n");
        corpo.append("CPF: ").append(h.getPaciente().getCpf()).append("\n");
        corpo.append("Email: ").append(h.getPaciente().getEmail()).append("\n");
        corpo.append("Idade: ").append(h.getPaciente().getIdade()).append(" anos | Sexo: ").append(h.getPaciente().getSexo()).append("\n");
        corpo.append("Convenio: ").append(h.getPaciente().getConvenio()).append("\n");
        corpo.append("Medico Solicitante: ").append(h.getMedico().getNome()).append("\n");
        corpo.append("============================================\n\n");

        // Resultados detalhados
        corpo.append("RESULTADOS DO HEMOGRAMA:\n");
        corpo.append("------------------------------------------------------------\n\n");

        corpo.append("HEMOGLOBINA: ").append(hemoglobina).append(" g/dL\n");
        corpo.append("Valores de referencia: ")
             .append(h.getPaciente().getSexo() == Sexo.MASCULINO ? "13.8 a 17.2 g/dL (adultos)\n" : "12.1 a 15.1 g/dL (adultos)\n");
        corpo.append("Status: ").append(h.getHemoglobina() != null ? validador.getStatusHemoglobina(h.getHemoglobina(), h.getPaciente().getSexo()) : "N/A").append("\n\n");

        corpo.append("LEUCOCITOS: ").append(leucocitos).append(" celulas/mm3\n");
        corpo.append("Valores de referencia: 4.500 a 11.000 celulas/mm3\n");
        corpo.append("Status: ").append(h.getLeucocitos() != null ? validador.getStatusLeucocitos(h.getLeucocitos()) : "N/A").append("\n\n");

        corpo.append("HEMATOCRITO: ").append(hematocrito).append(" %\n");
        corpo.append("Valores de referencia: ")
             .append(h.getPaciente().getSexo() == Sexo.MASCULINO ? "40% a 54% (adultos)\n" : "36% a 48% (adultos)\n");
        corpo.append("Status: ").append(h.getHematocrito() != null ? validador.getStatusHematocrito(h.getHematocrito(), h.getPaciente().getSexo()) : "N/A").append("\n\n");

        corpo.append("PLAQUETAS: ").append(plaquetas).append(" plaquetas/mm3\n");
        corpo.append("Valores de referencia: 150.000 a 450.000 plaquetas/mm3\n");
        corpo.append("Status: ").append(h.getPlaquetas() != null ? validador.getStatusPlaquetas(h.getPlaquetas()) : "N/A").append("\n");

        corpo.append("\n============================================\n");
        corpo.append("CONCLUSAO:\n");
        corpo.append("------------------------------------------------------------\n");
        corpo.append("Exame realizado dentro dos parametros de normalidade.\n");
        corpo.append("============================================\n");
        corpo.append("Medico Responsavel: ").append(h.getMedico().getNome()).append("\n");
        corpo.append("CRM: ").append(h.getMedico().getCrm()).append("\n");
        corpo.append("Data de Emissao: ").append(java.time.LocalDate.now()).append("\n");
        corpo.append("============================================\n");

        return corpo.toString();
    }
}
