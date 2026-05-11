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
 *
 * @author steve
 */
public class Koneksi {
    // 2. Buat variabel static untuk menampung koneksi
    private static Connection koneksi;
    
    // 3. Tentukan parameter koneksi: URL, Username, Password
    // Format URL: jdbc:mysql://host:port/nama_database
    private static final String URL = "jdbc:mysql://localhost:3306/db_lapangan";
    private static final String USER = "root"; // Ganti dengan user Anda
    private static final String PASSWORD = ""; // Ganti dengan password Anda
    
    // 4. Buat method static untuk membuat dan mengembalikan koneksi
    public static Connection getKoneksi() {
        // Jika koneksi belum ada atau tertutup, buat yang baru
        if (koneksi == null) {
            try {
                // Langkah penting: Load JDBC Driver class
                // Untuk driver modern (versi 6+), seringkali ini tidak wajib, tapi baik untuk dicantumkan
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Attempt to create a connection to the database
                koneksi = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi ke database BERHASIL!");
                
            } catch (ClassNotFoundException ex) {
                // Error jika driver tidak ditemukan
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, "MySQL JDBC Driver tidak ditemukan! Pastikan file JAR sudah ditambahkan.", ex);
            } catch (SQLException ex) {
                // Error lain terkait koneksi (e.g., salah password, database tidak ada)
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, "Koneksi GAGAL! Periksa URL, username, password, dan status MySQL.", ex);
            }
        }
        // Kembalikan objek koneksi (baik yang berhasil dibuat atau yang sudah ada)
        return koneksi;
    }
}
