package View;

import Controller.PelangganController;
import Model.Pelanggan;
import Model.UserSession;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PelangganFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PelangganFrame.class.getName());
    private final PelangganController controller = new PelangganController();

    public PelangganFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        labelSubtitle = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        txtIdPelanggan = new javax.swing.JTextField();
        labelNama = new javax.swing.JLabel();
        txtNamaPelanggan = new javax.swing.JTextField();
        labelNoHp = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        labelAlamat = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        buttonTambah = new javax.swing.JButton();
        buttonUbah = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonBersihkan = new javax.swing.JButton();
        labelCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        buttonCari = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePelanggan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Pelanggan");
        getContentPane().setBackground(new java.awt.Color(220, 224, 229));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitle.setFont(new java.awt.Font("sansserif", 1, 28)); // NOI18N
        labelTitle.setText("Data Pelanggan");
        getContentPane().add(labelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, 40));

        labelSubtitle.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        labelSubtitle.setText("Kelola data pelanggan untuk proses booking lapangan");
        getContentPane().add(labelSubtitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 430, 25));

        labelId.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelId.setText("ID");
        getContentPane().add(labelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, 25));

        txtIdPelanggan.setEditable(false);
        txtIdPelanggan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtIdPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 230, 30));

        labelNama.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelNama.setText("Nama Pelanggan*");
        getContentPane().add(labelNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 160, 25));

        txtNamaPelanggan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtNamaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 230, 30));

        labelNoHp.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelNoHp.setText("No HP*");
        getContentPane().add(labelNoHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, 25));

        txtNoHp.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtNoHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 230, 30));

        labelEmail.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelEmail.setText("Email");
        getContentPane().add(labelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 160, 25));

        txtEmail.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 230, 30));

        labelAlamat.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelAlamat.setText("Alamat");
        getContentPane().add(labelAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 160, 25));

        txtAlamat.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        getContentPane().add(txtAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 230, 30));

        buttonTambah.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });
        getContentPane().add(buttonTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 120, 35));

        buttonUbah.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonUbah.setText("Ubah");
        buttonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUbahActionPerformed(evt);
            }
        });
        getContentPane().add(buttonUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 120, 35));

        buttonHapus.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonHapus.setText("Hapus");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });
        getContentPane().add(buttonHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 120, 35));

        buttonBersihkan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        buttonBersihkan.setText("Bersihkan");
        buttonBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBersihkanActionPerformed(evt);
            }
        });
        getContentPane().add(buttonBersihkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 375, 380, 35));

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
        getContentPane().add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 380, 40));

        tablePelanggan.setFont(new java.awt.Font("sansserif", 0, 13)); // NOI18N
        tablePelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "No HP", "Email", "Alamat"
            }
        ));
        tablePelanggan.setRowHeight(24);
        tablePelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePelanggan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 650, 470));

        pack();
        setSize(new java.awt.Dimension(1120, 600));
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

    private void tablePelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePelangganMouseClicked
        isiFormDariTable();
    }//GEN-LAST:event_tablePelangganMouseClicked

    private void tambahData() {
        try {
            controller.tambah(ambilDataForm(false));
            JOptionPane.showMessageDialog(this, "Data pelanggan berhasil ditambahkan.");
            loadData();
            clearForm();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ubahData() {
        try {
            controller.ubah(ambilDataForm(true));
            JOptionPane.showMessageDialog(this, "Data pelanggan berhasil diubah.");
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
                    "Yakin ingin menghapus data pelanggan ini?",
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.hapus(id);
                JOptionPane.showMessageDialog(this, "Data pelanggan berhasil dihapus.");
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

    private Pelanggan ambilDataForm(boolean wajibId) {
        Pelanggan pelanggan = new Pelanggan();
        if (wajibId) {
            pelanggan.setIdPelanggan(getIdDariForm());
        }
        pelanggan.setNamaPelanggan(txtNamaPelanggan.getText().trim());
        pelanggan.setNoHp(txtNoHp.getText().trim());
        pelanggan.setEmail(txtEmail.getText().trim());
        pelanggan.setAlamat(txtAlamat.getText().trim());
        return pelanggan;
    }

    private int getIdDariForm() {
        if (txtIdPelanggan.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Pilih data pelanggan terlebih dahulu.");
        }
        return Integer.parseInt(txtIdPelanggan.getText().trim());
    }

    private void loadData() {
        try {
            tampilkanData(controller.getAll());
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanData(List<Pelanggan> data) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nama", "No HP", "Email", "Alamat"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Pelanggan p : data) {
            model.addRow(new Object[]{
                p.getIdPelanggan(),
                p.getNamaPelanggan(),
                p.getNoHp(),
                p.getEmail(),
                p.getAlamat()
            });
        }
        tablePelanggan.setModel(model);
    }

    private void isiFormDariTable() {
        int row = tablePelanggan.getSelectedRow();
        if (row >= 0) {
            txtIdPelanggan.setText(String.valueOf(tablePelanggan.getValueAt(row, 0)));
            txtNamaPelanggan.setText(String.valueOf(tablePelanggan.getValueAt(row, 1)));
            txtNoHp.setText(String.valueOf(tablePelanggan.getValueAt(row, 2)));
            txtEmail.setText(valueOrEmpty(tablePelanggan.getValueAt(row, 3)));
            txtAlamat.setText(valueOrEmpty(tablePelanggan.getValueAt(row, 4)));
        }
    }

    private String valueOrEmpty(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private void clearForm() {
        txtIdPelanggan.setText("");
        txtNamaPelanggan.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtAlamat.setText("");
        tablePelanggan.clearSelection();
        txtNamaPelanggan.requestFocus();
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
        java.awt.EventQueue.invokeLater(() -> new PelangganFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonBersihkan;
    private javax.swing.JButton buttonCari;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton buttonUbah;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAlamat;
    private javax.swing.JLabel labelCari;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelNoHp;
    private javax.swing.JLabel labelSubtitle;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTable tablePelanggan;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNoHp;
    // End of variables declaration//GEN-END:variables
}
