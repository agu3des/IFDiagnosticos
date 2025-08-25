package core;

import reports.GeradorLaudo;
import reports.decorator.DecoradorCarimbo;
import reports.template.LaudoFactory;
import reports.template.LaudoFactoryRegistry;
import reports.template.LaudoTemplate;
import model.exame.Exame;
import model.laudo.Laudo;

public class LaudoFacade {

    private final NotificadorFacade notificadores;

    public LaudoFacade(NotificadorFacade notificadores) {
        this.notificadores = notificadores;
    }

    public String gerarLaudo(Exame exame, String formato, boolean printConsole) {
        try {
 
            LaudoTemplate template = LaudoFactoryRegistry.getTemplate(formato);


            Laudo laudo = LaudoFactory.criarLaudo(exame, template);

            String cabecalho = "Paciente: " + exame.getPaciente().getNome() +
                               "\nMédico: " + exame.getMedico().getNome();
            String corpo = laudo.gerarCorpo(exame); 
            String rodape = "Data: " + exame.getDataSolicitacao();


            LaudoTemplate laudoDecorado = new DecoradorCarimbo(template);
            GeradorLaudo gerador = new GeradorLaudo(laudoDecorado);

            String caminhoLaudo = gerador.gerar(cabecalho, corpo, rodape, exame.getCodigo(), printConsole);
            exame.setCaminhoLaudo(caminhoLaudo); 

            if (notificadores != null) {
                notificadores.notificarPaciente(exame, caminhoLaudo);
            }

            return caminhoLaudo;

        } catch (Exception e) {
            System.err.println("Erro ao gerar laudo: " + e.getMessage());
            return "erro_ao_gerar_laudo";
        }
    }
}
