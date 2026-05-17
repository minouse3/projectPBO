package Service;

import DAO.LapanganDAO;
import Model.Lapangan;
import Model.UserSession;
import java.math.BigDecimal;
import java.util.List;

public class LapanganService {
    private final LapanganDAO lapanganDAO = new LapanganDAO();

    public void tambah(Lapangan lapangan) {
        if (!isSuperadmin()) {
            throw new SecurityException("Hanya superadmin yang boleh menambah lapangan karena harga per jam wajib dibuat oleh superadmin.");
        }
        validasi(lapangan, true);
        lapanganDAO.insert(lapangan);
    }

    public void ubah(Lapangan lapangan) {
        if (lapangan.getIdLapangan() == null || lapangan.getIdLapangan() <= 0) {
            throw new IllegalArgumentException("Pilih data lapangan terlebih dahulu.");
        }

        if (isSuperadmin()) {
            validasi(lapangan, true);
            lapanganDAO.updateBySuperadmin(lapangan);
        } else {
            validasi(lapangan, false);
            lapanganDAO.updateByAdmin(lapangan);
        }
    }

    public void hapus(int idLapangan) {
        if (!isSuperadmin()) {
            throw new SecurityException("Hanya superadmin yang boleh menghapus data lapangan.");
        }
        if (idLapangan <= 0) {
            throw new IllegalArgumentException("Pilih data lapangan terlebih dahulu.");
        }
        lapanganDAO.delete(idLapangan);
    }

    public void nonaktifkan(int idLapangan) {
        if (idLapangan <= 0) {
            throw new IllegalArgumentException("Pilih data lapangan terlebih dahulu.");
        }
        lapanganDAO.nonaktifkan(idLapangan);
    }

    public Lapangan getById(int idLapangan) {
        return lapanganDAO.getById(idLapangan);
    }

    public List<Lapangan> getAll() {
        return lapanganDAO.getAll();
    }

    public List<Lapangan> cari(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return lapanganDAO.getAll();
        }
        return lapanganDAO.search(keyword.trim());
    }

    public List<Lapangan> getLapanganTersedia() {
        return lapanganDAO.getLapanganTersedia();
    }

    public BigDecimal getHargaPerJam(int idLapangan) {
        return lapanganDAO.getHargaPerJam(idLapangan);
    }

    public boolean isSuperadmin() {
        return UserSession.isLoggedIn()
                && UserSession.getUser() != null
                && "SUPERADMIN".equalsIgnoreCase(UserSession.getUser().getRole());
    }

    public boolean isAdminMurni() {
        return UserSession.isLoggedIn()
                && UserSession.getUser() != null
                && "ADMIN".equalsIgnoreCase(UserSession.getUser().getRole());
    }

    private void validasi(Lapangan lapangan, boolean wajibValidasiHarga) {
        if (lapangan.getNamaLapangan() == null || lapangan.getNamaLapangan().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama lapangan tidak boleh kosong.");
        }
        if (lapangan.getAlamatLapangan() == null || lapangan.getAlamatLapangan().trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat lapangan tidak boleh kosong.");
        }
        if (!isJenisValid(lapangan.getJenisLapangan())) {
            throw new IllegalArgumentException("Jenis lapangan tidak valid.");
        }
        if (!isStatusValid(lapangan.getStatusLapangan())) {
            throw new IllegalArgumentException("Status lapangan tidak valid.");
        }
        if (wajibValidasiHarga) {
            if (lapangan.getHargaPerJam() == null || lapangan.getHargaPerJam().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Harga per jam harus lebih dari 0.");
            }
        }
    }

    private boolean isJenisValid(String jenis) {
        return "Badminton".equals(jenis) || "Futsal".equals(jenis)
                || "Basket".equals(jenis) || "Voli".equals(jenis);
    }

    private boolean isStatusValid(String status) {
        return "TERSEDIA".equals(status) || "TIDAK_TERSEDIA".equals(status);
    }
}
