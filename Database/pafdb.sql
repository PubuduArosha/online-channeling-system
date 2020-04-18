-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2020 at 08:59 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `password` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `name`, `password`, `username`) VALUES
(1, 'Admin', '123456789', 'Admin 01'),
(2, 'Vetha', '987456321', 'Vetha 2');

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
  `refundID` int(11) NOT NULL,
  `paymentID` int(11) NOT NULL,
  `appointmentStatus` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointmentID`, `date`, `time`, `hospitalID`, `patientID`, `doctorID`, `refundID`, `paymentID`, `appointmentStatus`) VALUES
(2, '2020-10-24', '12:00:00', 1, 1, 3, 1, 1, 1),
(3, '2020-04-01', '16:07:04', 2, 1, 4, 2, 2, 0);

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
  `doctorStatus` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctorID`, `NIC`, `gender`, `firstName`, `lastName`, `email`, `specification`, `contact`, `workDate`, `workTime`, `password`, `adminID`, `doctorStatus`) VALUES
(3, '972586894V', 'Male', 'Washi', 'Nussry', 'nussry@gmail.com', 'MBBS - Heart Surgeon', 778968956, '2020-12-10', '12:00:56', '123456789', 1, 0),
(4, '789945689V', 'Male', 'Vetha', 'Kumar', 'raja@v.lk', 'MBBS- Neurologist', 786985236, '2020-04-30', '12:30:00', '9874656321', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `hospitalID` int(50) NOT NULL,
  `hospitalName` varchar(50) NOT NULL,
  `adminID` int(11) NOT NULL,
  `location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`hospitalID`, `hospitalName`, `adminID`, `location`) VALUES
(1, 'Navaloka', 1, 'Marudhana'),
(2, 'Hemas', 2, 'Navanthura');

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
(1, '856936891V', 'Raja', 'Bala', 'b@vetha.lk', 'Colombo 6', '123456789', 'Colombo', 778969365),
(2, '852369741V', 'Ram', 'Charan', 'ram@r.lk', 'Telungana,Andra', '789456123', 'Vaisak', 784598562);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentID` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `dateAndTime` datetime NOT NULL DEFAULT current_timestamp(),
  `amount` double NOT NULL,
  `paymentStatus` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentID`, `type`, `dateAndTime`, `amount`, `paymentStatus`) VALUES
(1, 'Credit Card', '2020-04-05 12:14:12', 5000, 'Pending'),
(2, 'Debit Card', '2020-04-18 01:01:33', 10000, 'Paid'),
(3, 'Visa Card', '2020-04-22 04:25:25', 30000, 'Failed'),
(4, 'Credit Card', '2020-07-03 12:21:25', 45000, 'Paid');

-- --------------------------------------------------------

--
-- Table structure for table `refund`
--

CREATE TABLE `refund` (
  `refundID` int(11) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `method` varchar(20) NOT NULL,
  `adminID` int(11) NOT NULL,
  `paymentID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `refund`
--

INSERT INTO `refund` (`refundID`, `amount`, `date`, `time`, `method`, `adminID`, `paymentID`) VALUES
(1, 5000, '2020-05-14', '12:00:00', 'Online', 1, 1),
(2, 800, '2020-02-14', '14:10:04', 'Online', 1, 2),
(8, 8000, '2020-03-07', '14:10:10', 'Online', 2, 4);

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
  ADD KEY `refundID` (`refundID`),
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
-- Indexes for table `refund`
--
ALTER TABLE `refund`
  ADD PRIMARY KEY (`refundID`),
  ADD KEY `adminID` (`adminID`);

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
  MODIFY `appointmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `doctorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `hospitalID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `patientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `refund`
--
ALTER TABLE `refund`
  MODIFY `refundID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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
  ADD CONSTRAINT `fk4` FOREIGN KEY (`refundID`) REFERENCES `refund` (`refundID`),
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
-- Constraints for table `refund`
--
ALTER TABLE `refund`
  ADD CONSTRAINT `r1` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`);

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
