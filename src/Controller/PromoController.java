package Controller;

import Model.Promo;
import Model.TableModelPromo;
import Service.PromoService;
import View.ViewDataPromo;
import View.ViewEditPromo;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

public class PromoController {
    private ViewDataPromo view;
    private PromoService service;
    private List<Promo> list;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public PromoController(ViewDataPromo view) {
        this.view = view;
        this.service = new PromoService();
        isiTable();
    }

    public void isiTable() {
        list = service.getAllPromo();
        TableModelPromo model = new TableModelPromo(list);
        view.getTableDataPromo().setModel(model);
    }

    public void isiField(int row, ViewEditPromo editView) {
        Promo p = list.get(row);
        editView.getTextId().setText(p.getIdPromo().toString());
        editView.getTextKode().setText(p.getKodePromo());
        editView.getTextNama().setText(p.getNamaPromo());
        editView.getTextMinDurasi().setText(p.getMinimalDurasiJam().toString());
        editView.getComboxJenis().setSelectedItem(capitalize(p.getJenisDiskon()));
        editView.getTextNilai().setText(p.getNilaiDiskon().toString());
        editView.getTextMaksimal().setText(p.getMaksimalDiskon() != null ? p.getMaksimalDiskon().toString() : "");
        editView.getTextStart().setText(sdf.format(p.getTanggalMulai()));
        editView.getTextEnd().setText(sdf.format(p.getTanggalSelesai()));
        editView.getComboxStatus().setSelectedItem(capitalize(p.getStatusPromo()));
    }

    public void delete() {
        int row = view.getTableDataPromo().getSelectedRow();
        if (row != -1) {
            int id = (int) view.getTableDataPromo().getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(view, "Hapus promo ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                service.hapusPromo(id);
                isiTable();
                JOptionPane.showMessageDialog(view, "Promo berhasil dihapus");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus");
        }
    }

    public void save(ViewEditPromo editView) {
        try {
            Promo p = new Promo();
            p.setKodePromo(editView.getTextKode().getText());
            p.setNamaPromo(editView.getTextNama().getText());
            p.setMinimalDurasiJam(Integer.parseInt(editView.getTextMinDurasi().getText()));
            p.setJenisDiskon(editView.getComboxJenis().getSelectedItem().toString().toUpperCase());
            p.setNilaiDiskon(new BigDecimal(editView.getTextNilai().getText()));
            String maks = editView.getTextMaksimal().getText();
            p.setMaksimalDiskon(maks.isEmpty() ? null : new BigDecimal(maks));
            
            p.setTanggalMulai(new java.sql.Date(sdf.parse(editView.getTextStart().getText()).getTime()));
            p.setTanggalSelesai(new java.sql.Date(sdf.parse(editView.getTextEnd().getText()).getTime()));
            p.setStatusPromo(editView.getComboxStatus().getSelectedItem().toString().toUpperCase());

            if (editView.getTextId().getText().isEmpty()) {
                service.tambahPromo(p);
                JOptionPane.showMessageDialog(editView, "Promo berhasil ditambah");
            } else {
                p.setIdPromo(Integer.parseInt(editView.getTextId().getText()));
                service.updatePromo(p);
                JOptionPane.showMessageDialog(editView, "Promo berhasil diupdate");
            }
            editView.dispose();
            isiTable();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(editView, "Format tanggal salah! Gunakan DD-MM-YYYY");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(editView, "Input angka tidak valid!");
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
