-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2019 at 11:44 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sprei`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id_penjualan` varchar(5) NOT NULL,
  `jumlah` smallint(6) NOT NULL,
  `harga` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id_penjualan`, `jumlah`, `harga`, `total`, `tanggal`) VALUES
('J001', 1, 90000, 90000, '2019-12-18 17:12:37'),
('J002', 1, 78000, 78000, '2019-12-18 17:12:37'),
('J003', 1, 85000, 85000, '2019-12-18 17:12:37'),
('J004', 1, 85000, 85000, '2019-12-18 17:12:38'),
('J005', 1, 75000, 75000, '2019-12-18 17:12:38'),
('J006', 1, 70000, 70000, '2019-12-18 17:12:38'),
('J007', 1, 95000, 95000, '2019-12-18 17:12:38'),
('J008', 1, 85000, 85000, '2019-12-18 17:12:39');

-- --------------------------------------------------------

--
-- Table structure for table `motif`
--

CREATE TABLE `motif` (
  `id_motif` varchar(4) NOT NULL,
  `motif` varchar(50) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `motif`
--

INSERT INTO `motif` (`id_motif`, `motif`, `tanggal`) VALUES
('M01', 'Polos', '2019-12-11 08:19:20'),
('M02', 'Anak - Anak', '2019-12-11 08:19:20'),
('M03', 'Dewasa', '2019-12-11 08:19:38'),
('M04', 'Abstrak', '2019-12-11 08:19:38');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` varchar(3) NOT NULL,
  `nama_pelanggan` varchar(50) NOT NULL,
  `alamat` varchar(80) NOT NULL,
  `kontak` varchar(20) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `alamat`, `kontak`, `tanggal`) VALUES
('P01', 'Andik Saputro', 'Jl. Sidumuncul No.10 ', '09182831923', '2019-12-18 17:12:37');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` varchar(5) NOT NULL,
  `id_motif` varchar(4) DEFAULT NULL,
  `id_ukuran` varchar(3) DEFAULT NULL,
  `id_pelanggan` varchar(3) DEFAULT NULL,
  `total` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `id_motif`, `id_ukuran`, `id_pelanggan`, `total`, `tanggal`) VALUES
('J001', 'M01', 'U01', 'P01', 90000, '2019-12-18 17:12:37'),
('J002', 'M02', 'U01', 'P01', 78000, '2019-12-18 17:12:37'),
('J003', 'M03', 'U01', 'P01', 85000, '2019-12-18 17:12:37'),
('J004', 'M04', 'U01', 'P01', 85000, '2019-12-18 17:12:38'),
('J005', 'M01', 'U02', 'P01', 75000, '2019-12-18 17:12:38'),
('J006', 'M02', 'U02', 'P01', 70000, '2019-12-18 17:12:38'),
('J007', 'M03', 'U02', 'P01', 95000, '2019-12-18 17:12:38'),
('J008', 'M04', 'U02', 'P01', 85000, '2019-12-18 17:12:39');

-- --------------------------------------------------------

--
-- Table structure for table `stok_harga`
--

CREATE TABLE `stok_harga` (
  `id_stok_harga` varchar(4) NOT NULL,
  `id_motif` varchar(4) NOT NULL,
  `id_ukuran` varchar(3) NOT NULL,
  `stok` mediumint(9) NOT NULL,
  `harga` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stok_harga`
--

INSERT INTO `stok_harga` (`id_stok_harga`, `id_motif`, `id_ukuran`, `stok`, `harga`, `tanggal`) VALUES
('SH01', 'M01', 'U01', 26, 90000, '2019-12-11 08:34:42'),
('SH02', 'M01', 'U02', 11, 75000, '2019-12-11 08:34:42'),
('SH03', 'M01', 'U03', 16, 90000, '2019-12-11 08:35:12'),
('SH04', 'M01', 'U04', 14, 85000, '2019-12-11 08:35:12'),
('SH05', 'M02', 'U01', 15, 78000, '2019-12-11 08:35:47'),
('SH06', 'M02', 'U02', 13, 70000, '2019-12-11 08:35:47'),
('SH07', 'M02', 'U03', 16, 85000, '2019-12-11 08:36:40'),
('SH08', 'M02', 'U04', 16, 85000, '2019-12-11 08:36:40'),
('SH09', 'M03', 'U01', 15, 85000, '2019-12-11 08:37:22'),
('SH10', 'M03', 'U02', 15, 95000, '2019-12-11 08:37:22'),
('SH11', 'M03', 'U03', 19, 70000, '2019-12-11 08:37:49'),
('SH12', 'M03', 'U04', 20, 85000, '2019-12-11 08:37:49'),
('SH13', 'M04', 'U01', 15, 85000, '2019-12-11 08:38:19'),
('SH14', 'M04', 'U02', 17, 85000, '2019-12-11 08:38:19'),
('SH15', 'M04', 'U03', 17, 75000, '2019-12-11 08:39:13'),
('SH16', 'M04', 'U04', 18, 85000, '2019-12-11 08:39:13');

-- --------------------------------------------------------

--
-- Table structure for table `ukuran`
--

CREATE TABLE `ukuran` (
  `id_ukuran` varchar(3) NOT NULL,
  `ukuran` varchar(12) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ukuran`
--

INSERT INTO `ukuran` (`id_ukuran`, `ukuran`, `tanggal`) VALUES
('U01', '120x200x20', '2019-12-11 08:18:05'),
('U02', '160x200x20', '2019-12-11 08:18:05'),
('U03', '180x200x20', '2019-12-11 08:18:36'),
('U04', '200x200x20', '2019-12-11 08:18:36');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(3) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`) VALUES
('U01', 'dwikusumo', 'dwikusumo'),
('U02', 'sonya', 'sonya');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD KEY `id_penjualan` (`id_penjualan`),
  ADD KEY `id_penjualan_2` (`id_penjualan`);

--
-- Indexes for table `motif`
--
ALTER TABLE `motif`
  ADD PRIMARY KEY (`id_motif`),
  ADD KEY `id_motif` (`id_motif`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_penjualan` (`id_penjualan`),
  ADD KEY `id_motif` (`id_motif`,`id_ukuran`,`id_pelanggan`),
  ADD KEY `id_ukuran` (`id_ukuran`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `stok_harga`
--
ALTER TABLE `stok_harga`
  ADD PRIMARY KEY (`id_stok_harga`),
  ADD KEY `id_stok` (`id_stok_harga`,`id_motif`,`id_ukuran`),
  ADD KEY `id_motif` (`id_motif`),
  ADD KEY `id_ukuran` (`id_ukuran`);

--
-- Indexes for table `ukuran`
--
ALTER TABLE `ukuran`
  ADD PRIMARY KEY (`id_ukuran`),
  ADD KEY `id_ukuran` (`id_ukuran`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_user` (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id_penjualan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_motif`) REFERENCES `motif` (`id_motif`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`id_ukuran`) REFERENCES `ukuran` (`id_ukuran`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `penjualan_ibfk_3` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `stok_harga`
--
ALTER TABLE `stok_harga`
  ADD CONSTRAINT `stok_harga_ibfk_1` FOREIGN KEY (`id_motif`) REFERENCES `motif` (`id_motif`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stok_harga_ibfk_2` FOREIGN KEY (`id_ukuran`) REFERENCES `ukuran` (`id_ukuran`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
