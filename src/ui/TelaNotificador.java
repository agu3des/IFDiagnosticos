package ui;

import core.SistemaFacade;
import model.Notificador;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class TelaNotificador extends JFrame {

    private final NotificadoresTableModel model;

    public TelaNotificador(SistemaFacade sistema) {
        setTitle("✉️ Notificações Enviadas");
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new NotificadoresTableModel(sistema.getNotificadorFacade().getHistorico());
        JTable tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.CENTER);
    }

    public void atualizarTabela() {
        model.fireTableDataChanged();
    }

    private static class NotificadoresTableModel extends AbstractTableModel {
        private final List<Notificador> notificadores;
        private final String[] colunas = {"Paciente", "E-mail", "Mensagem", "Status"};

        public NotificadoresTableModel(List<Notificador> notificadores) {
            this.notificadores = notificadores;
        }

        @Override
        public int getRowCount() {
            return notificadores.size();
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
            Notificador n = notificadores.get(rowIndex);
            switch (columnIndex) {
                case 0: return n.getExame().getPaciente().getNome();
                case 1: return n.getDestino();
                case 2: return n.getMensagem();
                case 3: return n.isSucesso() ? "✅" : "❌";
                default: return null;
            }
        }
    }
}
