package ui;

import core.SistemaFacade;

import javax.swing.*;
import java.awt.*;

public class TelaProcessamento extends JFrame {

    private final TelaExame tableModel = new TelaExame();
    private final JTable tabela = new JTable(tableModel);
    private final SistemaFacade sistema = new SistemaFacade();

    public TelaProcessamento() {
        setTitle("📋 Sistema de Processamento de Exames");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JScrollPane scroll = new JScrollPane(tabela);
        JButton btnCarregar = new JButton("📂 Carregar Exames");
        JButton btnProcessar = new JButton("⚙️ Processar Exames");

        JPanel botoes = new JPanel();
        botoes.add(btnCarregar);
        botoes.add(btnProcessar);

        add(scroll, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        btnCarregar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String caminho = chooser.getSelectedFile().getAbsolutePath();
                sistema.getExames(caminho).forEach(tableModel::adicionarExame);
            }
        });

        btnProcessar.addActionListener(e -> {
            sistema.executarFluxoUI(tableModel::atualizarExame);
            JOptionPane.showMessageDialog(this, "✅ Processamento concluído!");
        });
    }
}
