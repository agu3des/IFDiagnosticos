package ui;

import core.SistemaFacade;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private final SistemaFacade sistema = new SistemaFacade();

    public TelaPrincipal() {
        setTitle("📋 IF Diagnósticos - Sistema");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnCarregar = new JButton("📂 Carregar Exames");
        JButton btnProcessar = new JButton("⚙️ Processar Exames");
        JButton btnLaudos = new JButton("📄 Laudos");
        JButton btnNotificador = new JButton("✉️ Notificações");

        JPanel painel = new JPanel();
        painel.add(btnCarregar);
        painel.add(btnProcessar);
        painel.add(btnLaudos);
        painel.add(btnNotificador);

        add(painel, BorderLayout.CENTER);


        btnCarregar.addActionListener(e -> carregarExames());
        btnProcessar.addActionListener(e -> processarExames());
        btnLaudos.addActionListener(e -> abrirTelaLaudos());
        btnNotificador.addActionListener(e -> abrirTelaNotificador());
    }

    private void carregarExames() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String caminho = chooser.getSelectedFile().getAbsolutePath();
            sistema.getExames(caminho);
            JOptionPane.showMessageDialog(this, "✅ Exames carregados!");
        }
    }

    private void processarExames() {
        JOptionPane.showMessageDialog(this, "Iniciando processamento...");
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                sistema.executarFluxoUI(exame -> {
                });
                return null;
            }

            @Override
            protected void done() {
                JOptionPane.showMessageDialog(TelaPrincipal.this, "✅ Processamento concluído!");
            }
        };
        worker.execute();
    }

    private void abrirTelaLaudos() {
        TelaLaudos laudos = new TelaLaudos(sistema);
        laudos.setVisible(true);
    }

    private void abrirTelaNotificador() {
        TelaNotificador notificador = new TelaNotificador(sistema);
        notificador.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);
        });
    }
}
