CREATE DATABASE [EduTrainingMgmtV5]
GO

USE [EduTrainingMgmtV5]
GO
/****** Object:  Table [dbo].[GiaoVien]    Script Date: 06/18/2017 21:22:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiaoVien](
	[GiaoVienID] [int] NOT NULL,
	[TenGiaoVien] [nvarchar](255) NULL,
	[CMTND] [nvarchar](255) NULL,
	[DiaChi] [nvarchar](255) NULL,
	[GiaoVienQL] [int] NULL,
	[KhoaID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[GiaoVienID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[GiaoVien] ([GiaoVienID], [TenGiaoVien], [CMTND], [DiaChi], [GiaoVienQL], [KhoaID]) VALUES (1, N'Barbara Liskov', N'123', N'77 Massachusetts Avenue', NULL, 1)
INSERT [dbo].[GiaoVien] ([GiaoVienID], [TenGiaoVien], [CMTND], [DiaChi], [GiaoVienQL], [KhoaID]) VALUES (2, N'Joel Moses', N'124', N'Cambridge, MA 02139-4307', 1, 2)
INSERT [dbo].[GiaoVien] ([GiaoVienID], [TenGiaoVien], [CMTND], [DiaChi], [GiaoVienQL], [KhoaID]) VALUES (3, N'Frank Wilczek', N'125-125', N' TRS (711)', 1, 3)
INSERT [dbo].[GiaoVien] ([GiaoVienID], [TenGiaoVien], [CMTND], [DiaChi], [GiaoVienQL], [KhoaID]) VALUES (4, N'Morris Halle', N'126', N'Congolate', 1, 4)
INSERT [dbo].[GiaoVien] ([GiaoVienID], [TenGiaoVien], [CMTND], [DiaChi], [GiaoVienQL], [KhoaID]) VALUES (5, N'Walle J. H. Nauta', N'127', N'Massachuset - American', 1, 1)
INSERT [dbo].[GiaoVien] ([GiaoVienID], [TenGiaoVien], [CMTND], [DiaChi], [GiaoVienQL], [KhoaID]) VALUES (6, N'Walter A. Rosenblith', N'128', N'Cambridge', 1, 1)
/****** Object:  Table [dbo].[TruongDaiHoc]    Script Date: 06/18/2017 21:22:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TruongDaiHoc](
	[TruongID] [int] NOT NULL,
	[TenTruong] [nvarchar](255) NULL,
	[DiaChi] [nvarchar](255) NULL,
	[Email] [nvarchar](100) NULL,
	[Phone] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[TruongID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[TruongDaiHoc] ([TruongID], [TenTruong], [DiaChi], [Email], [Phone]) VALUES (1, N'Tech Space for  Kids', N'Samibazanga, Luanda', N'techspaceforkids@gmail.com', N'37-1410-1983')
INSERT [dbo].[TruongDaiHoc] ([TruongID], [TenTruong], [DiaChi], [Email], [Phone]) VALUES (2, N'Tech Space for Adults', N'Luanda 2', N'techspaceforadults@gmail.com', N'38-1410-1983')
INSERT [dbo].[TruongDaiHoc] ([TruongID], [TenTruong], [DiaChi], [Email], [Phone]) VALUES (3, N'Tech Space for Seniors', N'Luanda 3', N'techspaceforseniors@gmail.com', N'39-1410-1983')
/****** Object:  Table [dbo].[Khoa]    Script Date: 06/18/2017 21:22:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Khoa](
	[KhoaID] [int] NOT NULL,
	[TenKhoa] [nvarchar](255) NULL,
	[TruongID] [int] NULL,
	[GiaoVienQL] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[KhoaID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Khoa] ([KhoaID], [TenKhoa], [TruongID], [GiaoVienQL]) VALUES (1, N'Computer Science', 1, 1)
INSERT [dbo].[Khoa] ([KhoaID], [TenKhoa], [TruongID], [GiaoVienQL]) VALUES (2, N'Physics', 1, 2)
INSERT [dbo].[Khoa] ([KhoaID], [TenKhoa], [TruongID], [GiaoVienQL]) VALUES (3, N'Nutrition and Food Science', 1, 3)
INSERT [dbo].[Khoa] ([KhoaID], [TenKhoa], [TruongID], [GiaoVienQL]) VALUES (4, N'Linguistics', 1, 4)
INSERT [dbo].[Khoa] ([KhoaID], [TenKhoa], [TruongID], [GiaoVienQL]) VALUES (5, N'Test Dept 5 -1-160617', 2, 3)
/****** Object:  Table [dbo].[ChuyenNghanh]    Script Date: 06/18/2017 21:22:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuyenNghanh](
	[NghanhID] [int] NOT NULL,
	[TenNghanh] [nvarchar](255) NULL,
	[KhoaID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[NghanhID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChuyenNghanh] ([NghanhID], [TenNghanh], [KhoaID]) VALUES (1, N'Software Engineering', 1)
INSERT [dbo].[ChuyenNghanh] ([NghanhID], [TenNghanh], [KhoaID]) VALUES (2, N'Computing and Networking', 1)
INSERT [dbo].[ChuyenNghanh] ([NghanhID], [TenNghanh], [KhoaID]) VALUES (3, N'Gamification and Programming', 1)
INSERT [dbo].[ChuyenNghanh] ([NghanhID], [TenNghanh], [KhoaID]) VALUES (4, N'Robotics-edited', 1)
INSERT [dbo].[ChuyenNghanh] ([NghanhID], [TenNghanh], [KhoaID]) VALUES (6, N'Data communication', 5)
INSERT [dbo].[ChuyenNghanh] ([NghanhID], [TenNghanh], [KhoaID]) VALUES (7, N'Virtual Reality', 3)
/****** Object:  StoredProcedure [dbo].[spGetMajorByName]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetMajorByName]
	@MajorName NVARCHAR(255)
AS
BEGIN
	SELECT  ChuyenNghanh.NghanhID  AS [Major ID],
			ChuyenNghanh.TenNghanh AS [Major Name],
			ChuyenNghanh.KhoaID AS [Dept ID],
			Khoa.TenKhoa AS [Dept Name]
	FROM ChuyenNghanh INNER JOIN Khoa
		ON ChuyenNghanh.KhoaID = Khoa.KhoaID
	WHERE ChuyenNghanh.TenNghanh LIKE '%' + @MajorName + '%';
END
GO
/****** Object:  StoredProcedure [dbo].[spGetMajorByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetMajorByID]
(
	@MajorID INT
)
AS
BEGIN
	SELECT ChuyenNghanh.NghanhID AS [Major ID],
			ChuyenNghanh.TenNghanh AS [Major Name],
			Khoa.KhoaID AS [Dept ID],
			Khoa.TenKhoa
	FROM dbo.ChuyenNghanh INNER JOIN Khoa
		ON ChuyenNghanh.KhoaID = Khoa.KhoaID
	WHERE ChuyenNghanh.NghanhID = @MajorID;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetLecturerByName]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetLecturerByName]
	@LecturerName NVARCHAR(255)
AS
BEGIN
	SELECT GiaoVienID AS [Lecturer ID],
			TenGiaoVien AS [Lecturer Name],
			CMTND AS [SSN],
			DiaChi AS [Address],
			GiaoVienQL AS [Supervised By ID],		
			KhoaID AS [Dept ID]
	FROM GiaoVien
	WHERE TenGiaoVien LIKE '%' + @LecturerName + '%';
END
GO
/****** Object:  StoredProcedure [dbo].[spGetLecturerByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetLecturerByID]
	@LecturerID INT
AS
BEGIN
	SELECT GiaoVienID AS [Lecturer ID],
			TenGiaoVien AS [Lecturer Name],
			CMTND AS [SSN],
			DiaChi AS [Address],
			GiaoVienQL AS [Supervised By ID],		
			KhoaID AS [Dept ID]
	FROM GiaoVien
	WHERE GiaoVienID = @LecturerID;
END
GO
/****** Object:  Table [dbo].[KyThi]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KyThi](
	[KyThiID] [int] NOT NULL,
	[TenKyThi] [nvarchar](100) NULL,
	[NgayThi] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[KyThiID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (1, N'Midterm Test for Teaching Period 1 /2016', CAST(0x153B0B00 AS Date))
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (2, N'Midterm Test for Teaching Period 2/2016', CAST(0x903B0B00 AS Date))
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (3, N'Final Test for Teaching Period 1/2016', CAST(0x533B0B00 AS Date))
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (4, N'Final Test for Teaching Period 2/2016', CAST(0xCD3B0B00 AS Date))
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (5, N'Midterm Test for Teaching Period 1/2017', CAST(0x833C0B00 AS Date))
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (6, N'Midterm Test for Teaching Period 2/2017-edited', CAST(0x903B0B00 AS Date))
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (7, N'Final Test for Teaching period 1/2017', CAST(0xC03C0B00 AS Date))
INSERT [dbo].[KyThi] ([KyThiID], [TenKyThi], [NgayThi]) VALUES (8, N'Final Test for Teaching Period 2/2017', CAST(0x3A3D0B00 AS Date))
/****** Object:  Table [dbo].[SinhVien]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SinhVien](
	[SinhVienID] [int] NOT NULL,
	[TenSinhVien] [nvarchar](255) NULL,
	[DOB] [date] NULL,
	[DiaChi] [nvarchar](255) NULL,
	[TruongID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SinhVienID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (1, N'Kim Anh Nguyen', CAST(0xC30C0B00 AS Date), N'Hanoi 1, Vietnam1', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (2, N'Kim Thanh Nguyen', CAST(0xE40C0B00 AS Date), N'Hanoi 2, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (3, N'Tim Nguyen', CAST(0xF7120B00 AS Date), N'Hanoi 1, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (4, N'Tam Nguyen Thanh', CAST(0xBC120B00 AS Date), N'Hanoi 2, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (5, N'Hang Nga Do', CAST(0x641E0B00 AS Date), N'Hanoi 1, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (6, N'Thuy Duong Nguyen', CAST(0x4D210B00 AS Date), N'Hai Duong, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (7, N'Quoc Vuong Nguyen Tran', CAST(0xC92C0B00 AS Date), N'Bac Giang, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (8, N'Tuan Kiet Le Bon 1', CAST(0xB1350B00 AS Date), N'Hanoi, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (10, N'Pham Thanh Nhan', CAST(0x07060B00 AS Date), N'Hanoi, Vietnam', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (11, N'Lee Hui Ching', CAST(0xC4150B00 AS Date), N'Malaysia', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (14, N'Kim Jil Hyun', CAST(0xD7180B00 AS Date), N'Korea', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (15, N'Charles Le', CAST(0xF5000B00 AS Date), N'Go Cong, Ho Chi Minh City, Vietnam', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (16, N'Boo Jil Hyun', CAST(0xE63C0B00 AS Date), N'South Korea', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (17, N'Minh Chau Le', CAST(0xAA060B00 AS Date), N'Go Cong, HCM', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (18, N'Paul Wenzel', CAST(0x82070B00 AS Date), N'Germany', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (19, N'Anna Do', CAST(0x1F100B00 AS Date), N'Nha Trang, Vietnam', 1)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (20, N'Rosa Tran', CAST(0x2BED0A00 AS Date), N'Soc Son, Hanoi', 3)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (21, N'Maria Thuy Duong', CAST(0xE30A0B00 AS Date), N'Hai Duong', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (22, N'Ngoc Mai Nguyen', CAST(0x3B200B00 AS Date), N'Lien Ha, Dong Anh, Hanoi', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (23, N'Ansley Dzosa', CAST(0x380F0B00 AS Date), N'India', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (26, N'Kim Nguyen', CAST(0xE40C0B00 AS Date), N'Dong Anh, Ha Noi', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (27, N'Kim Anh Nguyen Le', CAST(0xE40C0B00 AS Date), N'HCM-VN', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (29, N'Nguyen Kim Thanh', CAST(0xE40C0B00 AS Date), N'Van Ha, Dong Anh, Hanoi, Vietnam', 2)
INSERT [dbo].[SinhVien] ([SinhVienID], [TenSinhVien], [DOB], [DiaChi], [TruongID]) VALUES (30, N'Nguyen Thanh Tam', CAST(0xF7120B00 AS Date), N'Soc Son, Hanoi, Vietnam', 2)
/****** Object:  Table [dbo].[MonHoc]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonHoc](
	[MonID] [int] NOT NULL,
	[TenMon] [nvarchar](255) NULL,
	[MieuTa] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MonID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (1, N'Operating Systems Architecture', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (2, N'Algorithms & Data Structures', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (3, N'Artificial Intelligence', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (4, N'Information Security', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (5, N'Computer Networks I', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (6, N'The Software Process', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (7, N'Embedded Systems Design & Interfacing', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (8, N'Electrical Energy Conversion & Utilisation', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (9, N'Signals, Systems & Control', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (10, N'Electronic Circuits', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (11, N'Web Information Systems', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (12, N'Service-Oriented Architectures', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (13, N'Advanced Database Systems', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (14, N'Compilers and Interpreters', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (15, N'Advanced Algorithms & Data Structures', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (16, N'Machine Learning', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (17, N'Computer Networks II', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (18, N'Advanced Computer and Network Security', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (19, N'Distributed Computing', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (20, N'Advanced Embedded Systems', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (21, N'Advanced Human-Computer Interaction', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (22, N'Systems Engineering', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (23, N'Engineering Project Management', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (24, N'Data Mining', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (25, N'Advanced Techniques for High Dimensional Data', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (26, N'Management Communication', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (27, N'Reasoning about Programs', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (28, N'Concurrency: Theory and Practice', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (29, N'Information Retrieval and Web Search', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (30, N'Systems Safety Engineering', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (31, N'Advanced Computational Techniques in Engineering', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (32, N'Special Topics in Computer Science 7A', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (33, N'Special Topics in Computer Science 7B', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (34, N'Advanced Topics in Software Engineering A', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (35, N'Advanced Topics in Software Engineering B', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (36, N'Special Topics in Design Computing 7A', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (37, N'Special Topics in Design Computing 7B', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (38, N'Advanced Topics in Engineering I', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (39, N'Advanced Topics in Engineering II', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (40, N'Engineering Postgraduate Project B', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (41, N'Economics of Innovation & Entrepreneurship', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (42, N'Systems Thinking for Sustainability', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (43, N'Carbon & Energy Management', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (44, N'Environmental Finance', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (45, N'Operating International Business', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (46, N'Globalisation & the World Economy', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (47, N'Managing the International Enterprise', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (48, N'Doing Business in Asia', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (49, N'Management Consulting in Asian Business', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (50, N'Social Marketing', NULL)
INSERT [dbo].[MonHoc] ([MonID], [TenMon], [MieuTa]) VALUES (51, N'Test 51-> 511', N'Description Test 51->511')
/****** Object:  Table [dbo].[LopTinChi]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LopTinChi](
	[LopTinChiID] [int] NOT NULL,
	[MonID] [int] NULL,
	[GiaoVienID] [int] NULL,
	[Note] [nvarchar](max) NULL,
 CONSTRAINT [PK__LopTinCh__7FE276372D27B809] PRIMARY KEY CLUSTERED 
(
	[LopTinChiID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (1, 1, 1, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (2, 2, 1, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (3, 3, 1, N'3-3-1: AI by Barbara')
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (4, 4, 2, N'4-4-2: IS for Y2017')
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (5, 5, 2, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (6, 6, 3, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (7, 7, 4, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (8, 8, 5, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (9, 9, 6, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (10, 10, 1, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (11, 11, 2, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (12, 12, 1, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (13, 13, 6, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (14, 14, 5, NULL)
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (16, 15, 4, N'16: Adv Algo & DS by Morris')
INSERT [dbo].[LopTinChi] ([LopTinChiID], [MonID], [GiaoVienID], [Note]) VALUES (17, 18, 3, N'Networking and information security topic will be conducted by Mr Frank Wk')
/****** Object:  Table [dbo].[DangKyLopTinChiSinhVien]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DangKyLopTinChiSinhVien](
	[LopTinChiSinhVienID] [int] NOT NULL,
	[LopTinChiID] [int] NOT NULL,
	[SinhvienID] [int] NOT NULL,
	[NgayDangKy] [date] NULL,
	[Note] [nvarchar](50) NULL,
 CONSTRAINT [PK_DangKyLopTinChiSinhVien] PRIMARY KEY CLUSTERED 
(
	[LopTinChiSinhVienID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (1, 1, 1, CAST(0x173B0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (2, 2, 2, CAST(0x173B0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (3, 3, 3, CAST(0x173B0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (4, 4, 4, CAST(0x363B0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (5, 1, 5, CAST(0x583B0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (6, 2, 6, CAST(0x733B0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (7, 3, 7, CAST(0x4D3C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (8, 4, 8, CAST(0xC13C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (9, 3, 8, CAST(0x493C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (10, 2, 1, CAST(0x693C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (11, 2, 2, CAST(0x4C3C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (12, 2, 3, CAST(0xA23C0B00 AS Date), N'EDITED')
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (13, 3, 4, CAST(0x693C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (14, 3, 5, CAST(0x893C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (15, 3, 6, CAST(0xA93C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (16, 3, 7, CAST(0xB13C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (17, 3, 1, CAST(0xB13C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (18, 4, 2, CAST(0x4A3C0B00 AS Date), NULL)
INSERT [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID], [LopTinChiID], [SinhvienID], [NgayDangKy], [Note]) VALUES (19, 5, 3, CAST(0x6A3C0B00 AS Date), NULL)
/****** Object:  Table [dbo].[DiemThi]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiemThi](
	[DiemThiID] [int] NOT NULL,
	[KyThiID] [int] NOT NULL,
	[LopTinChiSinhVienID] [int] NOT NULL,
	[Diem] [float] NOT NULL,
 CONSTRAINT [PK_DiemThi] PRIMARY KEY CLUSTERED 
(
	[DiemThiID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (1, 1, 1, 7.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (3, 1, 3, 7)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (4, 1, 4, 8.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (5, 1, 5, 9.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (6, 1, 6, 10)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (7, 1, 7, 10)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (8, 1, 8, 8.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (9, 1, 9, 9)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (10, 5, 1, 8)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (11, 2, 1, 5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (12, 2, 3, 5.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (13, 2, 4, 6.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (14, 2, 5, 8.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (15, 3, 1, 1)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (16, 3, 2, 3.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (17, 3, 5, 10)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (18, 3, 6, 15)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (19, 4, 7, 9.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (20, 4, 8, 8.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (21, 5, 9, 9.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (22, 5, 2, 5.8)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (23, 6, 2, 5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (24, 6, 6, 5.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (25, 7, 5, 9.8)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (26, 8, 6, 4.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (27, 1, 8, 2)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (28, 2, 9, 8.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (29, 3, 10, 7.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (30, 3, 11, 5.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (31, 4, 12, 6)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (32, 2, 2, 2)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (33, 5, 2, 3)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (34, 6, 3, 9)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (35, 7, 2, 10)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (36, 8, 3, 11.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (37, 2, 2, 3.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (38, 3, 2, 9.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (39, 4, 7, 10.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (40, 5, 8, 15.5)
INSERT [dbo].[DiemThi] ([DiemThiID], [KyThiID], [LopTinChiSinhVienID], [Diem]) VALUES (41, 4, 3, 8.3000001907348633)
/****** Object:  StoredProcedure [dbo].[spInsertAMark]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertAMark]
(-- Add the parameters for the stored procedure here
	@MarkID INT,
	@ExamID INT,
	@SubjectClassRegistrationID INT, -- Student code @ each exam
	@Mark FLOAT
)
AS
BEGIN
    -- Insert statements for procedure here
	INSERT DiemThi (DiemThiID, KyThiID, LopTinChiSinhVienID, Diem)
	VALUES (@MarkID, @ExamID,@SubjectClassRegistrationID,@Mark);
END
GO
/****** Object:  Table [dbo].[ChuyenNghanhMonHoc]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuyenNghanhMonHoc](
	[ChuyenNghanhMonHocID] [int] NOT NULL,
	[NghanhID] [int] NULL,
	[MonID] [int] NULL,
	[BatBuoc] [bit] NULL,
	[Note] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[ChuyenNghanhMonHocID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (2, 1, 2, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (3, 1, 3, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (4, 1, 4, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (5, 1, 5, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (6, 1, 6, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (7, 1, 7, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (8, 1, 8, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (9, 1, 9, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (10, 1, 10, 0, N'')
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (11, 1, 11, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (12, 1, 12, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (13, 1, 13, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (14, 1, 14, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (15, 1, 15, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (16, 1, 16, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (17, 1, 17, 0, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (18, 2, 17, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (19, 2, 18, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (20, 2, 19, 1, NULL)
INSERT [dbo].[ChuyenNghanhMonHoc] ([ChuyenNghanhMonHocID], [NghanhID], [MonID], [BatBuoc], [Note]) VALUES (21, 2, 20, 1, NULL)
/****** Object:  StoredProcedure [dbo].[spInsertAMajorSubject]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertAMajorSubject]
	-- Add the parameters for the stored procedure here
	@MajorSubjectID INT,
	@MajorID INT,
	@SubjectID INT,
	@Compulsory BIT,
	@Note NVARCHAR(MAX)
AS
BEGIN
    -- Insert statements for procedure here
	INSERT ChuyenNghanhMonHoc 
			(ChuyenNghanhMonHocID, NghanhID, MonID, BatBuoc, Note)
	VALUES (@MajorSubjectID, @MajorID, @SubjectID, @Compulsory, @Note);
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertAMajor]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spInsertAMajor]
(
	@NghanhID INT,
	@TenNghanh NVARCHAR(255),
	@KhoaID INT	
)
AS
BEGIN
	INSERT INTO ChuyenNghanh
	VALUES (@NghanhID,@TenNghanh,@KhoaID);
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertALecturer]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertALecturer]
	(-- Add the parameters for the stored procedure here
	@LecturerID INT,
	@LecturerName NVARCHAR(255),
	@SSN NVARCHAR(255),
	@Address NVARCHAR(255),
	@LecturerManager NVARCHAR(255),
	@DeptID INT
	)
AS
BEGIN
    -- Insert statements for procedure here
	INSERT GiaoVien(GiaoVienID, TenGiaoVien, CMTND, DiaChi,GiaoVienQL,KhoaID)
	VALUES (@LecturerID, @LecturerName, @SSN, @Address, @LecturerManager,@DeptID);
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertADepartment]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertADepartment]
	-- Add the parameters for the stored procedure here
	@DeptID INT, 
	@DeptName NVARCHAR(255),
	@SchoolID INT,
	@SupervisorID INT	
AS
BEGIN
    -- Insert statements for procedure here
	INSERT Khoa(KhoaID, TenKhoa, TruongID,GiaoVienQL)
	VALUES (@DeptID, @DeptName, @SchoolID, @SupervisorID);
END
GO
/****** Object:  Table [dbo].[GiaoVienMonHoc]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiaoVienMonHoc](
	[GiaoVienMonHocID] [int] NOT NULL,
	[GiaoVienID] [int] NULL,
	[MonID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[GiaoVienMonHocID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (1, 2, 1)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (2, 1, 2)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (3, 1, 3)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (4, 1, 4)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (6, 1, 6)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (7, 1, 7)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (8, 1, 8)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (9, 1, 9)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (10, 1, 10)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (11, 2, 1)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (12, 2, 3)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (14, 2, 7)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (15, 2, 9)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (16, 2, 10)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (17, 3, 1)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (18, 3, 2)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (19, 3, 3)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (20, 3, 4)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (21, 4, 1)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (22, 4, 3)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (23, 4, 12)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (25, 4, 25)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (26, 5, 1)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (27, 5, 2)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (28, 5, 3)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (30, 6, 2)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (31, 6, 3)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (32, 6, 3)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (33, 6, 4)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (34, 6, 5)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (35, 6, 6)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (36, 1, 50)
INSERT [dbo].[GiaoVienMonHoc] ([GiaoVienMonHocID], [GiaoVienID], [MonID]) VALUES (37, 3, 1)
/****** Object:  StoredProcedure [dbo].[spInsertLecturerSubject]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertLecturerSubject]
	(-- Add the parameters for the stored procedure here
	@LecturerSubjectID INT,
	@LecturerID INT,
	@SubjectID INT
	)
AS
BEGIN
    -- Insert statements for procedure here
	INSERT GiaoVienMonHoc(GiaoVienMonHocID, GiaoVienID, MonID)
	VALUES (@LecturerSubjectID, @LecturerID, @SubjectID);
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertASubjectClassRegistration]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertASubjectClassRegistration]
	-- Add the parameters for the stored procedure here
	@SubjectClassRegistrationID INT,
	@SubjectBasedClassID INT,
	@StudentID INT,
	@RegistrationDate DATE,
	@Note NVARCHAR(MAX)
AS
BEGIN
    -- Insert statements for procedure here
	INSERT DangKyLopTinChiSinhVien 
			(LopTinChiSinhVienID, LopTinChiID, 
			SinhvienID, NgayDangKy, Note)
	VALUES (@SubjectClassRegistrationID,@SubjectBasedClassID,
			@StudentID,@RegistrationDate,@Note);
END
GO
/****** Object:  StoredProcedure [dbo].[spGetDepartmentByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetDepartmentByID]
	@DeptID INT
AS
BEGIN
	SELECT KhoaID AS [Dept ID],
			TenKhoa AS [Dept Name],
			TruongID AS [School ID],
			GiaoVienQL AS [Dept Supervisor ID]
	FROM Khoa
	WHERE KhoaID = @DeptID;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetAllLecturerSubjectIDs]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetAllLecturerSubjectIDs]	
AS
BEGIN	
	SELECT DISTINCT GiaoVienMonHocID AS [LecturerSubjectID]
	FROM GiaoVienMonHoc;
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertASubjectBasedClass]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertASubjectBasedClass]
	-- Add the parameters for the stored procedure here
	@SubjectBasedClassID INT,
	@SubjectID INT,
	@LecturerID INT,
	@Note NVARCHAR(MAX)
AS
BEGIN
    -- Insert statements for procedure here
	INSERT LopTinChi (LopTinChiID, MonID, GiaoVienID, Note)
	VALUES (@SubjectBasedClassID,@SubjectID,@LecturerID,@Note);
END
GO
/****** Object:  Table [dbo].[LopNienChe]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LopNienChe](
	[LopNienCheID] [int] NOT NULL,
	[TenLop] [nvarchar](255) NULL,
	[GiaoVienChuNhiemID] [int] NULL,
	[NamHoc] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[LopNienCheID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LopNienChe] ([LopNienCheID], [TenLop], [GiaoVienChuNhiemID], [NamHoc]) VALUES (1, N'Year-oriented class 1', 2, 2016)
INSERT [dbo].[LopNienChe] ([LopNienCheID], [TenLop], [GiaoVienChuNhiemID], [NamHoc]) VALUES (2, N'Year-oriented class 2', 3, 2016)
INSERT [dbo].[LopNienChe] ([LopNienCheID], [TenLop], [GiaoVienChuNhiemID], [NamHoc]) VALUES (3, N'Year-oriented class 3', 4, 2017)
INSERT [dbo].[LopNienChe] ([LopNienCheID], [TenLop], [GiaoVienChuNhiemID], [NamHoc]) VALUES (4, N'Year-oriented class 4', 5, 2017)
INSERT [dbo].[LopNienChe] ([LopNienCheID], [TenLop], [GiaoVienChuNhiemID], [NamHoc]) VALUES (5, N'Year-oriented class 5', 6, 2017)
/****** Object:  Table [dbo].[LopNienCheGiaoVien]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LopNienCheGiaoVien](
	[LopNienCheGiaoVienID] [int] NOT NULL,
	[LopNienCheID] [int] NULL,
	[GiaoVienID] [int] NULL,
	[NgayGio] [datetime] NULL,
	[Gio] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[LopNienCheGiaoVienID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (1, 1, 1, CAST(0x0000A5F800735B40 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (2, 1, 2, CAST(0x0000A5F800A4CB80 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (3, 1, 3, CAST(0x0000A5F800E6B680 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (4, 2, 1, CAST(0x0000A5F900735B40 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (5, 2, 3, CAST(0x0000A5F900A4CB80 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (6, 2, 6, CAST(0x0000A5FA009450C0 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (7, 3, 5, CAST(0x0000A7830062E080 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (8, 3, 2, CAST(0x0000A78300E6B680 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (9, 3, 3, CAST(0x0000A78301391C40 AS DateTime), NULL)
INSERT [dbo].[LopNienCheGiaoVien] ([LopNienCheGiaoVienID], [LopNienCheID], [GiaoVienID], [NgayGio], [Gio]) VALUES (10, 4, 3, CAST(0x0000A78400A4CB80 AS DateTime), NULL)
/****** Object:  StoredProcedure [dbo].[spListAllMajors]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllMajors]
AS
BEGIN
	SELECT  ChuyenNghanh.NghanhID  AS [Major ID],
			ChuyenNghanh.TenNghanh AS [Major Name],
			ChuyenNghanh.KhoaID AS [Dept ID],
			Khoa.TenKhoa AS [Dept Name]
	FROM ChuyenNghanh INNER JOIN Khoa
		ON ChuyenNghanh.KhoaID = Khoa.KhoaID
	ORDER BY ChuyenNghanh.TenNghanh;
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllLecturers]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllLecturers]
	-- Add the parameters for the stored procedure here
AS
BEGIN
    -- Insert statements for procedure here
    -- Use Common Table Expression (CTE) to join a table with itself
    WITH cteLecturer
    AS 
	(
		SELECT * 
		FROM GiaoVien
	)
	SELECT 
		GiaoVien.GiaoVienID AS [Lecturer ID],
		GiaoVien.TenGiaoVien AS [Lecturer Name],
		GiaoVien.CMTND AS [SSN],
		GiaoVien.DiaChi AS [Address],
		GiaoVien.GiaoVienQL AS [Supervised By ID],
		cteLecturer.TenGiaoVien AS [Supervised By Name],
		
		GiaoVien.KhoaID AS [Dept ID],
		Khoa.TenKhoa
		
	FROM GiaoVien LEFT JOIN cteLecturer
		ON GiaoVien.GiaoVienQL = cteLecturer.GiaoVienID
		LEFT JOIN Khoa
		ON GiaoVien.KhoaID = Khoa.KhoaID;	
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateAMark]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateAMark]
(
	@MarkID INT,
	@ExamID INT,
	@SubjectClassRegistrationID INT, -- Student code @ each exam
	@Mark FLOAT
)
AS
BEGIN
	UPDATE DiemThi
	SET DiemThiID = @MarkID,
		KyThiID = @ExamID, 
		LopTinChiSinhVienID = @SubjectClassRegistrationID,
		Diem = @Mark
	WHERE DiemThiID = @MarkID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateAMajorSubject]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateAMajorSubject]
(
	@MajorSubjectID INT,
	@MajorID INT,
	@SubjectID INT,
	@Compulsory BIT,
	@Note NVARCHAR(MAX)
)
AS
BEGIN
	UPDATE ChuyenNghanhMonHoc
	SET ChuyenNghanhMonHocID = @MajorSubjectID,
		NghanhID = @MajorID,
		MonID = @SubjectID,
		BatBuoc = @Compulsory,
		Note = @Note	
	WHERE ChuyenNghanhMonHocID = @MajorSubjectID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateAMajor]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateAMajor]
(
	@MajorID INT,
	@MajorName NVARCHAR(255),
	@DeptID INT
)
AS
BEGIN
	UPDATE ChuyenNghanh
	SET NghanhID = @MajorID,
		TenNghanh = @MajorName,
		KhoaID = @DeptID
	WHERE NghanhID = @MajorID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateALecturerSubject]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateALecturerSubject]
(
	@LecturerSubjectID INT,
	@LecturerID INT,
	@SubjectID INT
)
AS
BEGIN
	UPDATE GiaoVienMonHoc
	SET GiaoVienMonHocID = @LecturerSubjectID,
		GiaoVienID = @LecturerID,
		MonID = @SubjectID
	WHERE GiaoVienMonHocID = @LecturerSubjectID
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateALecturer]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateALecturer]
(
	@LecturerID INT,
	@LecturerName NVARCHAR(255),
	@SSN NVARCHAR(255),
	@Address NVARCHAR(255),
	@LecturerManager NVARCHAR(255),
	@DeptID INT
)
AS
BEGIN
	UPDATE GiaoVien
	SET GiaoVienID = @LecturerID,
		TenGiaoVien = @LecturerName,
		CMTND = @SSN, 
		DiaChi = @Address,
		GiaoVienQL = @LecturerManager,
		KhoaID = @DeptID
	WHERE GiaoVienID = @LecturerID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateADepartment]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateADepartment]
(
	@DeptID INT, 
	@DeptName NVARCHAR(255),
	@SchoolID INT,
	@SupervisorID INT
)
AS
BEGIN
	UPDATE Khoa
	SET KhoaID = @DeptID,
		TenKhoa = @DeptName,
		TruongID = @SchoolID,
		GiaoVienQL = @SupervisorID
	WHERE KhoaID = @DeptID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateASubjectClassRegistration]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateASubjectClassRegistration]
(
	@SubjectClassRegistrationID INT,
	@SubjectBasedClassID INT,
	@StudentID INT,
	@RegistrationDate DATE,
	@Note NVARCHAR(MAX)
)
AS
BEGIN
	UPDATE DangKyLopTinChiSinhVien
	SET LopTinChiSinhVienID = @SubjectClassRegistrationID, 
			LopTinChiID = @SubjectBasedClassID, 
			SinhvienID = @StudentID, 
			NgayDangKy = @RegistrationDate, 
			Note = @Note
	WHERE DangKyLopTinChiSinhVien.LopTinChiSinhVienID = @SubjectClassRegistrationID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateASubjectBasedClass]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateASubjectBasedClass]
(
	@SubjectBasedClassID INT,
	@SubjectID INT,
	@LecturerID INT,
	@Note NVARCHAR(MAX)
)
AS
BEGIN
	UPDATE LopTinChi
	SET LopTinChiID = @SubjectBasedClassID,
		MonID = @SubjectID, 
		GiaoVienID = @LecturerID,
		Note = @Note
	WHERE LopTinChiID = @SubjectBasedClassID;
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllDepartments]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllDepartments]
AS
BEGIN
	SELECT  Khoa.KhoaID AS [Dept ID],
			Khoa.TenKhoa AS [Dept Name],
			TruongDaiHoc.TruongID AS [School ID],
			TruongDaiHoc.TenTruong AS [School Name],
			Khoa.GiaoVienQL AS [Dept Supervisor ID],
			GiaoVien.TenGiaoVien AS [Dept Supervisor Name]
	FROM dbo.Khoa INNER JOIN TruongDaiHoc
		ON Khoa.TruongID = TruongDaiHoc.TruongID
		INNER JOIN GiaoVien
		ON Khoa.GiaoVienQL = GiaoVien.GiaoVienID;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetDepartmentByName]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetDepartmentByName]
	@DeptName NVARCHAR(255)
AS
BEGIN
	SELECT  Khoa.KhoaID AS [Dept ID],
			Khoa.TenKhoa AS [Dept Name],
			TruongDaiHoc.TruongID AS [School ID],
			TruongDaiHoc.TenTruong AS [School Name],
			Khoa.GiaoVienQL AS [Dept Supervisor ID],
			GiaoVien.TenGiaoVien AS [Dept Supervisor Name]
	FROM dbo.Khoa INNER JOIN TruongDaiHoc
		ON Khoa.TruongID = TruongDaiHoc.TruongID
		INNER JOIN GiaoVien
		ON Khoa.GiaoVienQL = GiaoVien.GiaoVienID
	WHERE TenKhoa LIKE '%' + @DeptName + '%';
END
GO
/****** Object:  StoredProcedure [dbo].[spReportMajorSubjectRegulation]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spReportMajorSubjectRegulation]
AS
BEGIN	
	SELECT --ChuyenNghanhMonHoc.ChuyenNghanhMonHocID AS [MS ID],
			ChuyenNghanh.NghanhID AS [Major ID],
			ChuyenNghanh.TenNghanh AS [Major Name],
			MonHoc.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name],
			'Compulsory' =
			CASE
				WHEN ChuyenNghanhMonHoc.BatBuoc = 1 THEN 'true'
				ELSE 'false'
			END,			
			ChuyenNghanhMonHoc.Note AS [Note]
	FROM ChuyenNghanh INNER JOIN ChuyenNghanhMonHoc
		ON ChuyenNghanh.NghanhID = ChuyenNghanhMonHoc.NghanhID
		INNER JOIN MonHoc
		ON ChuyenNghanhMonHoc.MonID = MonHoc.MonID
	ORDER BY ChuyenNghanh.TenNghanh, MonHoc.TenMon;
			
		 
END
GO
/****** Object:  StoredProcedure [dbo].[spReportLecturersSubjectsAssigment]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spReportLecturersSubjectsAssigment]
	-- Add the parameters for the stored procedure here
AS
BEGIN
   	SELECT --GiaoVienMonHoc.GiaoVienMonHocID AS [LecSub ID],
			GiaoVienMonHoc.GiaoVienID AS [Lecturer ID],
			GiaoVien.TenGiaoVien AS [Lecturer Name], 
			GiaoVienMonHoc.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name]
	FROM GiaoVienMonHoc LEFT JOIN GiaoVien
			ON GiaoVienMonHoc.GiaoVienID = GiaoVien.GiaoVienID
			LEFT JOIN MonHoc
			ON GiaoVienMonHoc.MonID = MonHoc.MonID
	ORDER BY [Lecturer Name], [Subject Name];
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllSubjectBasedClass]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllSubjectBasedClass]
AS
BEGIN
	SELECT LopTinChi.LopTinChiID AS [Class ID],
			LopTinChi.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name],
			LopTinChi.GiaoVienID AS [Lecturer ID],
			GiaoVien.TenGiaoVien AS [Lecturer Name],
			LopTinChi.Note AS [Note]
	FROM LopTinChi LEFT JOIN MonHoc
			ON LopTinChi.MonID = MonHoc.MonID
			LEFT JOIN GiaoVien
			ON LopTinChi.GiaoVienID = GiaoVien.GiaoVienID
	ORDER BY [Class ID], MonHoc.TenMon;
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllMajorSubject]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllMajorSubject]
AS
BEGIN	
	SELECT ChuyenNghanhMonHoc.ChuyenNghanhMonHocID AS [MS ID],
			ChuyenNghanh.NghanhID AS [Major ID],
			ChuyenNghanh.TenNghanh AS [Major Name],
			MonHoc.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name],
			'Compulsory' =
			CASE
				WHEN ChuyenNghanhMonHoc.BatBuoc = 1 THEN 'true'
				ELSE 'false'
			END,			
			ChuyenNghanhMonHoc.Note AS [Note]
	FROM ChuyenNghanh INNER JOIN ChuyenNghanhMonHoc
		ON ChuyenNghanh.NghanhID = ChuyenNghanhMonHoc.NghanhID
		INNER JOIN MonHoc
		ON ChuyenNghanhMonHoc.MonID = MonHoc.MonID
	ORDER BY [MS ID],ChuyenNghanh.TenNghanh, MonHoc.TenMon;
			
		 
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllLecturersSubjects]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllLecturersSubjects]
	-- Add the parameters for the stored procedure here
AS
BEGIN
   	SELECT GiaoVienMonHoc.GiaoVienMonHocID AS [LecSub ID],
			GiaoVienMonHoc.GiaoVienID AS [Lecturer ID],
			GiaoVien.TenGiaoVien AS [Lecturer Name], 
			GiaoVienMonHoc.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name]
	FROM GiaoVienMonHoc LEFT JOIN GiaoVien
			ON GiaoVienMonHoc.GiaoVienID = GiaoVien.GiaoVienID
			LEFT JOIN MonHoc
			ON GiaoVienMonHoc.MonID = MonHoc.MonID
	ORDER BY [LecSub ID], GiaoVien.TenGiaoVien;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetSubjectBasedClassByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetSubjectBasedClassByID]
	@SubjectBasedClassID INT
AS
BEGIN
	SELECT LopTinChi.LopTinChiID AS [Class ID],
			LopTinChi.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name],
			LopTinChi.GiaoVienID AS [Lecturer ID],
			GiaoVien.TenGiaoVien AS [Lecturer Name],
			LopTinChi.Note AS [Note]
	FROM LopTinChi LEFT JOIN MonHoc
			ON LopTinChi.MonID = MonHoc.MonID
			LEFT JOIN GiaoVien
			ON LopTinChi.GiaoVienID = GiaoVien.GiaoVienID
	WHERE LopTinChi.LopTinChiID = @SubjectBasedClassID;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetMajorSubjectByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetMajorSubjectByID]
	@MajorSubjectID INT
AS
BEGIN
	SELECT ChuyenNghanhMonHoc.ChuyenNghanhMonHocID AS [MS ID],
			ChuyenNghanh.NghanhID AS [Major ID],
			ChuyenNghanh.TenNghanh AS [Major Name],
			MonHoc.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name],
			'Compulsory' =
			CASE
				WHEN ChuyenNghanhMonHoc.BatBuoc = 1 THEN 'True'
				ELSE 'False'
			END,			
			ChuyenNghanhMonHoc.Note AS [Note]
	FROM ChuyenNghanh INNER JOIN ChuyenNghanhMonHoc
		ON ChuyenNghanh.NghanhID = ChuyenNghanhMonHoc.NghanhID
		INNER JOIN MonHoc
		ON ChuyenNghanhMonHoc.MonID = MonHoc.MonID
	WHERE ChuyenNghanhMonHoc.ChuyenNghanhMonHocID = @MajorSubjectID
	ORDER BY ChuyenNghanh.TenNghanh, MonHoc.TenMon;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetLecturerSubjectByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetLecturerSubjectByID]
	@LecturerSubjectID INT
AS
BEGIN
	SELECT GiaoVienMonHoc.GiaoVienMonHocID AS [LecSub ID],
			GiaoVienMonHoc.GiaoVienID AS [Lecturer ID],
			GiaoVien.TenGiaoVien AS [Lecturer Name], 
			GiaoVienMonHoc.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name]
	FROM GiaoVienMonHoc LEFT JOIN GiaoVien
			ON GiaoVienMonHoc.GiaoVienID = GiaoVien.GiaoVienID
			LEFT JOIN MonHoc
			ON GiaoVienMonHoc.MonID = MonHoc.MonID
	WHERE GiaoVienMonHoc.GiaoVienMonHocID = @LecturerSubjectID
END
GO
/****** Object:  Table [dbo].[LopNienCheMonHoc]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LopNienCheMonHoc](
	[LopNienCheMonHocID] [int] NOT NULL,
	[LopNienCheID] [int] NULL,
	[MonID] [int] NULL,
	[KyHoc] [int] NULL,
	[NamHoc] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[LopNienCheMonHocID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (1, 1, 1, 1, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (2, 1, 2, 1, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (3, 1, 3, 1, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (4, 1, 4, 1, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (5, 1, 5, 2, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (6, 1, 6, 2, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (7, 1, 7, 2, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (8, 1, 8, 2, 2016)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (9, 2, 1, 1, 2017)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (10, 2, 2, 1, 2017)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (11, 2, 3, 2, 2017)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (12, 2, 4, 2, 2017)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (13, 3, 5, 1, 2017)
INSERT [dbo].[LopNienCheMonHoc] ([LopNienCheMonHocID], [LopNienCheID], [MonID], [KyHoc], [NamHoc]) VALUES (14, 3, 7, 2, 2017)
/****** Object:  StoredProcedure [dbo].[spDeleteByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spDeleteByID]
(
	@TableName NVARCHAR(MAX),
	@IDColumnName NVARCHAR(MAX),
	@IDNumber INT
)	
AS
BEGIN	
	DECLARE @SQLString NVARCHAR(MAX);
	
	SET @SQLString
	= N'DELETE FROM ' + @TableName +
	' WHERE ' + @IDColumnName + ' = '+ CAST(@IDNumber AS NVARCHAR);
	
	EXEC sp_executesql @SQLString;	
END
GO
/****** Object:  StoredProcedure [dbo].[spGetIDMax]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetIDMax]
(
	@TableName NVARCHAR(MAX),
	@IDColumnName NVARCHAR(MAX)
)	
AS
BEGIN	
	DECLARE @SQLString NVARCHAR(MAX);
	
	SET @SQLString
	= N'SELECT MAX('+@IDColumnName+')AS [MaxID]
	FROM '+@TableName;
	
	EXEC sp_executesql @SQLString;	
END
GO
/****** Object:  StoredProcedure [dbo].[spGetExamByName]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spGetExamByName]
(
	@ExamName NVARCHAR(255)
)
AS
BEGIN
	SELECT 
		KyThiID AS [Exam ID],
		TenKyThi AS [Exam Name],
		NgayThi AS [Exam Date]
	FROM KyThi	
	WHERE TenKyThi LIKE '%' + @ExamName + '%'
	ORDER BY KyThiID;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetExamByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetExamByID]
(
	@ExamID INT
)
AS
BEGIN
	SELECT 
		KyThiID AS [Exam ID],
		TenKyThi AS [Exam Name],
		NgayThi AS [Exam Date]
	FROM KyThi
	WHERE KyThiID = @ExamID
	ORDER BY KyThiID;
END
GO
/****** Object:  StoredProcedure [dbo].[spThreeQuery]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spThreeQuery]
AS
BEGIN
 SELECT * FROM SinhVien;
 SELECT * FROM GiaoVien;
 SELECT * FROM Khoa;
END
GO
/****** Object:  StoredProcedure [dbo].[spReportStudentMarkExamSubjectLecturer]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spReportStudentMarkExamSubjectLecturer]
AS
BEGIN
SELECT --DiemThi.DiemThiID AS [Mark ID],
   			--Info Examination
   			DiemThi.KyThiID AS [Exam ID],
			KyThi.TenKyThi AS [Exam Name],
			KyThi.NgayThi AS [Exam Date],   			
   
			--Info Subject-Based Class
   			--DiemThi.LopTinChiSinhVienID AS [StudentCode@Exam],--Student ID in each Subject Based Class
   			
   			--Info Class and Subject, Lecturer
   			--DangKyLopTinChiSinhVien.LopTinChiID AS [SBC ID],
   			--LopTinChi.MonID AS [Subject ID],
   			MonHoc.TenMon AS [Subject Name],
   			
   			--LopTinChi.GiaoVienID AS [Lecturer ID],
   			GiaoVien.TenGiaoVien AS [Lecturer Name],
   			
   			--Info Student
   			DangKyLopTinChiSinhVien.SinhvienID AS [Student ID],
   			SinhVien.TenSinhVien AS [Student Name],		
   
   			DiemThi.Diem AS [Mark],
   			--Info School
   			TruongDaiHoc.TenTruong AS [School Name]
   	FROM DiemThi INNER JOIN KyThi
   			ON DiemThi.KyThiID = KyThi.KyThiID
   			INNER JOIN DangKyLopTinChiSinhVien
   			ON DangKyLopTinChiSinhVien.LopTinChiSinhVienID = DiemThi.LopTinChiSinhVienID
   			INNER JOIN LopTinChi
   			ON DangKyLopTinChiSinhVien.LopTinChiID = LopTinChi.LopTinChiID
   			INNER JOIN MonHoc
   			ON LopTinChi.MonID = MonHoc.MonID
   			INNER JOIN GiaoVien
   			ON LopTinChi.GiaoVienID = GiaoVien.GiaoVienID
   			INNER JOIN SinhVien
   			ON DangKyLopTinChiSinhVien.SinhvienID = SinhVien.SinhVienID
   			INNER JOIN TruongDaiHoc
   			ON TruongDaiHoc.TruongID = SinhVien.TruongID
   	ORDER BY [Exam ID], [Exam Date],[Subject Name], Mark DESC, [Student Name]
END


/*
//Option 2:
BEGIN	
	--2.Drop a temporary table in sql (if neccessary)
	--https://stackoverflow.com/questions/659051/check-if-a-temporary-table-exists-and-delete-if-it-exists-before-creating-a-temp
	IF OBJECT_ID('tempdb..#cteCode') IS NOT NULL DROP TABLE #cteCode
	

	IF OBJECT_ID('tempdb..#cteCode2') IS NOT NULL DROP TABLE #cteCode2
	
	--3. Create 2 temporary tables with ID related
	SELECT DiemThi.DiemThiID,
			DiemThi.KyThiID,
			DiemThi.LopTinChiSinhVienID,
			DiemThi.Diem,
			DangKyLopTinChiSinhVien.SinhvienID,
			DangKyLopTinChiSinhVien.LopTinChiID
	INTO #cteCode
	FROM DiemThi INNER JOIN DangKyLopTinChiSinhVien
		ON DiemThi.LopTinChiSinhVienID = DangKyLopTinChiSinhVien.LopTinChiSinhVienID
		INNER JOIN KyThi
		ON DiemThi.KyThiID = KyThi.KyThiID
	
	SELECT #cteCode.SinhvienID,
			SinhVien.TenSinhVien,
			#cteCode.Diem,
			#cteCode.KyThiID,
			KyThi.NgayThi,
			#cteCode.LopTinChiID,
			LopTinChi.MonID,
			LopTinChi.GiaoVienID
	INTO #cteCode2
	FROM #cteCode INNER JOIN SinhVien
		ON #cteCode.SinhvienID = SinhVien.SinhVienID
		INNER JOIN KyThi
		ON #cteCode.KyThiID = KyThi.KyThiID
		INNER JOIN LopTinChi
		ON #cteCode.LopTinChiID = LopTinChi.LopTinChiID
	
	--4. Display as user-friendly format
	SELECT	#cteCode2.SinhvienID,
				#cteCode2.TenSinhVien,
				#cteCode2.Diem,
				#cteCode2.LopTinChiID,
				#cteCode2.MonID,
				MonHoc.TenMon,
				#cteCode2.GiaoVienID,
				GiaoVien.TenGiaoVien,
				#cteCode2.KyThiID,
				#cteCode2.NgayThi
	FROM #cteCode2 INNER JOIN MonHoc
		ON #cteCode2.MonID = MonHoc.MonID
		INNER JOIN GiaoVien
		ON #cteCode2.GiaoVienID = GiaoVien.GiaoVienID
	ORDER BY #cteCode2.SinhvienID,#cteCode2.MonID,#cteCode2.Diem, #cteCode2.KyThiID ;

END
*/
GO
/****** Object:  StoredProcedure [dbo].[spListAllSubjectClassRegistration]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllSubjectClassRegistration]
AS
BEGIN	
	SELECT DangKyLopTinChiSinhVien.LopTinChiSinhVienID AS [SCR ID],
			DangKyLopTinChiSinhVien.LopTinChiID AS [Class ID],
			LopTinChi.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name],
			
			LopTinChi.GiaoVienID AS [Lecturer ID],
			GiaoVien.TenGiaoVien AS [Lecturer Name],
						
			DangKyLopTinChiSinhVien.SinhvienID AS [Student ID],
			SinhVien.TenSinhVien AS [Student Name],
			
			DangKyLopTinChiSinhVien.NgayDangKy AS [Registed on],
			
			DangKyLopTinChiSinhVien.Note AS [Note]
			
	FROM DangKyLopTinChiSinhVien INNER JOIN LopTinChi 
			ON DangKyLopTinChiSinhVien.LopTinChiID = LopTinChi.LopTinChiID
			INNER JOIN MonHoc ON LopTinChi.MonID = MonHoc.MonID
			INNER JOIN GiaoVien ON LopTinChi.GiaoVienID = GiaoVien.GiaoVienID
			INNER JOIN SinhVien ON DangKyLopTinChiSinhVien.SinhvienID = SinhVien.SinhVienID
	ORDER BY [SCR ID], MonHoc.TenMon,SinhVien.TenSinhVien;
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllMarks]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllMarks]
	-- Add the parameters for the stored procedure here
AS
BEGIN
   	SELECT DiemThi.DiemThiID AS [Mark ID],
   			--Info Examination
   			DiemThi.KyThiID AS [Exam ID],
			KyThi.TenKyThi AS [Exam Name],
			KyThi.NgayThi AS [Exam Date],   			
   
			--Info Subject-Based Class
   			DiemThi.LopTinChiSinhVienID AS [StudentCode@Exam],--Student ID in each Subject Based Class
   			
   			--Info Class and Subject, Lecturer
   			--DangKyLopTinChiSinhVien.LopTinChiID AS [SBC ID],
   			--LopTinChi.MonID AS [Subject ID],
   			MonHoc.TenMon AS [Subject Name],
   			
   			--LopTinChi.GiaoVienID AS [Lecturer ID],
   			GiaoVien.TenGiaoVien AS [Lecturer Name],
   			
   			--Info Student
   			DangKyLopTinChiSinhVien.SinhvienID AS [Student ID],
   			SinhVien.TenSinhVien AS [Student Name],		
   
   			DiemThi.Diem AS [Mark],
   			--Info School
   			TruongDaiHoc.TenTruong AS [School Name]
   	FROM DiemThi INNER JOIN KyThi
   			ON DiemThi.KyThiID = KyThi.KyThiID
   			INNER JOIN DangKyLopTinChiSinhVien
   			ON DangKyLopTinChiSinhVien.LopTinChiSinhVienID = DiemThi.LopTinChiSinhVienID
   			INNER JOIN LopTinChi
   			ON DangKyLopTinChiSinhVien.LopTinChiID = LopTinChi.LopTinChiID
   			INNER JOIN MonHoc
   			ON LopTinChi.MonID = MonHoc.MonID
   			INNER JOIN GiaoVien
   			ON LopTinChi.GiaoVienID = GiaoVien.GiaoVienID
   			INNER JOIN SinhVien
   			ON DangKyLopTinChiSinhVien.SinhvienID = SinhVien.SinhVienID
   			INNER JOIN TruongDaiHoc
   			ON TruongDaiHoc.TruongID = SinhVien.TruongID
   	ORDER BY [Mark ID], [Exam ID], [Exam Date],[Subject Name], Mark DESC, [Student Name]
END
GO
/****** Object:  StoredProcedure [dbo].[spGetSubjectClassRegistrationByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetSubjectClassRegistrationByID]
(
	@SubjectClassRegistrationID INT
)
AS
BEGIN	
	SELECT DangKyLopTinChiSinhVien.LopTinChiSinhVienID AS [SCR ID],
			DangKyLopTinChiSinhVien.LopTinChiID AS [Class ID],
			LopTinChi.MonID AS [Subject ID],
			MonHoc.TenMon AS [Subject Name],
			
			LopTinChi.GiaoVienID AS [Lecturer ID],
			GiaoVien.TenGiaoVien AS [Lecturer Name],
						
			DangKyLopTinChiSinhVien.SinhvienID AS [Student ID],
			SinhVien.TenSinhVien AS [Student Name],
			
			DangKyLopTinChiSinhVien.NgayDangKy AS [Registed on],
			
			DangKyLopTinChiSinhVien.Note AS [Note]
			
	FROM DangKyLopTinChiSinhVien INNER JOIN LopTinChi 
			ON DangKyLopTinChiSinhVien.LopTinChiID = LopTinChi.LopTinChiID
			INNER JOIN MonHoc ON LopTinChi.MonID = MonHoc.MonID
			INNER JOIN GiaoVien ON LopTinChi.GiaoVienID = GiaoVien.GiaoVienID
			INNER JOIN SinhVien ON DangKyLopTinChiSinhVien.SinhvienID = SinhVien.SinhVienID
	
	WHERE DangKyLopTinChiSinhVien.LopTinChiSinhVienID = @SubjectClassRegistrationID
	
	ORDER BY MonHoc.TenMon,SinhVien.TenSinhVien;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetMarkByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetMarkByID]
	-- Add the parameters for the stored procedure here
	@MarkID INT
AS
BEGIN
   	SELECT DiemThi.DiemThiID AS [Mark ID],
   			--Info Examination
   			DiemThi.KyThiID AS [Exam ID],
			KyThi.TenKyThi AS [Exam Name],
			KyThi.NgayThi AS [Exam Date],   			
   
			--Info Subject-Based Class
   			DiemThi.LopTinChiSinhVienID AS [StudentCode@Exam],--Student ID in each Subject Based Class
   			
   			--Info Class and Subject, Lecturer
   			--DangKyLopTinChiSinhVien.LopTinChiID AS [SBC ID],
   			--LopTinChi.MonID AS [Subject ID],
   			MonHoc.TenMon AS [Subject Name],
   			
   			--LopTinChi.GiaoVienID AS [Lecturer ID],
   			GiaoVien.TenGiaoVien AS [Lecturer Name],
   			
   			--Info Student
   			DangKyLopTinChiSinhVien.SinhvienID AS [Student ID],
   			SinhVien.TenSinhVien AS [Student Name],		
   
   			DiemThi.Diem AS [Mark],
   			--Info School
   			TruongDaiHoc.TenTruong AS [School Name]
   	FROM DiemThi INNER JOIN KyThi
   			ON DiemThi.KyThiID = KyThi.KyThiID
   			INNER JOIN DangKyLopTinChiSinhVien
   			ON DangKyLopTinChiSinhVien.LopTinChiSinhVienID = DiemThi.LopTinChiSinhVienID
   			INNER JOIN LopTinChi
   			ON DangKyLopTinChiSinhVien.LopTinChiID = LopTinChi.LopTinChiID
   			INNER JOIN MonHoc
   			ON LopTinChi.MonID = MonHoc.MonID
   			INNER JOIN GiaoVien
   			ON LopTinChi.GiaoVienID = GiaoVien.GiaoVienID
   			INNER JOIN SinhVien
   			ON DangKyLopTinChiSinhVien.SinhvienID = SinhVien.SinhVienID
   			INNER JOIN TruongDaiHoc
   			ON TruongDaiHoc.TruongID = SinhVien.TruongID
   	WHERE DiemThi.DiemThiID = @MarkID
   	ORDER BY [Exam ID], [Exam Date],[Subject Name], Mark DESC, [Student Name]
END
GO
/****** Object:  Table [dbo].[SinhVienChuyenNghanh]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SinhVienChuyenNghanh](
	[SinhVienChuyenNghanhID] [int] NOT NULL,
	[SinhVienID] [int] NULL,
	[ChuyenNghanhID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SinhVienChuyenNghanhID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (1, 1, 1)
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (2, 2, 2)
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (3, 3, 1)
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (4, 4, 1)
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (5, 5, 2)
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (6, 6, 1)
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (7, 7, 1)
INSERT [dbo].[SinhVienChuyenNghanh] ([SinhVienChuyenNghanhID], [SinhVienID], [ChuyenNghanhID]) VALUES (8, 8, 1)
/****** Object:  StoredProcedure [dbo].[spGetSubjectByName]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spGetSubjectByName]
(
	@SubjectName NVARCHAR(255)
)
AS
BEGIN
	SELECT
		MonID AS [Subject ID],
		TenMon AS [Subject Name],
		MieuTa AS [Description]
	FROM MonHoc
	WHERE TenMon LIKE '%' + @SubjectName + '%';
END
GO
/****** Object:  StoredProcedure [dbo].[spGetSubjectByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetSubjectByID]
(
	@SubjectID INT
)
AS
BEGIN
	SELECT
		MonID AS [Subject ID],
		TenMon AS [Subject Name],
		MieuTa AS [Description]
	FROM MonHoc
	WHERE MonID = @SubjectID;
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertASubject]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertASubject]
	-- Add the parameters for the stored procedure here
	@SubjectID INT, 
	@SubjectName NVARCHAR(255),
	@Description NVARCHAR(255)
AS
BEGIN
    -- Insert statements for procedure here
	INSERT MonHoc(MonID, TenMon, MieuTa)
	VALUES (@SubjectID, @SubjectName,@Description);
END
GO
/****** Object:  StoredProcedure [dbo].[spListSchools]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListSchools]
AS
BEGIN
	SELECT TruongID AS [School ID],
			TenTruong AS [School Name],
			DiaChi AS [Address],
			Email AS [Email],
			Phone AS [Phone]
	FROM dbo.TruongDaiHoc	
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllSubjects]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllSubjects]
	-- Add the parameters for the stored procedure here
AS
BEGIN
    -- Insert statements for procedure here
   	SELECT 
		MonID AS [Subject ID],
		TenMon AS [Subject Name],
		MieuTa AS [Description]
	FROM MonHoc
	ORDER BY TenMon;
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllExams]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllExams]
	-- Add the parameters for the stored procedure here
AS
BEGIN
    -- Insert statements for procedure here
   	SELECT 
		KyThiID AS [Exam ID],
		TenKyThi AS [Exam Name],
		NgayThi AS [Exam Date]
	FROM KyThi
	ORDER BY KyThiID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateAnExam]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateAnExam]
(
	@ExamID INT, 
	@ExamName NVARCHAR(255),
	@ExamDate DATE
)
AS
BEGIN
	UPDATE KyThi
	SET KyThiID = @ExamID, 
		TenKyThi = @ExamName,
		NgayThi = @ExamDate
	WHERE KyThiID = @ExamID;
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertAnExam]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertAnExam]
	-- Add the parameters for the stored procedure here
	@ExamID INT, 
	@ExamName NVARCHAR(255),
	@ExamDate DATE
AS
BEGIN
    -- Insert statements for procedure here
	INSERT KyThi (KyThiID, TenKyThi, NgayThi)
	VALUES (@ExamID, @ExamName, @ExamDate);
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateASubject]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUpdateASubject]
(
	@SubjectID INT, 
	@SubjectName NVARCHAR(255),
	@Description NVARCHAR(255)
)
AS
BEGIN
	UPDATE MonHoc
	SET MonID = @SubjectID,
		TenMon = @SubjectName,
		MieuTa = @Description
	WHERE MonID = @SubjectID;
END
GO
/****** Object:  StoredProcedure [dbo].[spUpdateAStudent]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spUpdateAStudent]
	@StudentID INT,
	@FullName NVARCHAR(255),
	@DOB DATE,
	@Address NVARCHAR(255),
	@SchoolID INT	
AS
BEGIN
	UPDATE SinhVien
	SET TenSinhVien = @FullName,
		DOB = @DOB,
		DiaChi = @Address,
		TruongID = @SchoolID
	FROM dbo.SinhVien
	WHERE SinhVienID = @StudentID;
END
GO
/****** Object:  StoredProcedure [dbo].[spListAllStudents]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spListAllStudents]
	-- Add the parameters for the stored procedure here
AS
BEGIN
    -- Insert statements for procedure here
	SELECT SinhVien.SinhVienID AS [Student ID],
			SinhVien.TenSinhVien AS [Full Name],
			SinhVien.DOB AS [DOB],
			SinhVien.DiaChi AS [Address], 
			SinhVien.TruongID AS [School ID],
			TruongDaiHoc.TenTruong AS [School Name]
	FROM SinhVien INNER JOIN TruongDaiHoc
		ON SinhVien.TruongID = TruongDaiHoc.TruongID
	ORDER BY [Student ID] , SinhVien.TenSinhVien;
END
GO
/****** Object:  StoredProcedure [dbo].[spGetStudentByName]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetStudentByName]
	@StudentName NVARCHAR(255)
AS
BEGIN
	SELECT SinhVien.SinhVienID AS [Student ID],
			SinhVien.TenSinhVien AS [Full Name],
			SinhVien.DOB AS [DOB],
			SinhVien.DiaChi AS [Address],
			SinhVien.TruongID AS [School ID],
			TruongDaiHoc.TenTruong AS [School Name]
	FROM SinhVien INNER JOIN TruongDaiHoc
		ON SinhVien.TruongID = TruongDaiHoc.TruongID
	WHERE TenSinhVien LIKE '%' + @StudentName + '%';
END
GO
/****** Object:  StoredProcedure [dbo].[spGetStudentByID]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spGetStudentByID]
	@StudentID INT
AS
BEGIN
	SELECT SinhVienID AS [Student ID],
			TenSinhVien AS [Full Name],
			DOB AS [DOB],
			DiaChi AS [Address],
			TruongID AS [School ID]
	FROM dbo.SinhVien
	WHERE SinhVienID = @StudentID;
END
GO
/****** Object:  StoredProcedure [dbo].[spInsertAStudent]    Script Date: 06/18/2017 21:22:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spInsertAStudent]
	-- Add the parameters for the stored procedure here
	@StudentID INT, 
	@FullName NVARCHAR(255),
	@DOB DATE,
	@Address NVARCHAR(255),
	@SchoolID INT
AS
BEGIN
    -- Insert statements for procedure here
	INSERT SinhVien(SinhVienID, TenSinhVien, DOB, DiaChi, TruongID)
	VALUES (@StudentID, @FullName, @DOB, @Address, @SchoolID);
END
GO
/****** Object:  ForeignKey [fk_04]    Script Date: 06/18/2017 21:22:29 ******/
ALTER TABLE [dbo].[GiaoVien]  WITH CHECK ADD  CONSTRAINT [fk_04] FOREIGN KEY([GiaoVienQL])
REFERENCES [dbo].[GiaoVien] ([GiaoVienID])
GO
ALTER TABLE [dbo].[GiaoVien] CHECK CONSTRAINT [fk_04]
GO
/****** Object:  ForeignKey [fk_05]    Script Date: 06/18/2017 21:22:29 ******/
ALTER TABLE [dbo].[GiaoVien]  WITH CHECK ADD  CONSTRAINT [fk_05] FOREIGN KEY([KhoaID])
REFERENCES [dbo].[Khoa] ([KhoaID])
GO
ALTER TABLE [dbo].[GiaoVien] CHECK CONSTRAINT [fk_05]
GO
/****** Object:  ForeignKey [fk_02]    Script Date: 06/18/2017 21:22:29 ******/
ALTER TABLE [dbo].[Khoa]  WITH CHECK ADD  CONSTRAINT [fk_02] FOREIGN KEY([TruongID])
REFERENCES [dbo].[TruongDaiHoc] ([TruongID])
GO
ALTER TABLE [dbo].[Khoa] CHECK CONSTRAINT [fk_02]
GO
/****** Object:  ForeignKey [fk_03]    Script Date: 06/18/2017 21:22:29 ******/
ALTER TABLE [dbo].[Khoa]  WITH CHECK ADD  CONSTRAINT [fk_03] FOREIGN KEY([GiaoVienQL])
REFERENCES [dbo].[GiaoVien] ([GiaoVienID])
GO
ALTER TABLE [dbo].[Khoa] CHECK CONSTRAINT [fk_03]
GO
/****** Object:  ForeignKey [fk_01]    Script Date: 06/18/2017 21:22:29 ******/
ALTER TABLE [dbo].[ChuyenNghanh]  WITH CHECK ADD  CONSTRAINT [fk_01] FOREIGN KEY([KhoaID])
REFERENCES [dbo].[Khoa] ([KhoaID])
GO
ALTER TABLE [dbo].[ChuyenNghanh] CHECK CONSTRAINT [fk_01]
GO
/****** Object:  ForeignKey [fk_17]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[SinhVien]  WITH CHECK ADD  CONSTRAINT [fk_17] FOREIGN KEY([TruongID])
REFERENCES [dbo].[TruongDaiHoc] ([TruongID])
GO
ALTER TABLE [dbo].[SinhVien] CHECK CONSTRAINT [fk_17]
GO
/****** Object:  ForeignKey [fk_18]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[LopTinChi]  WITH CHECK ADD  CONSTRAINT [fk_18] FOREIGN KEY([MonID])
REFERENCES [dbo].[MonHoc] ([MonID])
GO
ALTER TABLE [dbo].[LopTinChi] CHECK CONSTRAINT [fk_18]
GO
/****** Object:  ForeignKey [fk_19]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[LopTinChi]  WITH CHECK ADD  CONSTRAINT [fk_19] FOREIGN KEY([GiaoVienID])
REFERENCES [dbo].[GiaoVien] ([GiaoVienID])
GO
ALTER TABLE [dbo].[LopTinChi] CHECK CONSTRAINT [fk_19]
GO
/****** Object:  ForeignKey [FK_DangKyLopTinChiSinhVien_LopTinChi]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[DangKyLopTinChiSinhVien]  WITH CHECK ADD  CONSTRAINT [FK_DangKyLopTinChiSinhVien_LopTinChi] FOREIGN KEY([LopTinChiID])
REFERENCES [dbo].[LopTinChi] ([LopTinChiID])
GO
ALTER TABLE [dbo].[DangKyLopTinChiSinhVien] CHECK CONSTRAINT [FK_DangKyLopTinChiSinhVien_LopTinChi]
GO
/****** Object:  ForeignKey [FK_DangKyLopTinChiSinhVien_SinhVien]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[DangKyLopTinChiSinhVien]  WITH CHECK ADD  CONSTRAINT [FK_DangKyLopTinChiSinhVien_SinhVien] FOREIGN KEY([SinhvienID])
REFERENCES [dbo].[SinhVien] ([SinhVienID])
GO
ALTER TABLE [dbo].[DangKyLopTinChiSinhVien] CHECK CONSTRAINT [FK_DangKyLopTinChiSinhVien_SinhVien]
GO
/****** Object:  ForeignKey [FK_DiemThi_DangKyLopTinChiSinhVien]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[DiemThi]  WITH CHECK ADD  CONSTRAINT [FK_DiemThi_DangKyLopTinChiSinhVien] FOREIGN KEY([LopTinChiSinhVienID])
REFERENCES [dbo].[DangKyLopTinChiSinhVien] ([LopTinChiSinhVienID])
GO
ALTER TABLE [dbo].[DiemThi] CHECK CONSTRAINT [FK_DiemThi_DangKyLopTinChiSinhVien]
GO
/****** Object:  ForeignKey [FK_DiemThi_KyThi]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[DiemThi]  WITH CHECK ADD  CONSTRAINT [FK_DiemThi_KyThi] FOREIGN KEY([KyThiID])
REFERENCES [dbo].[KyThi] ([KyThiID])
GO
ALTER TABLE [dbo].[DiemThi] CHECK CONSTRAINT [FK_DiemThi_KyThi]
GO
/****** Object:  ForeignKey [fk_08]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[ChuyenNghanhMonHoc]  WITH CHECK ADD  CONSTRAINT [fk_08] FOREIGN KEY([NghanhID])
REFERENCES [dbo].[ChuyenNghanh] ([NghanhID])
GO
ALTER TABLE [dbo].[ChuyenNghanhMonHoc] CHECK CONSTRAINT [fk_08]
GO
/****** Object:  ForeignKey [fk_09]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[ChuyenNghanhMonHoc]  WITH CHECK ADD  CONSTRAINT [fk_09] FOREIGN KEY([MonID])
REFERENCES [dbo].[MonHoc] ([MonID])
GO
ALTER TABLE [dbo].[ChuyenNghanhMonHoc] CHECK CONSTRAINT [fk_09]
GO
/****** Object:  ForeignKey [fk_10]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[GiaoVienMonHoc]  WITH CHECK ADD  CONSTRAINT [fk_10] FOREIGN KEY([GiaoVienID])
REFERENCES [dbo].[GiaoVien] ([GiaoVienID])
GO
ALTER TABLE [dbo].[GiaoVienMonHoc] CHECK CONSTRAINT [fk_10]
GO
/****** Object:  ForeignKey [fk_11]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[GiaoVienMonHoc]  WITH CHECK ADD  CONSTRAINT [fk_11] FOREIGN KEY([MonID])
REFERENCES [dbo].[MonHoc] ([MonID])
GO
ALTER TABLE [dbo].[GiaoVienMonHoc] CHECK CONSTRAINT [fk_11]
GO
/****** Object:  ForeignKey [fk_14]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[LopNienChe]  WITH CHECK ADD  CONSTRAINT [fk_14] FOREIGN KEY([GiaoVienChuNhiemID])
REFERENCES [dbo].[GiaoVien] ([GiaoVienID])
GO
ALTER TABLE [dbo].[LopNienChe] CHECK CONSTRAINT [fk_14]
GO
/****** Object:  ForeignKey [fk_15]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[LopNienCheGiaoVien]  WITH CHECK ADD  CONSTRAINT [fk_15] FOREIGN KEY([LopNienCheID])
REFERENCES [dbo].[LopNienChe] ([LopNienCheID])
GO
ALTER TABLE [dbo].[LopNienCheGiaoVien] CHECK CONSTRAINT [fk_15]
GO
/****** Object:  ForeignKey [fk_16]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[LopNienCheGiaoVien]  WITH CHECK ADD  CONSTRAINT [fk_16] FOREIGN KEY([GiaoVienID])
REFERENCES [dbo].[GiaoVien] ([GiaoVienID])
GO
ALTER TABLE [dbo].[LopNienCheGiaoVien] CHECK CONSTRAINT [fk_16]
GO
/****** Object:  ForeignKey [fk_12]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[LopNienCheMonHoc]  WITH CHECK ADD  CONSTRAINT [fk_12] FOREIGN KEY([LopNienCheID])
REFERENCES [dbo].[LopNienChe] ([LopNienCheID])
GO
ALTER TABLE [dbo].[LopNienCheMonHoc] CHECK CONSTRAINT [fk_12]
GO
/****** Object:  ForeignKey [fk_13]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[LopNienCheMonHoc]  WITH CHECK ADD  CONSTRAINT [fk_13] FOREIGN KEY([MonID])
REFERENCES [dbo].[MonHoc] ([MonID])
GO
ALTER TABLE [dbo].[LopNienCheMonHoc] CHECK CONSTRAINT [fk_13]
GO
/****** Object:  ForeignKey [fk_06]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[SinhVienChuyenNghanh]  WITH CHECK ADD  CONSTRAINT [fk_06] FOREIGN KEY([SinhVienID])
REFERENCES [dbo].[SinhVien] ([SinhVienID])
GO
ALTER TABLE [dbo].[SinhVienChuyenNghanh] CHECK CONSTRAINT [fk_06]
GO
/****** Object:  ForeignKey [fk_07]    Script Date: 06/18/2017 21:22:33 ******/
ALTER TABLE [dbo].[SinhVienChuyenNghanh]  WITH CHECK ADD  CONSTRAINT [fk_07] FOREIGN KEY([ChuyenNghanhID])
REFERENCES [dbo].[ChuyenNghanh] ([NghanhID])
GO
ALTER TABLE [dbo].[SinhVienChuyenNghanh] CHECK CONSTRAINT [fk_07]
GO
