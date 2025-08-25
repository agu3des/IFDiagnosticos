package model.laudo;

import java.time.LocalDate;
import model.Medico;
import model.Paciente;
import model.exame.Exame;
import reports.template.LaudoTemplate;

public abstract class Laudo {
    protected LaudoTemplate modelo;

    public Laudo(LaudoTemplate modelo) {
        this.modelo = modelo;
    }

    public void gerar(Exame exame) {
        Medico medicoResponsavel = exame.getMedico();
        String nomeArquivo = gerarNomeArquivo(exame.getPaciente(), exame);
        String cabecalho = gerarCabecalho(exame);
        String corpo = gerarCorpo(exame); 
        String rodape = gerarRodape(medicoResponsavel);

        modelo.gerar(cabecalho, corpo, rodape, nomeArquivo, true);
    }

    public abstract String gerarCorpo(Exame exame);

    protected String gerarNomeArquivo(Paciente paciente, Exame exame) {
        String nome = paciente.getNome().toLowerCase().replace(" ", "_");
        String data = LocalDate.now().toString();
        return "laudo_" + nome + "_" + exame.getCodigo() + "_" + data;
    }

    protected String gerarCabecalho(Exame exame) {
        Paciente paciente = exame.getPaciente();
        return "============================================\n" +
               "           IF DIAGNOSTICOS - LAUDO MEDICO\n" +
               "============================================\n" +
               "Exame: " + getNomeExame() + "\n" +
               "Codigo do Exame: " + exame.getCodigo() + "\n" +
               "Data do Exame: " + exame.getDataSolicitacao() + "\n" +
               "------------------------------------------------------------\n" +
               "PACIENTE: " + paciente.getNome() + "\n" +
               "CPF: " + paciente.getCpf() + "\n" +
               "Email: " + paciente.getEmail() + "\n" +
               "Idade: " + paciente.getIdade() + " anos | Sexo: " + paciente.getSexo() + "\n" +
               "Convenio: " + paciente.getConvenio() + "\n" +
               "Medico Solicitante: " + exame.getMedico().getNome() + "\n" +
               "============================================\n";
    }

    protected String gerarRodape(Medico medicoResponsavel) {
        return "\n============================================\n" +
               "CONCLUSAO:\n" +
               "------------------------------------------------------------\n" +
               "Exame realizado dentro dos parametros de normalidade.\n" +
               "============================================\n" +
               "Medico Responsavel: " + medicoResponsavel.getNome() + "\n" +
               "CRM: " + medicoResponsavel.getCrm() + "\n" +
               "Data de Emissao: " + LocalDate.now() + "\n" +
               "============================================\n";
    }

    protected abstract String getNomeExame();
}
