package reports;

import reports.template.LaudoTemplate;

public class GeradorLaudo {

    private final LaudoTemplate template;

    public GeradorLaudo(LaudoTemplate template) {
        this.template = template;
    }

    public String gerar(String cabecalho, String corpo, String rodape, String nomeArquivo, boolean printConsole) {
        return template.gerar(cabecalho, corpo, rodape, nomeArquivo, printConsole);
    }

}
