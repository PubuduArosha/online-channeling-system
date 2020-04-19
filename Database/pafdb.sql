-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2020 at 04:22 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pafdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminID` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `name`, `password`) VALUES
(1, 'Pubudu', '1234'),
(2, 'arosha', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appointmentID` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `hospitalID` int(11) NOT NULL,
  `patientID` int(11) NOT NULL,
  `doctorID` int(11) NOT NULL,
  `refundID` int(11) DEFAULT NULL,
  `paymentID` int(11) NOT NULL,
  `appointmentStatus` varchar(8) NOT NULL DEFAULT 'new'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointmentID`, `date`, `time`, `hospitalID`, `patientID`, `doctorID`, `refundID`, `paymentID`, `appointmentStatus`) VALUES
(4, '2020-04-01', '23:49:25', 3, 1, 1, NULL, 1, 'new');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctorID` int(11) NOT NULL,
  `NIC` varchar(13) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `specification` varchar(100) NOT NULL,
  `contact` int(10) NOT NULL,
  `workDate` varchar(10) NOT NULL,
  `workTime` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `adminID` int(10) NOT NULL,
  `doctorStatus` varchar(20) NOT NULL,
  `valid` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctorID`, `NIC`, `gender`, `firstName`, `lastName`, `email`, `specification`, `contact`, `workDate`, `workTime`, `password`, `adminID`, `doctorStatus`, `valid`) VALUES
(1, '1234567890v', 'Male', 'Karunarathna', 'Gunasena', 'kg@gmail.com', 'COVID19', 761234567, 'Weekday', '8.00am-12.00p.m', '1234', 1, 'Work', 1),
(3, '1234567812v', 'Male', 'Gunasiri', 'Karunasena', 'gunasiri@gmail.com', 'Family Doctor ', 764812594, 'Weekday', '10.00pm - 12.pm', '1234', 1, 'Work', 0),
(4, '7815841258v', 'Femal', 'Kasuni', 'Galappaththi', 'kasuni@gmail.com', 'Eye specialist ', 714844523, 'WeekEnd', '8.00am-10.00p.m', '1234', 1, 'Work', 0);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `hospitalID` int(50) NOT NULL,
  `hospitalName` varchar(50) NOT NULL,
  `location` varchar(50) NOT NULL,
  `adminID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`hospitalID`, `hospitalName`, `location`, `adminID`) VALUES
(3, 'IDH Hospital', 'Angoda', 1),
(9, 'Newil Pranandu Hospital', 'Malabe', 2),
(10, 'Asiri Hospital', 'Colombo', 1);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patientID` int(11) NOT NULL,
  `NIC` varchar(13) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `contact` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patientID`, `NIC`, `firstName`, `lastName`, `email`, `address`, `password`, `city`, `contact`) VALUES
(1, '1234567813v', 'vihaga', 'nalaka', 'nv@gmail.com', 'no123, abc road, zwc.', '1234', 'galle', 715894780),
(2, '1581269774V', 'Sunil', 'Perera', 's@g.com', 'no 123, Kaduwela road, malabe.', '1234', 'malabe', 771484598),
(3, '845126472815V', 'Jaya', 'Sri', 'jaya@g.com', 'no 152, galle road, galle.', '1234', 'galle', 768495235);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentID` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `dateAndTime` datetime NOT NULL DEFAULT current_timestamp(),
  `amount` double NOT NULL,
  `paymentStatus` varchar(12) NOT NULL DEFAULT 'new'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentID`, `type`, `dateAndTime`, `amount`, `paymentStatus`) VALUES
(1, 'crd', '2020-04-08 19:49:25', 1920, 'new'),
(2, 'crd', '2020-04-08 19:49:25', 1920, 'new');

-- --------------------------------------------------------

--
-- Table structure for table `refun`
--

CREATE TABLE `refun` (
  `refunID` int(11) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `method` varchar(20) NOT NULL,
  `adminID` int(11) NOT NULL,
  `paymentID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `refun`
--

INSERT INTO `refun` (`refunID`, `amount`, `date`, `time`, `method`, `adminID`, `paymentID`) VALUES
(1, 1920, '2020-04-19', '34:50:43', 'credit', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `worksin`
--

CREATE TABLE `worksin` (
  `hospitalID` int(11) NOT NULL,
  `doctorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointmentID`),
  ADD KEY `hospitalID` (`hospitalID`),
  ADD KEY `patientID` (`patientID`),
  ADD KEY `doctorID` (`doctorID`),
  ADD KEY `paymentID` (`paymentID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctorID`),
  ADD KEY `adminID` (`adminID`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`hospitalID`),
  ADD KEY `adminID` (`adminID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patientID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentID`);

--
-- Indexes for table `refun`
--
ALTER TABLE `refun`
  ADD PRIMARY KEY (`refunID`),
  ADD KEY `adminID` (`adminID`),
  ADD KEY `paymentID` (`paymentID`);

--
-- Indexes for table `worksin`
--
ALTER TABLE `worksin`
  ADD PRIMARY KEY (`hospitalID`,`doctorID`),
  ADD KEY `hospitalID` (`hospitalID`),
  ADD KEY `doctorID` (`doctorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appointmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `doctorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `hospitalID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `patientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `refun`
--
ALTER TABLE `refun`
  MODIFY `refunID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`hospitalID`) REFERENCES `hospital` (`hospitalID`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`),
  ADD CONSTRAINT `fk3` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`doctorID`),
  ADD CONSTRAINT `fk4` FOREIGN KEY (`refundID`) REFERENCES `refun` (`refunID`),
  ADD CONSTRAINT `fk5` FOREIGN KEY (`paymentID`) REFERENCES `payment` (`paymentID`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `d1` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`);

--
-- Constraints for table `hospital`
--
ALTER TABLE `hospital`
  ADD CONSTRAINT `h1` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`);

--
-- Constraints for table `refun`
--
ALTER TABLE `refun`
  ADD CONSTRAINT `r1` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`),
  ADD CONSTRAINT `w3` FOREIGN KEY (`refunID`) REFERENCES `payment` (`paymentID`);

--
-- Constraints for table `worksin`
--
ALTER TABLE `worksin`
  ADD CONSTRAINT `w1` FOREIGN KEY (`hospitalID`) REFERENCES `hospital` (`hospitalID`),
  ADD CONSTRAINT `w2` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`doctorID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
