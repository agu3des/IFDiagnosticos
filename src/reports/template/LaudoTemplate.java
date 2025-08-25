package reports.template;

public interface LaudoTemplate {
    String gerarConteudo(String cabecalho, String corpo, String rodape);
    String salvarEmArquivo(String conteudo, String nomeArquivo);

       default String gerar(String cabecalho, String corpo, String rodape, String nomeArquivo, boolean printConsole) {
        String conteudo = gerarConteudo(cabecalho, corpo, rodape);
        if (printConsole) {
            System.out.println(conteudo);
        }
        return salvarEmArquivo(conteudo, nomeArquivo);
    }
}
