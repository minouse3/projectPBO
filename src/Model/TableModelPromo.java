package Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelPromo extends AbstractTableModel {
    private List<Promo> list;
    private final String[] columnNames = {"ID", "Kode", "Nama", "Min Jam", "Jenis", "Nilai", "Maks", "Start", "End", "Status"};

    public TableModelPromo(List<Promo> list) {
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
            case 0: return list.get(rowIndex).getIdPromo();
            case 1: return list.get(rowIndex).getKodePromo();
            case 2: return list.get(rowIndex).getNamaPromo();
            case 3: return list.get(rowIndex).getMinimalDurasiJam();
            case 4: return list.get(rowIndex).getJenisDiskon();
            case 5: return list.get(rowIndex).getNilaiDiskon();
            case 6: return list.get(rowIndex).getMaksimalDiskon();
            case 7: return list.get(rowIndex).getTanggalMulai();
            case 8: return list.get(rowIndex).getTanggalSelesai();
            case 9: return list.get(rowIndex).getStatusPromo();
            default: return null;
        }
    }
}
