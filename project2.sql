-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 25, 2019 lúc 07:13 PM
-- Phiên bản máy phục vụ: 10.1.38-MariaDB
-- Phiên bản PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `project2`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `ID` int(11) NOT NULL,
  `acc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `accpost` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `time` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `IDpost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `comment`
--

INSERT INTO `comment` (`ID`, `acc`, `accpost`, `content`, `date`, `time`, `name`, `IDpost`) VALUES
(1, '0387326677', '0387326677', 'Mình nhận nhé bạn.', '0000-00-00', '00:00:00', 'Tạ Đức Phú', 3),
(2, '0333555444', '0387326677', 'maa', '10/05/2019', '06:46:22', 'Phú', 3),
(3, '0333555444', '0387326677', 'maa', '10/05/2019', '06:46:38', 'Phú', 3),
(4, '0387326677', '0387326677', 'hello', '16/05/2019', '09:10:08', 'Tạ Đức Phú', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `manager`
--

CREATE TABLE `manager` (
  `account` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `manager`
--

INSERT INTO `manager` (`account`, `password`, `name`) VALUES
('0917326977', '123456', 'Nguyễn Văn A');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `post`
--

CREATE TABLE `post` (
  `ID` int(11) NOT NULL,
  `acc` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `salary` int(10) NOT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `place` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `time` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `post`
--

INSERT INTO `post` (`ID`, `acc`, `salary`, `address`, `place`, `content`, `date`, `time`, `name`) VALUES
(3, '0387326677', 15000, 'ccc', 'Hai Bà Trưng', 'Nhà mình đang muốn tuyển người giúp việc. Yêu cầu:\r\n+ Vệ sinh phòng khách, phòng vệ sinh, phòng ngủ.\r\n+ Giặt giũ, phơi quần áo.\r\n+ Nấu bữa cơm trưa\r\n+ Thời gian: Từ 9h sáng đến 4h chiều.', '5/4/2019', '08:17:33', 'Tạ Đức Phú'),
(8, '0387326677', 12000, 'bbb', 'Cầu Giấy', 'aaa', '25/05/2019', '05:01:47', 'Tạ Đức Phú');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `save`
--

CREATE TABLE `save` (
  `account` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `IDpost` int(11) NOT NULL,
  `accPost` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `save`
--

INSERT INTO `save` (`account`, `IDpost`, `accPost`) VALUES
('0333555444', 3, '0387326677'),
('0387326677', 3, '0387326677');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `account` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`account`, `password`, `name`) VALUES
('0333555444', '1235', 'Phú'),
('0387326677', '123456', 'Tạ Đức Phú');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `acccomment` (`acc`),
  ADD KEY `acccomment1` (`accpost`),
  ADD KEY `idpost` (`IDpost`);

--
-- Chỉ mục cho bảng `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`account`);

--
-- Chỉ mục cho bảng `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `acc` (`acc`);

--
-- Chỉ mục cho bảng `save`
--
ALTER TABLE `save`
  ADD PRIMARY KEY (`account`,`IDpost`),
  ADD KEY `IDpostt` (`IDpost`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`account`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `post`
--
ALTER TABLE `post`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `idpost` FOREIGN KEY (`IDpost`) REFERENCES `post` (`ID`);

--
-- Các ràng buộc cho bảng `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `acc` FOREIGN KEY (`acc`) REFERENCES `user` (`account`);

--
-- Các ràng buộc cho bảng `save`
--
ALTER TABLE `save`
  ADD CONSTRAINT `IDpostt` FOREIGN KEY (`IDpost`) REFERENCES `post` (`ID`),
  ADD CONSTRAINT `account` FOREIGN KEY (`account`) REFERENCES `user` (`account`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
