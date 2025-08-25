package ui;

import core.SistemaFacade;
import model.exame.Exame;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class TelaLaudos extends JFrame {

    public TelaLaudos(SistemaFacade sistema) {
        setTitle("📄 Laudos Gerados");
        setSize(700, 400);
        setLocationRelativeTo(null);

        LaudosTableModel model = new LaudosTableModel(sistema.getExamesProcessados());
        JTable tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.CENTER);
    }

    private static class LaudosTableModel extends AbstractTableModel {
        private final List<Exame> exames;
        private final String[] colunas = {"Código", "Paciente", "Médico", "Laudo"};

        public LaudosTableModel(List<Exame> exames) {
            this.exames = exames;
        }

        @Override
        public int getRowCount() {
            return exames.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Exame e = exames.get(rowIndex);
            switch (columnIndex) {
                case 0: return e.getCodigo();
                case 1: return e.getPaciente().getNome();
                case 2: return e.getMedico().getNome();
                case 3: return e.getCaminhoLaudo();
                default: return null;
            }
        }
    }
}
