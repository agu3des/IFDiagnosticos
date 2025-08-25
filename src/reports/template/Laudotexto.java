package reports.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LaudoTexto implements LaudoTemplate {

    @Override
    public String gerarConteudo(String cabecalho, String corpo, String rodape) {
        StringBuilder sb = new StringBuilder();
        sb.append(cabecalho).append("\n");
        sb.append("---\n");
        sb.append(corpo).append("\n");
        sb.append("---\n");
        sb.append(rodape).append("\n");
        return sb.toString();
    }

  @Override
    public String salvarEmArquivo(String conteudo, String nomeArquivo) {
        String userHome = System.getProperty("user.home");
        File pasta = new File(userHome + File.separator + "Documents" + File.separator + "Laudos");
        pasta.mkdirs();

        String filePath = new File(pasta, nomeArquivo + ".txt").getAbsolutePath();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}