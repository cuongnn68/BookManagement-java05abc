
# Readme


## Project
- jdk 1.8
- database: sql server 
- jdbc driver sql server jre 8

## Kết nối đến SQL Server

#### Tạo tài khoản
- Vào sql server management bằng tài khoản windows authentications (mặc định)
- Chọn Sercurity -> Chuột phải vào login -> new login
- Chọn SQL Server authentication
- Trong code java tài khoản là admin, pass là 1
- Vào tab server Roles, chọn sysadmin (cho tiện)
- OK

#### Bật giao thức kết nối của sql server
- Bật SQL server configuration manager
- Chọn Sql server services, SQL Server -> Stopped (dừng db)
- Chọn SQL native client 11.0 Configuration -> Client protocol -> Enable tất cả
- Bật lại SQL server

#### JDBC Driver SQL Server 
https://docs.microsoft.com/vi-vn/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server


## Git cơ bản:

#### Tải code về máy: 
```git clone https://github.com/cuongnn68/BookManagement-java05abc```

#### đồng bộ code giữa máy và git hub:
```git add . ```	Thêm file vào để commit. Dấu chấm để thêm hết file, nếu ko thay bằng tên file

```git commit -m"nội dung minh lưu vào git" ```	commit vào git, tạo ra một bản ghi

```git push -u origin master ```	Đẩy code lên github (chỉ những cái đã commit). Dùng thêm cái -u kia thì chỉ 1 lần, lần sau chỉ cần dùng git push

```git pull ```	kéo từ trên github về, = 2 lệnh fetch + merge, có xung đột file là ko dùng được

```git fetch ```	lấy thông tin về, chưa thay đổi file

```git merge```	dùng cái vừa fetch về đề gộp vào bản mới, nếu có xung đột sẽ hiện thông báo và trong file sẽ có cả 2 phần code khác nhau, phần của mình và phần ở trên github, phải vào file code để chọn xem dùng những đoạn code nào và xóa phần ko dùng
 
Có gắng kéo về trước khi push nhá,  xong r merge và push