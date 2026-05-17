package View;

import Controller.LapanganController;
import Model.Lapangan;
import Model.UserSession;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LapanganFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LapanganFrame.class.getName());
    private final LapanganController controller = new LapanganController();

    public LapanganFrame() {
        initComponents();
        setLocationRelativeTo(null);
        aturAksesRole();
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        labelSubtitle = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        txtIdLapangan = new javax.swing.JTextField();
        labelNama = new javax.swing.JLabel();
        txtNamaLapangan = new javax.swing.JTextField();
        labelAlamat = new javax.swing.JLabel();
        txtAlamatLapangan = new javax.swing.JTextField();
        labelJenis = new javax.swing.JLabel();
        cmbJenisLapangan = new javax.swing.JComboBox<>();
        labelStatus = new javax.swing.JLabel();
        cmbStatusLapangan = new javax.swing.JComboBox<>();
        labelHarga = new javax.swing.JLabel();
        txtHargaPerJam = new javax.swing.JTextField();
        labelRoleInfo = new javax.swing.JLabel();
        buttonTambah = new javax.swing.JButton();
        buttonUbah = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonNonaktifkan = new javax.swing.JButton();
        buttonBersihkan = new javax.swing.JButton();
        labelCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        buttonCari = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLapangan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Lapangan");
        getContentPane().setBackground(new java.awt.Color(220, 224, 229));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitle.setFont(new java.awt.Font("sansserif", 1, 28)); // NOI18N
        labelTitle.setText("Data Lapangan");
        getContentPane().add(labelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, 40));

        labelSubtitle.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        labelSubtitle.setText("Kelola data lapangan, jenis, status, dan harga per jam");
        getContentPane().add(labelSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 430, 25));

        labelId.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelId.setText("ID");
        getContentPane().add(labelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, 25));

        txtIdLapangan.setEditable(false);
        txtIdLapangan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtIdLapangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 230, 30));

        labelNama.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelNama.setText("Nama Lapangan*");
        getContentPane().add(labelNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 160, 25));

        txtNamaLapangan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtNamaLapangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 230, 30));

        labelAlamat.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelAlamat.setText("Alamat*");
        getContentPane().add(labelAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, 25));

        txtAlamatLapangan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtAlamatLapangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 230, 30));

        labelJenis.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelJenis.setText("Jenis*");
        getContentPane().add(labelJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 160, 25));

        cmbJenisLapangan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmbJenisLapangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Badminton", "Futsal", "Basket", "Voli" }));
        getContentPane().add(cmbJenisLapangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 230, 30));

        labelStatus.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelStatus.setText("Status*");
        getContentPane().add(labelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 160, 25));

        cmbStatusLapangan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cmbStatusLapangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TERSEDIA", "TIDAK_TERSEDIA" }));
        getContentPane().add(cmbStatusLapangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 230, 30));

        labelHarga.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelHarga.setText("Harga / Jam*");
        getContentPane().add(labelHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 160, 25));

        txtHargaPerJam.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtHargaPerJam, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 230, 30));

        labelRoleInfo.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        labelRoleInfo.setText("Harga hanya dapat diubah oleh superadmin.");
        getContentPane().add(labelRoleInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 345, 390, 25));

        buttonTambah.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });
        getContentPane().add(buttonTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 385, 120, 35));

        buttonUbah.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonUbah.setText("Ubah");
        buttonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUbahActionPerformed(evt);
            }
        });
        getContentPane().add(buttonUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 385, 120, 35));

        buttonHapus.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonHapus.setText("Hapus");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });
        getContentPane().add(buttonHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 385, 120, 35));

        buttonNonaktifkan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonNonaktifkan.setText("Nonaktifkan");
        buttonNonaktifkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNonaktifkanActionPerformed(evt);
            }
        });
        getContentPane().add(buttonNonaktifkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 180, 35));

        buttonBersihkan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonBersihkan.setText("Bersihkan");
        buttonBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBersihkanActionPerformed(evt);
            }
        });
        getContentPane().add(buttonBersihkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 180, 35));

        labelCari.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelCari.setText("Cari");
        getContentPane().add(labelCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 40, 30));

        txtCari.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 260, 30));

        buttonCari.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonCari.setText("Cari");
        buttonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariActionPerformed(evt);
            }
        });
        getContentPane().add(buttonCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 90, 30));

        buttonRefresh.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonRefresh.setText("Refresh");
        buttonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(buttonRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 100, 30));

        buttonBack.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        getContentPane().add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 380, 40));

        tableLapangan.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        tableLapangan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Alamat", "Jenis", "Status", "Harga/Jam"
            }
        ));
        tableLapangan.setRowHeight(24);
        tableLapangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLapanganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLapangan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 710, 480));

        pack();
        setSize(new java.awt.Dimension(1180, 610));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        tambahData();
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUbahActionPerformed
        ubahData();
    }//GEN-LAST:event_buttonUbahActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
        hapusData();
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void buttonNonaktifkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNonaktifkanActionPerformed
        nonaktifkanData();
    }//GEN-LAST:event_buttonNonaktifkanActionPerformed

    private void buttonBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBersihkanActionPerformed
        clearForm();
    }//GEN-LAST:event_buttonBersihkanActionPerformed

    private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariActionPerformed
        cariData();
    }//GEN-LAST:event_buttonCariActionPerformed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        txtCari.setText("");
        loadData();
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        kembaliKeDashboard();
    }//GEN-LAST:event_buttonBackActionPerformed

    private void tableLapanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLapanganMouseClicked
        isiFormDariTable();
    }//GEN-LAST:event_tableLapanganMouseClicked

    private void tambahData() {
        try {
            controller.tambah(ambilDataForm(false));
            JOptionPane.showMessageDialog(this, "Data lapangan berhasil ditambahkan.");
            loadData();
            clearForm();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ubahData() {
        try {
            controller.ubah(ambilDataForm(true));
            JOptionPane.showMessageDialog(this, "Data lapangan berhasil diubah.");
            loadData();
            clearForm();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusData() {
        try {
            int id = getIdDariForm();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Yakin ingin menghapus data lapangan ini?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.hapus(id);
                JOptionPane.showMessageDialog(this, "Data lapangan berhasil dihapus.");
                loadData();
                clearForm();
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nonaktifkanData() {
        try {
            int id = getIdDariForm();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Yakin ingin menonaktifkan lapangan ini?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.nonaktifkan(id);
                JOptionPane.showMessageDialog(this, "Lapangan berhasil dinonaktifkan.");
                loadData();
                clearForm();
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cariData() {
        try {
            tampilkanData(controller.cari(txtCari.getText()));
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Lapangan ambilDataForm(boolean wajibId) {
        Lapangan lapangan = new Lapangan();
        if (wajibId) {
            lapangan.setIdLapangan(getIdDariForm());
        }
        lapangan.setNamaLapangan(txtNamaLapangan.getText().trim());
        lapangan.setAlamatLapangan(txtAlamatLapangan.getText().trim());
        lapangan.setJenisLapangan(String.valueOf(cmbJenisLapangan.getSelectedItem()));
        lapangan.setStatusLapangan(String.valueOf(cmbStatusLapangan.getSelectedItem()));
        if (!txtHargaPerJam.getText().trim().isEmpty()) {
            lapangan.setHargaPerJam(parseHarga(txtHargaPerJam.getText().trim()));
        }
        return lapangan;
    }

    private BigDecimal parseHarga(String text) {
            String cleaned = text.replace("Rp", "").replace(" ", "").trim();
        return new BigDecimal(cleaned);
    }

    private int getIdDariForm() {
        if (txtIdLapangan.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih data lapangan terlebih dahulu.");
        }
        return Integer.parseInt(txtIdLapangan.getText().trim());
    }

    private void loadData() {
        try {
            tampilkanData(controller.getAll());
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanData(List<Lapangan> data) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nama", "Alamat", "Jenis", "Status", "Harga/Jam"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Lapangan l : data) {
            model.addRow(new Object[]{
                l.getIdLapangan(),
                l.getNamaLapangan(),
                l.getAlamatLapangan(),
                l.getJenisLapangan(),
                l.getStatusLapangan(),
                l.getHargaPerJam()
            });
        }
        tableLapangan.setModel(model);
    }

    private void isiFormDariTable() {
        int row = tableLapangan.getSelectedRow();
        if (row >= 0) {
            txtIdLapangan.setText(String.valueOf(tableLapangan.getValueAt(row, 0)));
            txtNamaLapangan.setText(String.valueOf(tableLapangan.getValueAt(row, 1)));
            txtAlamatLapangan.setText(valueOrEmpty(tableLapangan.getValueAt(row, 2)));
            cmbJenisLapangan.setSelectedItem(String.valueOf(tableLapangan.getValueAt(row, 3)));
            cmbStatusLapangan.setSelectedItem(String.valueOf(tableLapangan.getValueAt(row, 4)));
            txtHargaPerJam.setText(valueOrEmpty(tableLapangan.getValueAt(row, 5)));
        }
    }

    private String valueOrEmpty(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private void clearForm() {
        txtIdLapangan.setText("");
        txtNamaLapangan.setText("");
        txtAlamatLapangan.setText("");
        cmbJenisLapangan.setSelectedIndex(0);
        cmbStatusLapangan.setSelectedIndex(0);
        txtHargaPerJam.setText("");
        tableLapangan.clearSelection();
        txtNamaLapangan.requestFocus();
    }

    private void aturAksesRole() {
        boolean superadmin = UserSession.isLoggedIn()
                && UserSession.getUser() != null
                && "SUPERADMIN".equalsIgnoreCase(UserSession.getUser().getRole());
        boolean admin = UserSession.isLoggedIn()
                && UserSession.getUser() != null
                && "ADMIN".equalsIgnoreCase(UserSession.getUser().getRole());

        txtHargaPerJam.setEditable(superadmin);
        txtHargaPerJam.setEnabled(superadmin);
        buttonTambah.setEnabled(superadmin);
        buttonHapus.setEnabled(superadmin);

        if (superadmin) {
            labelRoleInfo.setText("Login sebagai SUPERADMIN: harga dapat dibuat dan diubah.");
        } else if (admin) {
            labelRoleInfo.setText("Login sebagai ADMIN: harga dikunci, hanya data lain yang dapat diubah.");
        } else {
            labelRoleInfo.setText("Belum login: hak akses mengikuti session aplikasi.");
        }
    }

    private void kembaliKeDashboard() {
        dispose();
        if (UserSession.isLoggedIn() && UserSession.getUser() != null) {
            String role = UserSession.getUser().getRole();
            if ("SUPERADMIN".equalsIgnoreCase(role)) {
                new ViewHomeSuperadmin().setVisible(true);
            } else if ("ADMIN".equalsIgnoreCase(role)) {
                new ViewHomeAdmin().setVisible(true);
            }
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new LapanganFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonBersihkan;
    private javax.swing.JButton buttonCari;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonNonaktifkan;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton buttonUbah;
    private javax.swing.JComboBox<String> cmbJenisLapangan;
    private javax.swing.JComboBox<String> cmbStatusLapangan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAlamat;
    private javax.swing.JLabel labelCari;
    private javax.swing.JLabel labelHarga;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelJenis;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelRoleInfo;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelSubtitle;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTable tableLapangan;
    private javax.swing.JTextField txtAlamatLapangan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHargaPerJam;
    private javax.swing.JTextField txtIdLapangan;
    private javax.swing.JTextField txtNamaLapangan;
    // End of variables declaration//GEN-END:variables
}
