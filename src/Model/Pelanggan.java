package Model;

import java.sql.Timestamp;

public class Pelanggan {
    private Integer idPelanggan;
    private String namaPelanggan;
    private String noHp;
    private String email;
    private String alamat;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Pelanggan() {
    }

    public Pelanggan(Integer idPelanggan, String namaPelanggan, String noHp, String email, String alamat) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.noHp = noHp;
        this.email = email;
        this.alamat = alamat;
    }

    public Integer getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(Integer idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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
        return namaPelanggan + " - " + noHp;
    }
}
