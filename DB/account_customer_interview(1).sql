-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 15, 2023 at 06:58 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `account_customer_interview`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `accountId` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `accountNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`accountId`, `customerId`, `accountNumber`, `createdDate`, `updatedDate`) VALUES
(1, 1, '6037997443743321', '2023-08-15 04:57:25', '2023-08-15 04:57:25'),
(2, 2, '6037997443743322', '2023-08-15 04:57:31', '2023-08-15 04:57:31'),
(3, 3, '6037997443743323', '2023-08-15 04:57:47', '2023-08-15 04:57:47'),
(4, 1, '6037997443743324', '2023-08-15 04:57:56', '2023-08-15 04:57:56'),
(5, 3, '6037997443743325', '2023-08-15 04:58:04', '2023-08-15 04:58:04');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customerId` int(11) NOT NULL COMMENT 'This is customer id',
  `firstName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'First name of customer',
  `lastName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Last name of customer',
  `idNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'National identification number of customer',
  `phoneNumber` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Number of customer''s phone',
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Customer''s address',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Customer''s email',
  `birthDate` date DEFAULT NULL COMMENT 'Customer''s birth day',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'Customer''s gender',
  `createdDate` timestamp NULL DEFAULT NULL COMMENT 'Date of creation',
  `updatedDate` timestamp NULL DEFAULT NULL COMMENT 'Date of update it'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customerId`, `firstName`, `lastName`, `idNumber`, `phoneNumber`, `address`, `email`, `birthDate`, `gender`, `createdDate`, `updatedDate`) VALUES
(1, 'Roze', 'John', '0910182183', '09014700708', 'keshavarz, koche Vernus', 'roze.john@gmail.com', '1999-08-13', 1, '2023-08-14 20:00:14', '2023-08-14 20:02:55'),
(2, 'Sam', 'Bross', '0910182184', '09014700709', 'Vanak square', 'sam.bross@gmail.com', '1999-07-11', 2, '2023-08-14 20:00:19', '2023-08-14 20:03:09'),
(3, 'Nazi', 'Hamil', '0910182185', '09014700710', 'Tajrish square', 'nazi.hamil@gmail.com', '1999-06-09', 1, '2023-08-14 20:01:36', '2023-08-14 20:03:53');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`accountId`),
  ADD KEY `accounts_ibfk_1` (`customerId`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `accountId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'This is customer id', AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customers` (`customerId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
