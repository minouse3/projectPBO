CREATE DATABASE IF NOT EXISTS db_lapangan;
USE db_lapangan;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS tbl_pembayaran;
DROP TABLE IF EXISTS tbl_booking;
DROP TABLE IF EXISTS tbl_promo;
DROP TABLE IF EXISTS tbl_lapangan;
DROP TABLE IF EXISTS tbl_pelanggan;
DROP TABLE IF EXISTS tbl_pengguna;

SET FOREIGN_KEY_CHECKS = 1;

-- =====================================================
-- 1. TABEL PENGGUNA
-- Untuk akun login SUPERADMIN dan ADMIN
-- =====================================================

CREATE TABLE tbl_pengguna (
    id_pengguna INT AUTO_INCREMENT PRIMARY KEY,
    nama_pengguna VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    no_hp VARCHAR(20),
    role ENUM('SUPERADMIN', 'ADMIN') NOT NULL DEFAULT 'ADMIN',
    status_akun ENUM('AKTIF', 'NONAKTIF') NOT NULL DEFAULT 'AKTIF',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_pengguna
(nama_pengguna, username, email, password, no_hp, role, status_akun)
VALUES
('Super Admin', 'superadmin', 'superadmin@lapangan.com', SHA2('super123', 256), '081111111111', 'SUPERADMIN', 'AKTIF'),
('Admin Futsal', 'adminfutsal', 'adminfutsal@lapangan.com', SHA2('admin123', 256), '082222222222', 'ADMIN', 'AKTIF'),
('Admin Badminton', 'adminbadminton', 'adminbadminton@lapangan.com', SHA2('admin123', 256), '083333333333', 'ADMIN', 'AKTIF');


-- =====================================================
-- 2. TABEL PELANGGAN
-- Untuk data pelanggan yang dibookingkan admin/superadmin
-- Pelanggan tidak login ke sistem
-- =====================================================

CREATE TABLE tbl_pelanggan (
    id_pelanggan INT AUTO_INCREMENT PRIMARY KEY,
    nama_pelanggan VARCHAR(100) NOT NULL,
    no_hp VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    alamat TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_pelanggan
(nama_pelanggan, no_hp, email, alamat)
VALUES
('Budi Santoso', '085711112222', 'budi@gmail.com', 'Semarang'),
('Siti Aminah', '085733334444', 'siti@gmail.com', 'Gunungpati'),
('Rizky Pratama', '085755556666', 'rizky@gmail.com', 'Sekaran'),
('Dewi Lestari', '085777778888', NULL, 'Ungaran');


-- =====================================================
-- 3. TABEL LAPANGAN
-- Harga hanya boleh dibuat/diedit oleh SUPERADMIN
-- ADMIN boleh edit data lain, tapi harga dikunci di aplikasi
-- =====================================================

CREATE TABLE tbl_lapangan (
    id_lapangan INT AUTO_INCREMENT PRIMARY KEY,
    nama_lapangan VARCHAR(100) NOT NULL,
    alamat_lapangan TEXT NOT NULL,
    jenis_lapangan ENUM('Badminton', 'Futsal', 'Basket', 'Voli') NOT NULL,
    status_lapangan ENUM('TERSEDIA', 'TIDAK_TERSEDIA') NOT NULL DEFAULT 'TERSEDIA',
    harga_per_jam DECIMAL(12,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_lapangan
(nama_lapangan, alamat_lapangan, jenis_lapangan, status_lapangan, harga_per_jam)
VALUES
('Lapangan Badminton A', 'Gedung Olahraga Indoor 1', 'Badminton', 'TERSEDIA', 50000),
('Lapangan Badminton B', 'Gedung Olahraga Indoor 2', 'Badminton', 'TERSEDIA', 50000),
('Lapangan Futsal A', 'Area Futsal Indoor 1', 'Futsal', 'TERSEDIA', 100000),
('Lapangan Futsal B', 'Area Futsal Indoor 2', 'Futsal', 'TERSEDIA', 90000),
('Lapangan Basket A', 'Area Outdoor Basket 1', 'Basket', 'TERSEDIA', 120000),
('Lapangan Voli A', 'Area Outdoor Voli 1', 'Voli', 'TIDAK_TERSEDIA', 80000);


-- =====================================================
-- 4. TABEL PROMO
-- Hanya bisa dikelola oleh SUPERADMIN
-- Promo berdasarkan minimal durasi booking
-- =====================================================

CREATE TABLE tbl_promo (
    id_promo INT AUTO_INCREMENT PRIMARY KEY,
    kode_promo VARCHAR(50) NOT NULL UNIQUE,
    nama_promo VARCHAR(100) NOT NULL,
    minimal_durasi_jam INT NOT NULL,
    jenis_diskon ENUM('PERSEN', 'NOMINAL') NOT NULL,
    nilai_diskon DECIMAL(12,2) NOT NULL,
    maksimal_diskon DECIMAL(12,2) NULL,
    tanggal_mulai DATE NOT NULL,
    tanggal_selesai DATE NOT NULL,
    status_promo ENUM('AKTIF', 'NONAKTIF') NOT NULL DEFAULT 'AKTIF',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_promo
(kode_promo, nama_promo, minimal_durasi_jam, jenis_diskon, nilai_diskon, maksimal_diskon, tanggal_mulai, tanggal_selesai, status_promo)
VALUES
('MAIN2JAM', 'Diskon 10% Minimal Booking 2 Jam', 2, 'PERSEN', 10, 30000, DATE_SUB(CURDATE(), INTERVAL 30 DAY), DATE_ADD(CURDATE(), INTERVAL 30 DAY), 'AKTIF'),
('MAIN3JAM', 'Potongan Rp50.000 Minimal Booking 3 Jam', 3, 'NOMINAL', 50000, NULL, DATE_SUB(CURDATE(), INTERVAL 30 DAY), DATE_ADD(CURDATE(), INTERVAL 60 DAY), 'AKTIF'),
('HEMATMALAM', 'Diskon 15% Booking Minimal 2 Jam', 2, 'PERSEN', 15, 40000, DATE_SUB(CURDATE(), INTERVAL 30 DAY), DATE_ADD(CURDATE(), INTERVAL 60 DAY), 'AKTIF'),
('PROMOLAMA', 'Promo Lama Tidak Aktif', 1, 'PERSEN', 5, 10000, DATE_SUB(CURDATE(), INTERVAL 120 DAY), DATE_SUB(CURDATE(), INTERVAL 60 DAY), 'NONAKTIF');


-- =====================================================
-- 5. TABEL BOOKING
-- DRAFT           = booking masih bisa diedit/hapus
-- PENDING_PAYMENT = booking sudah fix, menunggu pembayaran
-- SELESAI         = booking sudah lunas
-- DIBATALKAN      = booking dibatalkan
-- =====================================================

CREATE TABLE tbl_booking (
    id_booking INT AUTO_INCREMENT PRIMARY KEY,
    id_pelanggan INT NOT NULL,
    id_lapangan INT NOT NULL,
    id_pengguna INT NOT NULL,
    tanggal_booking DATE NOT NULL,
    jam_mulai TIME NOT NULL,
    durasi_jam INT NOT NULL,
    subtotal_harga DECIMAL(12,2) NOT NULL,
    status_booking ENUM('DRAFT', 'PENDING_PAYMENT', 'SELESAI', 'DIBATALKAN') NOT NULL DEFAULT 'DRAFT',
    catatan TEXT,
    confirmed_at DATETIME NULL,
    cancelled_at DATETIME NULL,
    alasan_batal TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_booking_pelanggan
        FOREIGN KEY (id_pelanggan)
        REFERENCES tbl_pelanggan(id_pelanggan)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_booking_lapangan
        FOREIGN KEY (id_lapangan)
        REFERENCES tbl_lapangan(id_lapangan)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_booking_pengguna
        FOREIGN KEY (id_pengguna)
        REFERENCES tbl_pengguna(id_pengguna)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    INDEX idx_booking_tanggal_lapangan (tanggal_booking, id_lapangan),
    INDEX idx_booking_status (status_booking),
    INDEX idx_booking_pelanggan (id_pelanggan),
    INDEX idx_booking_pengguna (id_pengguna)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_booking
(id_pelanggan, id_lapangan, id_pengguna, tanggal_booking, jam_mulai, durasi_jam, subtotal_harga, status_booking, catatan, confirmed_at)
VALUES
(1, 3, 2, CURDATE(), '08:00:00', 2, 200000, 'PENDING_PAYMENT', 'Booking futsal pagi, menunggu pembayaran', NOW()),
(2, 1, 2, CURDATE(), '10:00:00', 1, 50000, 'SELESAI', 'Booking badminton sudah lunas', NOW()),
(3, 5, 1, CURDATE(), '15:00:00', 2, 240000, 'DRAFT', 'Masih menunggu konfirmasi pelanggan', NULL),
(4, 4, 3, DATE_ADD(CURDATE(), INTERVAL 1 DAY), '19:00:00', 2, 180000, 'PENDING_PAYMENT', 'Booking futsal malam, menunggu pembayaran', NOW());


-- =====================================================
-- 6. TABEL PEMBAYARAN
-- Dibuat saat booking dikonfirmasi
--
-- Saat status PENDING:
-- admin masih bisa mengganti metode pembayaran dan promo
--
-- Saat status LUNAS:
-- pembayaran sudah selesai dan tidak boleh diedit
-- =====================================================

CREATE TABLE tbl_pembayaran (
    id_pembayaran INT AUTO_INCREMENT PRIMARY KEY,
    id_booking INT NOT NULL UNIQUE,
    metode_pembayaran ENUM('TUNAI', 'QRIS') NOT NULL,
    id_promo INT NULL,
    subtotal_harga DECIMAL(12,2) NOT NULL,
    potongan_promo DECIMAL(12,2) NOT NULL DEFAULT 0,
    total_bayar DECIMAL(12,2) NOT NULL,
    status_pembayaran ENUM('PENDING', 'LUNAS', 'DIBATALKAN') NOT NULL DEFAULT 'PENDING',
    waktu_pembayaran DATETIME NULL,
    dikonfirmasi_oleh INT NULL,
    waktu_konfirmasi DATETIME NULL,
    keterangan TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_pembayaran_booking
        FOREIGN KEY (id_booking)
        REFERENCES tbl_booking(id_booking)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_pembayaran_promo
        FOREIGN KEY (id_promo)
        REFERENCES tbl_promo(id_promo)
        ON UPDATE CASCADE
        ON DELETE SET NULL,

    CONSTRAINT fk_pembayaran_dikonfirmasi_oleh
        FOREIGN KEY (dikonfirmasi_oleh)
        REFERENCES tbl_pengguna(id_pengguna)
        ON UPDATE CASCADE
        ON DELETE SET NULL,

    INDEX idx_pembayaran_status (status_pembayaran),
    INDEX idx_pembayaran_metode (metode_pembayaran)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbl_pembayaran
(id_booking, metode_pembayaran, id_promo, subtotal_harga, potongan_promo, total_bayar, status_pembayaran, waktu_pembayaran, dikonfirmasi_oleh, waktu_konfirmasi, keterangan)
VALUES
(1, 'QRIS', 1, 200000, 20000, 180000, 'PENDING', NULL, NULL, NULL, 'Menunggu pembayaran QRIS'),
(2, 'TUNAI', NULL, 50000, 0, 50000, 'LUNAS', NOW(), 2, NOW(), 'Pembayaran tunai sudah lunas'),
(4, 'TUNAI', 1, 180000, 18000, 162000, 'PENDING', NULL, NULL, NULL, 'Menunggu pembayaran tunai');


-- =====================================================
-- QUERY BANTUAN UNTUK APLIKASI JAVA
-- =====================================================

-- Login:
-- SELECT id_pengguna, nama_pengguna, username, role, status_akun
-- FROM tbl_pengguna
-- WHERE username = ?
-- AND password = SHA2(?, 256)
-- AND status_akun = 'AKTIF';

-- Menampilkan promo aktif berdasarkan durasi booking:
-- SELECT *
-- FROM tbl_promo
-- WHERE status_promo = 'AKTIF'
-- AND CURDATE() BETWEEN tanggal_mulai AND tanggal_selesai
-- AND minimal_durasi_jam <= ?;

-- Menampilkan history pembayaran:
-- SELECT
--     pb.id_pembayaran,
--     b.id_booking,
--     pl.nama_pelanggan,
--     lp.nama_lapangan,
--     lp.jenis_lapangan,
--     b.tanggal_booking,
--     b.jam_mulai,
--     b.durasi_jam,
--     pb.metode_pembayaran,
--     pr.kode_promo,
--     pb.subtotal_harga,
--     pb.potongan_promo,
--     pb.total_bayar,
--     pb.status_pembayaran,
--     pb.waktu_pembayaran,
--     pg.nama_pengguna AS dibuat_oleh
-- FROM tbl_pembayaran pb
-- JOIN tbl_booking b ON pb.id_booking = b.id_booking
-- JOIN tbl_pelanggan pl ON b.id_pelanggan = pl.id_pelanggan
-- JOIN tbl_lapangan lp ON b.id_lapangan = lp.id_lapangan
-- JOIN tbl_pengguna pg ON b.id_pengguna = pg.id_pengguna
-- LEFT JOIN tbl_promo pr ON pb.id_promo = pr.id_promo
-- ORDER BY pb.created_at DESC;
