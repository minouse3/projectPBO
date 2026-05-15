package DAO;

import DAOInterface.PromoDAOInterface;
import Koneksi.Koneksi;
import Model.Promo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PromoDAO implements PromoDAOInterface {
    Connection connection;

    public PromoDAO() {
        connection = Koneksi.getKoneksi();
    }

    @Override
    public void insert(Promo p) {
        String sql = "INSERT INTO tbl_promo (kode_promo, nama_promo, minimal_durasi_jam, jenis_diskon, nilai_diskon, maksimal_diskon, tanggal_mulai, tanggal_selesai, status_promo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getKodePromo());
            ps.setString(2, p.getNamaPromo());
            ps.setInt(3, p.getMinimalDurasiJam());
            ps.setString(4, p.getJenisDiskon());
            ps.setBigDecimal(5, p.getNilaiDiskon());
            ps.setBigDecimal(6, p.getMaksimalDiskon());
            ps.setDate(7, p.getTanggalMulai());
            ps.setDate(8, p.getTanggalSelesai());
            ps.setString(9, p.getStatusPromo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PromoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Promo p) {
        String sql = "UPDATE tbl_promo SET kode_promo=?, nama_promo=?, minimal_durasi_jam=?, jenis_diskon=?, nilai_diskon=?, maksimal_diskon=?, tanggal_mulai=?, tanggal_selesai=?, status_promo=? WHERE id_promo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getKodePromo());
            ps.setString(2, p.getNamaPromo());
            ps.setInt(3, p.getMinimalDurasiJam());
            ps.setString(4, p.getJenisDiskon());
            ps.setBigDecimal(5, p.getNilaiDiskon());
            ps.setBigDecimal(6, p.getMaksimalDiskon());
            ps.setDate(7, p.getTanggalMulai());
            ps.setDate(8, p.getTanggalSelesai());
            ps.setString(9, p.getStatusPromo());
            ps.setInt(10, p.getIdPromo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PromoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tbl_promo WHERE id_promo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PromoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Promo> getAll() {
        List<Promo> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_promo";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToPromo(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Promo> getCariNama(String nama) {
        List<Promo> list = new ArrayList<>();
        String sql = "SELECT * FROM tbl_promo WHERE nama_promo LIKE ? OR kode_promo LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nama + "%");
            ps.setString(2, "%" + nama + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToPromo(rs));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private Promo mapResultSetToPromo(ResultSet rs) throws SQLException {
        Promo p = new Promo();
        p.setIdPromo(rs.getInt("id_promo"));
        p.setKodePromo(rs.getString("kode_promo"));
        p.setNamaPromo(rs.getString("nama_promo"));
        p.setMinimalDurasiJam(rs.getInt("minimal_durasi_jam"));
        p.setJenisDiskon(rs.getString("jenis_diskon"));
        p.setNilaiDiskon(rs.getBigDecimal("nilai_diskon"));
        p.setMaksimalDiskon(rs.getBigDecimal("maksimal_diskon"));
        p.setTanggalMulai(rs.getDate("tanggal_mulai"));
        p.setTanggalSelesai(rs.getDate("tanggal_selesai"));
        p.setStatusPromo(rs.getString("status_promo"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        p.setUpdatedAt(rs.getTimestamp("updated_at"));
        return p;
    }
}
