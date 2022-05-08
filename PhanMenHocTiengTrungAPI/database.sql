use master
go
create database PhanMemHocTiengTrung
go
use PhanMemHocTiengTrung
go
create table TaiKhoan(
	id int identity primary key,
	email nvarchar(50) not null,
	hoten nvarchar(50) not null,
	ngaysinh nvarchar(100) not null,
	sdt char(10),
	hinhanh nvarchar(max),
	[level] int,
	isAdmin bit,
)
go
create table BaiHoc(
	id int identity primary key,
	chude varchar(50),
	idYoutube nvarchar(50),
	diem int
)
go
create table ChiTietBaiHoc(
	id int identity primary key,
	idBaiHoc int not null,
	tuonghinh nvarchar(max),
	nghiatiengviet nvarchar(50),
	amthanh nvarchar(max),
	video nvarchar(max),
	a nvarchar(50),
	b nvarchar(50),
	c nvarchar(50),
	d nvarchar(50),
	dapdan nvarchar(50)
)
go
create table BaiDaHoc(
	id int identity primary key,
	idBaiHoc int not null,
	idTaiKhoan int not null,
	diem int
)
go
create table BaiKiemTra(
	id int identity primary key,
	idBaiHoc int not null,
	idTaiKhoan int not null,
	diem int
)
go
create proc Pro_BaiDaHoc @idTaiKhoan int
as
begin
	select BaiHoc.id,BaiHoc.chude,BaiHoc.idYoutube,BaiDaHoc.diem from BaiDaHoc,BaiHoc where BaiDaHoc.idBaiHoc = BaiHoc.id and BaiDaHoc.idTaiKhoan = @idTaiKhoan
end
go
create proc Pro_BaiKiemTra @idTaiKhoan int
as
begin
	select BaiHoc.id,BaiHoc.chude,BaiHoc.idYoutube,BaiKiemTra.diem from BaiKiemTra,BaiHoc where BaiKiemTra.idBaiHoc = BaiHoc.id and BaiKiemTra.idTaiKhoan = @idTaiKhoan
end
go
create proc Pro_ChiTietBaiHoc @idBaiHoc int
as
begin
	select ChiTietBaiHoc.id,ChiTietBaiHoc.tuonghinh,ChiTietBaiHoc.nghiatiengviet,ChiTietBaiHoc.amthanh,ChiTietBaiHoc.video,ChiTietBaiHoc.a,ChiTietBaiHoc.b,ChiTietBaiHoc.c,ChiTietBaiHoc.d,ChiTietBaiHoc.dapdan 
	from ChiTietBaiHoc,BaiHoc 
	where ChiTietBaiHoc.idBaiHoc = BaiHoc.id and BaiHoc.id = @idBaiHoc
end