package model.laudo;

import model.exame.Exame;
import model.exame.Ressonancia;
import reports.template.LaudoTemplate;
import validators.ValidadorRessonancia;

public class LaudoRessonancia extends Laudo {
    private final ValidadorRessonancia validador = new ValidadorRessonancia();

    public LaudoRessonancia(LaudoTemplate modelo) {
        super(modelo);
    }

    @Override
    protected String getNomeExame() {
        return "Ressonancia";
    }

    @Override
    public String gerarCorpo(Exame exame) {
        if (!(exame instanceof Ressonancia)) {
            throw new IllegalArgumentException("Exame nao e Ressonancia!");
        }

        Ressonancia r = (Ressonancia) exame;

        if (!validador.validar(exame).isEmpty()) {
            return "Dados invalidos para o exame de Ressonancia.";
        }

        StringBuilder corpo = new StringBuilder();

        // Cabecalho da empresa
        corpo.append("============================================\n");
        corpo.append("IF DIAGNOSTICOS - LAUDO MEDICO\n");
        corpo.append("============================================\n");
        corpo.append("Exame: Ressonancia Magnetica\n");
        corpo.append("Codigo do Exame: ").append(r.getCodigo()).append("\n");
        corpo.append("Data do Exame: ").append(r.getDataSolicitacao()).append("\n");
        corpo.append("------------------------------------------------------------\n");

        // Dados do paciente
        corpo.append("PACIENTE: ").append(r.getPaciente().getNome()).append("\n");
        corpo.append("CPF: ").append(r.getPaciente().getCpf()).append("\n");
        corpo.append("Email: ").append(r.getPaciente().getEmail()).append("\n");
        corpo.append("Idade: ").append(r.getPaciente().getIdade()).append(" anos | Sexo: ").append(r.getPaciente().getSexo()).append("\n");
        corpo.append("Convenio: ").append(r.getPaciente().getConvenio()).append("\n");
        corpo.append("Medico Solicitante: ").append(r.getMedico().getNome()).append("\n");
        corpo.append("============================================\n\n");

        // Procedimento
        corpo.append("PROCEDIMENTO REALIZADO:\n");
        corpo.append("------------------------------------------------------------\n");
        corpo.append("Ressonancia Magnetica de ").append(r.getAreaCorpo() != null ? r.getAreaCorpo() : "N/A").append("\n");
        corpo.append("Uso de contraste: ").append(!r.getComContraste() && r.getComContraste() ? "SIM" : "NAO").append("\n\n");

        // Descricao tecnica
        corpo.append("DESCRICAO TECNICA:\n");
        corpo.append("------------------------------------------------------------\n");
        corpo.append("Imagens multiplanares e multisequenciais obtidas.\n");
        corpo.append("Tecnica: equipamento de 1.5 Tesla.\n");
        corpo.append("Protocolo padronizado da clinica.\n\n");

        // Achados
        corpo.append("ACHADOS:\n");
        corpo.append("------------------------------------------------------------\n");
        corpo.append("- Estruturas anatomicas preservadas\n");
        corpo.append("- Sem evidencias de lesoes significativas\n");
        corpo.append("- Ausencia de sinais inflamatorios\n\n");

        // Observacoes tecnicas
        corpo.append("OBSERVACOES TECNICAS:\n");
        corpo.append("------------------------------------------------------------\n");
        corpo.append("Exame realizado conforme protocolo interno da clinica.\n");
        corpo.append("Tempo de aquisicao: aproximadamente 30 minutos.\n");

        // Rodape e conclusao
        corpo.append("\n============================================\n");
        corpo.append("CONCLUSAO:\n");
        corpo.append("------------------------------------------------------------\n");
        corpo.append("Exame realizado dentro dos parametros de normalidade.\n");
        corpo.append("============================================\n");
        corpo.append("Medico Responsavel: ").append(r.getMedico().getNome()).append("\n");
        corpo.append("CRM: ").append(r.getMedico().getCrm()).append("\n");
        corpo.append("Data de Emissao: ").append(java.time.LocalDate.now()).append("\n");
        corpo.append("============================================\n");

        return corpo.toString();
    }
}
