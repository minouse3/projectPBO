-- =========================================
-- DATABASE : db_lapangan
-- =========================================

CREATE DATABASE IF NOT EXISTS db_lapangan
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE db_lapangan;

-- =========================================
-- TABLE : users
-- =========================================

DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS lapangan;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    no_hp VARCHAR(20),
    alamat TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- =========================================
-- TABLE : lapangan
-- =========================================

CREATE TABLE lapangan (
    id_lapangan INT AUTO_INCREMENT PRIMARY KEY,
    nama_lapangan VARCHAR(100) NOT NULL,
    jenis VARCHAR(50) NOT NULL,
    harga_per_jam INT NOT NULL,
    status ENUM('Aktif', 'Nonaktif') DEFAULT 'Aktif',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- =========================================
-- TABLE : booking
-- =========================================

CREATE TABLE booking (
    id_booking INT AUTO_INCREMENT PRIMARY KEY,

    id_user INT NOT NULL,
    id_lapangan INT NOT NULL,

    tanggal DATE NOT NULL,
    jam_mulai TIME NOT NULL,

    durasi INT NOT NULL,
    total_harga INT NOT NULL,

    status ENUM('Booked', 'Selesai', 'Batal')
    DEFAULT 'Booked',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_booking_user
        FOREIGN KEY (id_user)
        REFERENCES users(id_user)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_booking_lapangan
        FOREIGN KEY (id_lapangan)
        REFERENCES lapangan(id_lapangan)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;

-- =========================================
-- DATA AWAL USERS
-- =========================================

INSERT INTO users (nama, no_hp, alamat) VALUES
('Andi Saputra', '081234567890', 'Jakarta'),
('Budi Santoso', '082345678901', 'Bekasi'),
('Citra Lestari', '083333333333', 'Depok');

-- =========================================
-- DATA AWAL LAPANGAN
-- =========================================

INSERT INTO lapangan
(nama_lapangan, jenis, harga_per_jam, status)
VALUES
('Lapangan A', 'Futsal', 100000, 'Aktif'),
('Lapangan B', 'Badminton', 50000, 'Aktif'),
('Lapangan C', 'Basket', 120000, 'Aktif'),
('Lapangan D', 'Voli', 80000, 'Aktif');

-- =========================================
-- DATA AWAL BOOKING
-- =========================================

INSERT INTO booking
(id_user, id_lapangan, tanggal, jam_mulai,
durasi, total_harga, status)
VALUES
(1, 1, '2026-05-10', '10:00:00', 2, 200000, 'Booked'),
(2, 2, '2026-05-10', '13:00:00', 1, 50000, 'Selesai');

-- =========================================
-- VIEW LAPORAN BOOKING
-- =========================================

CREATE OR REPLACE VIEW v_booking_lengkap AS
SELECT
    b.id_booking,
    u.nama AS nama_user,
    u.no_hp,
    l.nama_lapangan,
    l.jenis,
    b.tanggal,
    b.jam_mulai,
    b.durasi,
    b.total_harga,
    b.status
FROM booking b
JOIN users u
    ON b.id_user = u.id_user
JOIN lapangan l
    ON b.id_lapangan = l.id_lapangan;

-- =========================================
-- SELESAI
-- =========================================