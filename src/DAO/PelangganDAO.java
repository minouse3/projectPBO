package DAO;

import DAOInterface.PelangganDAOInterface;
import Koneksi.Koneksi;
import Model.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO implements PelangganDAOInterface {
    private Connection connection;

    public PelangganDAO() {
        connection = Koneksi.getKoneksi();
    }

    private Connection getConnection() {
        connection = Koneksi.getKoneksi();
        return connection;
    }

    @Override
    public void insert(Pelanggan pelanggan) {
        String sql = "INSERT INTO tbl_pelanggan (nama_pelanggan, no_hp, email, alamat) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, pelanggan.getNamaPelanggan());
            ps.setString(2, pelanggan.getNoHp());
            ps.setString(3, pelanggan.getEmail());
            ps.setString(4, pelanggan.getAlamat());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal menambah pelanggan: " + ex.getMessage());
        }
    }

    @Override
    public void update(Pelanggan pelanggan) {
        String sql = "UPDATE tbl_pelanggan SET nama_pelanggan=?, no_hp=?, email=?, alamat=? WHERE id_pelanggan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, pelanggan.getNamaPelanggan());
            ps.setString(2, pelanggan.getNoHp());
            ps.setString(3, pelanggan.getEmail());
            ps.setString(4, pelanggan.getAlamat());
            ps.setInt(5, pelanggan.getIdPelanggan());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mengubah pelanggan: " + ex.getMessage());
        }
    }

    @Override
    public void delete(int idPelanggan) {
        String sql = "DELETE FROM tbl_pelanggan WHERE id_pelanggan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPelanggan);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal menghapus pelanggan. Kemungkinan pelanggan sudah memiliki data booking.");
        }
    }

    @Override
    public Pelanggan getById(int idPelanggan) {
        String sql = "SELECT * FROM tbl_pelanggan WHERE id_pelanggan=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPelanggan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal mengambil pelanggan: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Pelanggan> getAll() {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_pelanggan ORDER BY id_pelanggan DESC";
        try (Statement st = getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Gagal menampilkan pelanggan: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Pelanggan> search(String keyword) {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_pelanggan "
                + "WHERE nama_pelanggan LIKE ? OR no_hp LIKE ? OR email LIKE ? OR alamat LIKE ? "
                + "ORDER BY id_pelanggan DESC";
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
            throw new RuntimeException("Gagal mencari pelanggan: " + ex.getMessage());
        }
        return list;
    }

    private Pelanggan mapResultSet(ResultSet rs) throws SQLException {
        Pelanggan p = new Pelanggan();
        p.setIdPelanggan(rs.getInt("id_pelanggan"));
        p.setNamaPelanggan(rs.getString("nama_pelanggan"));
        p.setNoHp(rs.getString("no_hp"));
        p.setEmail(rs.getString("email"));
        p.setAlamat(rs.getString("alamat"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        p.setUpdatedAt(rs.getTimestamp("updated_at"));
        return p;
    }
}
