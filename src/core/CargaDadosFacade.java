package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import factories.ExameFactoryRegistry;
import factories.FabricaHemograma;
import factories.FabricaRessonancia;
import model.Medico;
import model.Paciente;
import model.enums.FaixaEtaria;
import model.enums.Prioridade;
import model.enums.Sexo;
import model.exame.Exame;
import model.exame.Hemograma;
import model.exame.Ressonancia;

public class CargaDadosFacade {

    private final ExameFacade exameFacade;

    public CargaDadosFacade() {
        this.exameFacade = new ExameFacade();
    }

    static {
        ExameFactoryRegistry.registerFactory("hemograma", new FabricaHemograma());
        ExameFactoryRegistry.registerFactory("ressonancia", new FabricaRessonancia());
    }

   public List<Exame> carregarDados(String caminhoCsv) {
    List<Exame> exames = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.trim().isEmpty()) continue;

            if (linha.startsWith("\"") && linha.endsWith("\"")) {
                linha = linha.substring(1, linha.length() - 1);
            }

            String[] campos = linha.split(",");

            if (campos.length < 13) {
                System.err.println("Linha inválida: " + linha);
                continue;
            }

            String tipoExame = campos[0].trim().toLowerCase();
            String codigo = campos[1].trim();
            double valorBase = Double.parseDouble(campos[2].trim());
            Date dataSolicitacao = new Date(Long.parseLong(campos[3].trim()));
            Prioridade prioridade = Prioridade.valueOf(campos[4].trim().toUpperCase());

            String nomePaciente = campos[5].trim();
            String cpf = campos[6].trim();
            String email = campos[7].trim();
            Sexo sexo = Sexo.valueOf(campos[8].trim().toUpperCase());
            FaixaEtaria faixaEtaria = FaixaEtaria.valueOf(campos[9].trim().toUpperCase());
            boolean temConvenio = Boolean.parseBoolean(campos[10].trim());

            Paciente paciente = new Paciente(nomePaciente, cpf, new Date(), email, sexo, faixaEtaria, temConvenio);

            String nomeMedico = campos[11].trim();
            String crm = campos[12].trim();
            Medico medico = new Medico(nomeMedico, crm);
            

            Exame exame = exameFacade.agendarExame(tipoExame, codigo, valorBase, dataSolicitacao, prioridade, paciente, medico);
            if (exame.getCodigo() == null || exame.getCodigo().isEmpty()) {
                exame.getCodigo(); 
            }

            if (exame instanceof Hemograma) {
                Hemograma h = (Hemograma) exame;
                h.setHemoglobina(Double.parseDouble(campos[13].trim()));
                h.setLeucocitos(Double.parseDouble(campos[14].trim()));
                h.setHematocrito(Double.parseDouble(campos[15].trim()));
                h.setPlaquetas(Double.parseDouble(campos[16].trim()));
            } else if (exame instanceof Ressonancia) {
                Ressonancia r = (Ressonancia) exame;
                r.setAreaCorpo(campos[13].trim());
                r.setComContraste(Boolean.parseBoolean(campos[14].trim()));
            }

            exames.add(exame);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return exames;
}

}