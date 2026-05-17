package DAO;

import DAOInterface.LapanganDAOInterface;
import Koneksi.Koneksi;
import Model.Lapangan;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LapanganDAO implements LapanganDAOInterface {
    private Connection connection;

    public LapanganDAO() {
        connection = Koneksi.getKoneksi();
    }

    private Connection getConnection() {
        connection = Koneksi.getKoneksi();
        return connection;
    }

    @Override
    public void insert(Lapangan lapangan) {
        String sql = "INSERT INTO tbl_lapangan (nama_lapangan, alamat_lapangan, jenis_lapangan, status_lapangan, harga_per_jam) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, lapangan.getNamaLapangan());
            ps.setString(2, lapangan.getAlamatLapangan());
            ps.setString(3, lapangan.getJenisLapangan());
            ps.setString(4, lapangan.getStatusLapangan());
            ps.setBigDecimal(5, lapangan.getHargaPerJam());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal menambah lapangan: " + ex.getMessage());
        }
    }

    @Override
    public void updateBySuperadmin(Lapangan lapangan) {
        String sql = "UPDATE tbl_lapangan SET nama_lapangan=?, alamat_lapangan=?, jenis_lapangan=?, "
                + "status_lapangan=?, harga_per_jam=? WHERE id_lapangan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, lapangan.getNamaLapangan());
            ps.setString(2, lapangan.getAlamatLapangan());
            ps.setString(3, lapangan.getJenisLapangan());
            ps.setString(4, lapangan.getStatusLapangan());
            ps.setBigDecimal(5, lapangan.getHargaPerJam());
            ps.setInt(6, lapangan.getIdLapangan());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mengubah lapangan: " + ex.getMessage());
        }
    }

    @Override
    public void updateByAdmin(Lapangan lapangan) {
        String sql = "UPDATE tbl_lapangan SET nama_lapangan=?, alamat_lapangan=?, jenis_lapangan=?, "
                + "status_lapangan=? WHERE id_lapangan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, lapangan.getNamaLapangan());
            ps.setString(2, lapangan.getAlamatLapangan());
            ps.setString(3, lapangan.getJenisLapangan());
            ps.setString(4, lapangan.getStatusLapangan());
            ps.setInt(5, lapangan.getIdLapangan());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mengubah lapangan: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int idLapangan) {
        String sql = "DELETE FROM tbl_lapangan WHERE id_lapangan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idLapangan);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal menghapus lapangan. Kemungkinan lapangan sudah memiliki data booking.");
        }
    }

    @Override
    public void nonaktifkan(int idLapangan) {
        String sql = "UPDATE tbl_lapangan SET status_lapangan='TIDAK_TERSEDIA' WHERE id_lapangan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idLapangan);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal menonaktifkan lapangan: " + ex.getMessage());
        }
    }

    @Override
    public Lapangan getById(int idLapangan) {
        String sql = "SELECT * FROM tbl_lapangan WHERE id_lapangan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idLapangan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mengambil lapangan: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Lapangan> getAll() {
        List<Lapangan> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_lapangan ORDER BY id_lapangan DESC";
        try (Statement st = getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal menampilkan lapangan: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Lapangan> search(String keyword) {
        List<Lapangan> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_lapangan WHERE nama_lapangan LIKE ? OR alamat_lapangan LIKE ? "
                + "OR jenis_lapangan LIKE ? OR status_lapangan LIKE ? ORDER BY id_lapangan DESC";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mencari lapangan: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Lapangan> getLapanganTersedia() {
        List<Lapangan> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_lapangan WHERE status_lapangan='TERSEDIA' ORDER BY nama_lapangan ASC";
        try (Statement st = getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mengambil lapangan tersedia: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public BigDecimal getHargaPerJam(int idLapangan) {
        String sql = "SELECT harga_per_jam FROM tbl_lapangan WHERE id_lapangan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idLapangan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("harga_per_jam");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mengambil harga lapangan: " + ex.getMessage());
        }
        return BigDecimal.ZERO;
    }

    private Lapangan mapResultSet(ResultSet rs) throws SQLException {
        Lapangan l = new Lapangan();
        l.setIdLapangan(rs.getInt("id_lapangan"));
        l.setNamaLapangan(rs.getString("nama_lapangan"));
        l.setAlamatLapangan(rs.getString("alamat_lapangan"));
        l.setJenisLapangan(rs.getString("jenis_lapangan"));
        l.setStatusLapangan(rs.getString("status_lapangan"));
        l.setHargaPerJam(rs.getBigDecimal("harga_per_jam"));
        l.setCreatedAt(rs.getTimestamp("created_at"));
        l.setUpdatedAt(rs.getTimestamp("updated_at"));
        return l;
    }
}
