package reports.decorator;

import reports.template.LaudoTemplate;

public class DecoradorRodapeConfidencial extends DecoradorLaudo {

    public DecoradorRodapeConfidencial(LaudoTemplate laudo) {
        super(laudo);
    }

    @Override
    public String gerarConteudo(String cabecalho, String corpo, String rodape) {

        String rodapeConfidencial = rodape + "\n⚠️ Este laudo é confidencial e destinado apenas ao paciente.";
        return laudo.gerarConteudo(cabecalho, corpo, rodapeConfidencial);
    }

    @Override
    public String salvarEmArquivo(String conteudo, String nomeArquivo) {
        return laudo.salvarEmArquivo(conteudo, nomeArquivo);
    }
}
