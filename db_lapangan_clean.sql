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

-- HANYA INSERT SUPERADMIN AWAL
INSERT INTO tbl_pengguna
(nama_pengguna, username, email, password, no_hp, role, status_akun)
VALUES
('Super Admin', 'superadmin', 'superadmin@lapangan.com', SHA2('super123', 256), '081111111111', 'SUPERADMIN', 'AKTIF');


-- =====================================================
-- 2. TABEL PELANGGAN
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


-- =====================================================
-- 3. TABEL LAPANGAN
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


-- =====================================================
-- 4. TABEL PROMO
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


-- =====================================================
-- 5. TABEL BOOKING
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
        ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =====================================================
-- 6. TABEL PEMBAYARAN
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
        ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
