package Service;

import DAO.PelangganDAO;
import Model.Pelanggan;
import java.util.List;

public class PelangganService {
    private final PelangganDAO pelangganDAO = new PelangganDAO();

    public void tambah(Pelanggan pelanggan) {
        validasi(pelanggan);
        pelangganDAO.insert(pelanggan);
    }

    public void ubah(Pelanggan pelanggan) {
        if (pelanggan.getIdPelanggan() == null || pelanggan.getIdPelanggan() <= 0) {
            throw new IllegalArgumentException("Pilih data pelanggan terlebih dahulu.");
        }
        validasi(pelanggan);
        pelangganDAO.update(pelanggan);
    }

    public void hapus(int idPelanggan) {
        if (idPelanggan <= 0) {
            throw new IllegalArgumentException("Pilih data pelanggan terlebih dahulu.");
        }
        pelangganDAO.delete(idPelanggan);
    }

    public Pelanggan getById(int idPelanggan) {
        return pelangganDAO.getById(idPelanggan);
    }

    public List<Pelanggan> getAll() {
        return pelangganDAO.getAll();
    }

    public List<Pelanggan> cari(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return pelangganDAO.getAll();
        }
        return pelangganDAO.search(keyword.trim());
    }

    private void validasi(Pelanggan pelanggan) {
        if (pelanggan.getNamaPelanggan() == null || pelanggan.getNamaPelanggan().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pelanggan tidak boleh kosong.");
        }
        if (pelanggan.getNoHp() == null || pelanggan.getNoHp().trim().isEmpty()) {
            throw new IllegalArgumentException("No HP pelanggan tidak boleh kosong.");
        }
        if (!pelanggan.getNoHp().matches("[0-9+ -]{8,20}")) {
            throw new IllegalArgumentException("Format no HP tidak valid.");
        }
        if (pelanggan.getEmail() != null && !pelanggan.getEmail().trim().isEmpty()
                && !pelanggan.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Format email tidak valid.");
        }
    }
}
