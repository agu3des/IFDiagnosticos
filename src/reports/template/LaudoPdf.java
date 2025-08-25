package reports.template;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

public class LaudoPdf implements LaudoTemplate {

    @Override
    public String gerarConteudo(String cabecalho, String corpo, String rodape) {
        StringBuilder conteudo = new StringBuilder();
        conteudo.append(cabecalho).append("\n\n");
        conteudo.append(corpo).append("\n\n");
        conteudo.append(rodape);
        return conteudo.toString();
    }

    @Override
    public String salvarEmArquivo(String conteudo, String nomeArquivo) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

            float margin = 50;
            float yStart = 700;
            float leading = 14.5f;
            float yPosition = yStart;

            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);

            String[] lines = conteudo.split("\n");

            for (String line : lines) {
                // muda a fonte se for linha de cabeçalho ou separador
                if (line.contains("=====") || line.contains("-----")) {
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                } else {
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                }

                // verifica se precisa de nova página
                if (yPosition <= 50) { // margem inferior
                    contentStream.endText();
                    contentStream.close();

                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                    yPosition = yStart;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                }

                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
                yPosition -= leading;
            }

            contentStream.endText();
            contentStream.close();

            String dirPath = System.getProperty("user.home") + "/Documentos/Laudos/";
            File directory = new File(dirPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File arquivo = new File(directory, nomeArquivo + ".pdf");
            document.save(arquivo);

            return arquivo.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
