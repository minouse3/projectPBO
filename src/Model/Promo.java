package Model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Promo {
    private Integer idPromo;
    private String kodePromo;
    private String namaPromo;
    private Integer minimalDurasiJam;
    private String jenisDiskon;
    private BigDecimal nilaiDiskon;
    private BigDecimal maksimalDiskon;
    private Date tanggalMulai;
    private Date tanggalSelesai;
    private String statusPromo;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Promo() {}

    public Integer getIdPromo() { return idPromo; }
    public void setIdPromo(Integer idPromo) { this.idPromo = idPromo; }

    public String getKodePromo() { return kodePromo; }
    public void setKodePromo(String kodePromo) { this.kodePromo = kodePromo; }

    public String getNamaPromo() { return namaPromo; }
    public void setNamaPromo(String namaPromo) { this.namaPromo = namaPromo; }

    public Integer getMinimalDurasiJam() { return minimalDurasiJam; }
    public void setMinimalDurasiJam(Integer minimalDurasiJam) { this.minimalDurasiJam = minimalDurasiJam; }

    public String getJenisDiskon() { return jenisDiskon; }
    public void setJenisDiskon(String jenisDiskon) { this.jenisDiskon = jenisDiskon; }

    public BigDecimal getNilaiDiskon() { return nilaiDiskon; }
    public void setNilaiDiskon(BigDecimal nilaiDiskon) { this.nilaiDiskon = nilaiDiskon; }

    public BigDecimal getMaksimalDiskon() { return maksimalDiskon; }
    public void setMaksimalDiskon(BigDecimal maksimalDiskon) { this.maksimalDiskon = maksimalDiskon; }

    public Date getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(Date tanggalMulai) { this.tanggalMulai = tanggalMulai; }

    public Date getTanggalSelesai() { return tanggalSelesai; }
    public void setTanggalSelesai(Date tanggalSelesai) { this.tanggalSelesai = tanggalSelesai; }

    public String getStatusPromo() { return statusPromo; }
    public void setStatusPromo(String statusPromo) { this.statusPromo = statusPromo; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
