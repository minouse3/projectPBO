package Controller;

import Model.Pengguna;
import Model.TableModelPengguna;
import Service.PenggunaService;
import View.ViewAdminManagement;
import View.ViewEditUsers;
import java.util.List;
import javax.swing.JOptionPane;

public class ManajemenAdminController {
    private ViewAdminManagement view;
    private PenggunaService service;
    private List<Pengguna> list;

    public ManajemenAdminController(ViewAdminManagement view) {
        this.view = view;
        this.service = new PenggunaService();
        isiTable();
    }

    public void isiTable() {
        list = service.getAllPengguna();
        TableModelPengguna model = new TableModelPengguna(list);
        view.getTableDataPromo().setModel(model);
    }

    public void isiField(int row, ViewEditUsers editView) {
        Pengguna p = list.get(row);
        editView.getTextId().setText(p.getIdPengguna().toString());
        editView.getTextNama().setText(p.getNamaPengguna());
        editView.getTextUsername().setText(p.getUsername());
        editView.getTextEmail().setText(p.getEmail());
        editView.getTextPassword().setText(""); // Password dikosongkan saat edit untuk alasan keamanan
        editView.getTextNoHp().setText(p.getNoHp());
        editView.getComboxRole().setSelectedItem(capitalize(p.getRole()));
        editView.getComboxStatus().setSelectedItem(capitalize(p.getStatusAkun()));
    }

    public void delete() {
        int row = view.getTableDataPromo().getSelectedRow();
        if (row != -1) {
            int id = (int) view.getTableDataPromo().getValueAt(row, 0);

            // Restriction: User cannot delete their own account
            if (Model.UserSession.isLoggedIn() && id == Model.UserSession.getUser().getIdPengguna()) {
                JOptionPane.showMessageDialog(view, "Anda tidak dapat menghapus akun Anda sendiri!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, "Hapus pengguna ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    service.hapusPengguna(id);
                    isiTable();
                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus");
                } catch (Exception e) {
                    if (e.getMessage().contains("foreign key constraint fails")) {
                        JOptionPane.showMessageDialog(view, "Gagal menghapus! Pengguna ini memiliki data transaksi (Booking).\nSilakan ubah status menjadi 'NONAKTIF' saja.");
                    } else {
                        JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + e.getMessage());
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus");
        }
    }

    public void save(ViewEditUsers editView) {
        Pengguna p = new Pengguna();
        p.setNamaPengguna(editView.getTextNama().getText());
        p.setUsername(editView.getTextUsername().getText());
        p.setEmail(editView.getTextEmail().getText());
        p.setPassword(editView.getTextPassword().getText());
        p.setNoHp(editView.getTextNoHp().getText());
        p.setRole(editView.getComboxRole().getSelectedItem().toString().toUpperCase());
        p.setStatusAkun(editView.getComboxStatus().getSelectedItem().toString().toUpperCase());

        if (editView.getTextId().getText().isEmpty()) {
            // Insert
            if (p.getPassword().isEmpty()) {
                JOptionPane.showMessageDialog(editView, "Password baru wajib diisi!");
                return;
            }
            service.tambahPengguna(p);
            JOptionPane.showMessageDialog(editView, "Data berhasil ditambah");
        } else {
            // Update
            p.setIdPengguna(Integer.parseInt(editView.getTextId().getText()));
            service.updatePengguna(p);
            JOptionPane.showMessageDialog(editView, "Data berhasil diupdate");
        }
        editView.dispose();
        isiTable();
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
