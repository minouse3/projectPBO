package Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelPengguna extends AbstractTableModel {
    private List<Pengguna> list;
    private final String[] columnNames = {"ID", "Nama", "Username", "Email", "No. HP", "Role", "Status"};

    public TableModelPengguna(List<Pengguna> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return list.get(rowIndex).getIdPengguna();
            case 1: return list.get(rowIndex).getNamaPengguna();
            case 2: return list.get(rowIndex).getUsername();
            case 3: return list.get(rowIndex).getEmail();
            case 4: return list.get(rowIndex).getNoHp();
            case 5: return list.get(rowIndex).getRole();
            case 6: return list.get(rowIndex).getStatusAkun();
            default: return null;
        }
    }
}
