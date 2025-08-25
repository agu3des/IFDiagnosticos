package reports.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LaudoHtml implements LaudoTemplate {

    @Override
    public String gerarConteudo(String cabecalho, String corpo, String rodape) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>\n");
        sb.append("<pre>").append(cabecalho).append("</pre>\n");
        sb.append("<hr/>\n");
        sb.append("<p>").append(corpo.replace("\n", "<br/>")).append("</p>\n");
        sb.append("<hr/>\n");
        sb.append("<small>").append(rodape).append("</small>\n");
        sb.append("</body></html>");
        return sb.toString();
    }

    @Override
    public String salvarEmArquivo(String conteudo, String nomeArquivo) {
        String userHome = System.getProperty("user.home");
        File pasta = new File(userHome + File.separator + "Documents" + File.separator + "Laudos");
        pasta.mkdirs();

        String filePath = new File(pasta, nomeArquivo + ".html").getAbsolutePath();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}