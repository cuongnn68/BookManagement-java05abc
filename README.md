
# Readme


### Project
- jdk 1.8
- database: sql server

### Git cơ bản:

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

### Note
- Ae viết mấy hàm trong package service dùng các hàm gọi từ database: DBBook, DBBookCase, DBUser (hoặc tạo thêm hàm nhưng ghi chú rõ yêu cầu cần querry gì từ database)
- Làm xong phần nào của cái controller thì up lên ae xem
