-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 23 Jul 2020 pada 06.02
-- Versi Server: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `travel_2017102020`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_travel_2017102020`
--

CREATE TABLE IF NOT EXISTS `tbl_travel_2017102020` (
  `kode_travel` varchar(10) NOT NULL,
  `nm_travel` varchar(40) NOT NULL,
  `keterangan` text NOT NULL,
  `harga` double NOT NULL,
  `jumlah_travel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_travel_2017102020`
--

INSERT INTO `tbl_travel_2017102020` (`kode_travel`, `nm_travel`, `keterangan`, `harga`, `jumlah_travel`) VALUES
('TRV-0001', 'CireJak', 'Tujuan Cirebon-Jakarta 1 hari', 300000, 100),
('TRV-0002', 'KunJak', 'Travel Kuningan - Jakarta', 500000, 50),
('TRV-0003', 'CirBan Express', 'Travel Cirebon - Bandung 1 hari	', 250000, 30),
('TRV-0004', 'BDG-JKT', 'Tujuan Bandung - Jakarta', 200000, 28),
('TRV-0005', 'Jaya Karta', 'Tujuan Jakarta - Jogjakarta', 350000, 30);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_travel_kembali_2017102020`
--

CREATE TABLE IF NOT EXISTS `tbl_travel_kembali_2017102020` (
  `id_kembali` int(11) NOT NULL,
  `no_sewa` varchar(15) NOT NULL,
  `tgl_kembali` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_travel_kembali_2017102020`
--

INSERT INTO `tbl_travel_kembali_2017102020` (`id_kembali`, `no_sewa`, `tgl_kembali`) VALUES
(5, 'SWA-0001', '2020-07-23'),
(6, 'SWA-0002', '2020-07-24');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_travel_sewa_2017102020`
--

CREATE TABLE IF NOT EXISTS `tbl_travel_sewa_2017102020` (
  `id_sewa` int(10) NOT NULL,
  `no_sewa` varchar(15) NOT NULL,
  `tgl_sewa` date NOT NULL,
  `nm_penyewa` varchar(40) NOT NULL,
  `kode_travel` varchar(10) NOT NULL,
  `jumlah_sewa` int(11) NOT NULL,
  `total_bayar` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_travel_sewa_2017102020`
--

INSERT INTO `tbl_travel_sewa_2017102020` (`id_sewa`, `no_sewa`, `tgl_sewa`, `nm_penyewa`, `kode_travel`, `jumlah_sewa`, `total_bayar`) VALUES
(6, 'SWA-0001', '2020-07-23', 'Ahmad', 'TRV-0001', 2, 600000),
(7, 'SWA-0002', '2020-07-24', 'Hanafi', 'TRV-0003', 5, 1250000),
(8, 'SWA-0003', '2020-07-23', 'Dhani', 'TRV-0004', 2, 400000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_user_2017102020`
--

CREATE TABLE IF NOT EXISTS `tbl_user_2017102020` (
  `id_user` varchar(10) NOT NULL,
  `nm_user` varchar(40) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_user_2017102020`
--

INSERT INTO `tbl_user_2017102020` (`id_user`, `nm_user`, `username`, `password`) VALUES
('USR-0001', 'Ahmad Hanafi', 'ahanafi', '827ccb0eea8a706c4c34a16891f84e7b'),
('USR-0002', 'Administrator', 'admin', '21232f297a57a5a743894a0e4a801fc3'),
('USR-0003', 'Siti Nur Hasanah', 'hasanah', 'e10adc3949ba59abbe56e057f20f883e'),
('USR-0004', 'Hanafi', 'hanafi', 'ddb4f9f25acf275396aa15605bb059ad');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_travel_2017102020`
--
ALTER TABLE `tbl_travel_2017102020`
  ADD PRIMARY KEY (`kode_travel`);

--
-- Indexes for table `tbl_travel_kembali_2017102020`
--
ALTER TABLE `tbl_travel_kembali_2017102020`
  ADD PRIMARY KEY (`id_kembali`);

--
-- Indexes for table `tbl_travel_sewa_2017102020`
--
ALTER TABLE `tbl_travel_sewa_2017102020`
  ADD PRIMARY KEY (`id_sewa`);

--
-- Indexes for table `tbl_user_2017102020`
--
ALTER TABLE `tbl_user_2017102020`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_travel_kembali_2017102020`
--
ALTER TABLE `tbl_travel_kembali_2017102020`
  MODIFY `id_kembali` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `tbl_travel_sewa_2017102020`
--
ALTER TABLE `tbl_travel_sewa_2017102020`
  MODIFY `id_sewa` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
