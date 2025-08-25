package ui;

import model.exame.Exame;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TelaExame extends AbstractTableModel {
    private final List<Exame> exames = new ArrayList<>();
    private final String[] colunas = {"Código", "Paciente", "Status", "Laudo"};

    public void adicionarExame(Exame exame) {
        exames.add(exame);
        fireTableRowsInserted(exames.size() - 1, exames.size() - 1);
    }

    public void atualizarExame(Exame exame) {
        int index = exames.indexOf(exame);
        if (index >= 0) {
            fireTableRowsUpdated(index, index);
        }
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
        Exame exame = exames.get(rowIndex);
        switch (columnIndex) {
            case 0: return exame.getCodigo();
            case 1: return exame.getPaciente().getNome();
            case 2: return exame.getEstado();
            case 3: return exame.getCaminhoLaudo() != null ? exame.getCaminhoLaudo() : "—";
            default: return null;
        }
    }

}
