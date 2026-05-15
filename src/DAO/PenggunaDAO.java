package DAO;

import DAOInterface.PenggunaDAOInterface;
import Koneksi.Koneksi;
import Model.Pengguna;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PenggunaDAO implements PenggunaDAOInterface {
    Connection connection;

    public PenggunaDAO() {
        connection = Koneksi.getKoneksi();
    }

    @Override
    public void insert(Pengguna p) {
        String sql = "INSERT INTO tbl_pengguna (nama_pengguna, username, email, password, no_hp, role, status_akun) VALUES (?, ?, ?, SHA2(?, 256), ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNamaPengguna());
            ps.setString(2, p.getUsername());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getPassword());
            ps.setString(5, p.getNoHp());
            ps.setString(6, p.getRole());
            ps.setString(7, p.getStatusAkun());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Pengguna p) {
        String sql = "UPDATE tbl_pengguna SET nama_pengguna=?, username=?, email=?, no_hp=?, role=?, status_akun=? WHERE id_pengguna=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNamaPengguna());
            ps.setString(2, p.getUsername());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getNoHp());
            ps.setString(5, p.getRole());
            ps.setString(6, p.getStatusAkun());
            ps.setInt(7, p.getIdPengguna());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tbl_pengguna WHERE id_pengguna=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Kita lempar exceptionnya agar ditangkap di tingkat atas (Controller)
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Pengguna> getAll() {
        List<Pengguna> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_pengguna";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Pengguna p = mapResultSetToPengguna(rs);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Pengguna> getCariNama(String nama) {
        List<Pengguna> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_pengguna WHERE nama_pengguna LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nama + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToPengguna(rs));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Pengguna login(String identifier, String password) {
        String sql = "SELECT * FROM tbl_pengguna WHERE (username = ? OR email = ?) AND password = SHA2(?, 256) AND status_akun = 'AKTIF'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPengguna(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PenggunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Pengguna mapResultSetToPengguna(ResultSet rs) throws SQLException {
        Pengguna p = new Pengguna();
        p.setIdPengguna(rs.getInt("id_pengguna"));
        p.setNamaPengguna(rs.getString("nama_pengguna"));
        p.setUsername(rs.getString("username"));
        p.setEmail(rs.getString("email"));
        p.setNoHp(rs.getString("no_hp"));
        p.setRole(rs.getString("role"));
        p.setStatusAkun(rs.getString("status_akun"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        p.setUpdatedAt(rs.getTimestamp("updated_at"));
        return p;
    }
}
