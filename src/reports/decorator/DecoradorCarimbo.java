package reports.decorator;

import reports.template.LaudoTemplate;

public class DecoradorCarimbo extends DecoradorLaudo {

    public DecoradorCarimbo(LaudoTemplate laudo) {
        super(laudo);
    }

    @Override
    public String gerarConteudo(String cabecalho, String corpo, String rodape) {
        String rodapeComCarimbo = rodape + "\nCarimbo digital: IF Diagnosticos";
        return laudo.gerarConteudo(cabecalho, corpo, rodapeComCarimbo);
    }

    @Override
    public String salvarEmArquivo(String conteudo, String nomeArquivo) {
        return laudo.salvarEmArquivo(conteudo, nomeArquivo);
    }
}
