/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Koneksi database MySQL.
 *
 * Perubahan yang ditambahkan untuk integrasi modul orang 2:
 * - getKoneksi() sekarang membuat koneksi baru jika koneksi lama sudah closed.
 * - Ditambahkan alias getConnection() agar DAO anggota lain bisa memakai nama method umum.
 */
public class Koneksi {
    private static Connection koneksi;

    private static final String URL = "jdbc:mysql://localhost:3306/db_lapangan?useSSL=false&serverTimezone=Asia/Jakarta";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getKoneksi() {
        try {
            if (koneksi == null || koneksi.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                koneksi = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi ke database BERHASIL!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE,
                    "MySQL JDBC Driver tidak ditemukan! Pastikan file JAR sudah ditambahkan.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE,
                    "Koneksi GAGAL! Periksa URL, username, password, dan status MySQL.", ex);
        }
        return koneksi;
    }

    public static Connection getConnection() {
        return getKoneksi();
    }
}
