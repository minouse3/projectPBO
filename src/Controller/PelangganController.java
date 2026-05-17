package Controller;

import Model.Pelanggan;
import Service.PelangganService;
import java.util.List;

public class PelangganController {
    private final PelangganService pelangganService = new PelangganService();

    public void tambah(Pelanggan pelanggan) {
        pelangganService.tambah(pelanggan);
    }

    public void ubah(Pelanggan pelanggan) {
        pelangganService.ubah(pelanggan);
    }

    public void hapus(int idPelanggan) {
        pelangganService.hapus(idPelanggan);
    }

    public Pelanggan getById(int idPelanggan) {
        return pelangganService.getById(idPelanggan);
    }

    public List<Pelanggan> getAll() {
        return pelangganService.getAll();
    }

    public List<Pelanggan> cari(String keyword) {
        return pelangganService.cari(keyword);
    }
}
