# Readme


## Project
- jdk 1.8
- database: sql server


## Git cơ bản:
git add . (dấu chấm để thêm hết file, nếu ko thay bằng tên file)
git commit -m"nội dung minh lưu vào git" (commit)
git push -u origin master (dùng thêm cái -u kia thì 1 lần lần sau chỉ cần dùng git push)
 
git pull (kéo từ trên github về, = 2 lệnh fetch + merge, có xung đột file là ko dùng được)
git fetch (lấy thông tin về, chưa thay đổi file)
git merge (dùng cái vừa fetch về đề gộp vào bản mới, nếu có xung đột sẽ hiện thông báo và trong file sẽ có cả 2 phần code khác nhau, phần của mình và phần ở trên github, phải vào file code để chọn xem dùng những đoạn code nào và xóa phần ko dùng)
 
có gắng kéo về trước khi push nhá,  xong merge r push


## Note
- Ae viet may ham trong package service dung cac ham goi tu database: DBBook, DBBookCase, DBUser
