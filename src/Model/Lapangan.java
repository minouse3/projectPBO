package Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Lapangan {
    private Integer idLapangan;
    private String namaLapangan;
    private String alamatLapangan;
    private String jenisLapangan;
    private String statusLapangan;
    private BigDecimal hargaPerJam;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Lapangan() {
    }

    public Lapangan(Integer idLapangan, String namaLapangan, String alamatLapangan,
                    String jenisLapangan, String statusLapangan, BigDecimal hargaPerJam) {
        this.idLapangan = idLapangan;
        this.namaLapangan = namaLapangan;
        this.alamatLapangan = alamatLapangan;
        this.jenisLapangan = jenisLapangan;
        this.statusLapangan = statusLapangan;
        this.hargaPerJam = hargaPerJam;
    }

    public Integer getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(Integer idLapangan) {
        this.idLapangan = idLapangan;
    }

    public String getNamaLapangan() {
        return namaLapangan;
    }

    public void setNamaLapangan(String namaLapangan) {
        this.namaLapangan = namaLapangan;
    }

    public String getAlamatLapangan() {
        return alamatLapangan;
    }

    public void setAlamatLapangan(String alamatLapangan) {
        this.alamatLapangan = alamatLapangan;
    }

    public String getJenisLapangan() {
        return jenisLapangan;
    }

    public void setJenisLapangan(String jenisLapangan) {
        this.jenisLapangan = jenisLapangan;
    }

    public String getStatusLapangan() {
        return statusLapangan;
    }

    public void setStatusLapangan(String statusLapangan) {
        this.statusLapangan = statusLapangan;
    }

    public BigDecimal getHargaPerJam() {
        return hargaPerJam;
    }

    public void setHargaPerJam(BigDecimal hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return namaLapangan + " (" + jenisLapangan + ")";
    }
}
