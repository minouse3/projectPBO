package Controller;

import Model.Lapangan;
import Service.LapanganService;
import java.math.BigDecimal;
import java.util.List;

public class LapanganController {
    private final LapanganService lapanganService = new LapanganService();

    public void tambah(Lapangan lapangan) {
        lapanganService.tambah(lapangan);
    }

    public void ubah(Lapangan lapangan) {
        lapanganService.ubah(lapangan);
    }

    public void hapus(int idLapangan) {
        lapanganService.hapus(idLapangan);
    }

    public void nonaktifkan(int idLapangan) {
        lapanganService.nonaktifkan(idLapangan);
    }

    public Lapangan getById(int idLapangan) {
        return lapanganService.getById(idLapangan);
    }

    public List<Lapangan> getAll() {
        return lapanganService.getAll();
    }

    public List<Lapangan> cari(String keyword) {
        return lapanganService.cari(keyword);
    }

    public List<Lapangan> getLapanganTersedia() {
        return lapanganService.getLapanganTersedia();
    }

    public BigDecimal getHargaPerJam(int idLapangan) {
        return lapanganService.getHargaPerJam(idLapangan);
    }

    public boolean isSuperadmin() {
        return lapanganService.isSuperadmin();
    }

    public boolean isAdminMurni() {
        return lapanganService.isAdminMurni();
    }
}
