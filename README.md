# :star2: Bài tập thực hành REST API :star2:


## :point_right: Yêu cầu bài tập

Xây dựng một Rest CRUD APIs sử dụng JPA, H2 đơn giản
- Thực nghiệm toàn bộ luồng đi trong Spring Boot
- Viết code cho từng tầng trong mô hình
- Tạo bean, sử dụng bean cho từng tầng trong mô hình
- Kết nối thực hiện các query với H2 Database

Trong đó:
- Mỗi Tutorial có: id, title, description, published status
- Các Api có chức năng tạo, truy vấn, cập nhật, xoá các Tutorial
- Một số Api hỗ trợ tìm kiếm theo published status hoặc title

![image](https://user-images.githubusercontent.com/72481546/220610492-0012aa43-024d-453c-b300-0607b5f16f7c.png)


## :zap: Kết quả sau khi chạy :zap:
### :heart: 1. POST /api/tutorials
<img src="https://user-images.githubusercontent.com/72481546/221475860-75406b49-d44e-4633-ac37-7831423afd32.png" alt="image" width="50%" style="display:block;">

### :heart: 2. GET /api/tutorials
<img src="https://user-images.githubusercontent.com/72481546/221475916-d5db52d3-0265-463a-9cc3-e4b2a74683ae.png" alt="image" width="50%" style="display:block;">

### :heart: 3. GET /api/tutorials/:id
<img src="https://user-images.githubusercontent.com/72481546/221475978-32d0bb02-4b4e-44a9-8a91-222e3ef58c09.png" alt="image" width="50%" style="display:block;">

### :heart: 4. PUT /api/tutorials/:id
<img src="https://user-images.githubusercontent.com/72481546/221476212-a467003b-c3ee-4c9c-935c-4d694b7efed5.png" alt="image" width="50%" style="display:block;">

### :heart: 5. DELETE /api/tutorials/:id
<img src="https://user-images.githubusercontent.com/72481546/221476283-be9f38e8-8b8b-4356-9f65-9bb29eed6c77.png" alt="image" width="50%" style="display:block;">

### :heart: 6. DELETE /api/tutorials
<img src="https://user-images.githubusercontent.com/72481546/221476352-fe80ec30-f7b7-4e4b-89fb-6c9fea26e3e1.png" alt="image" width="50%" style="display:block;">

### :heart:7. GET /tutorials/published

- Get all

<img src="https://user-images.githubusercontent.com/72481546/221476816-8f605e7e-dc69-4b61-b071-094a3905d13c.png" alt="image" width="50%" style="display:block;">

- Get by published

<img src="https://user-images.githubusercontent.com/72481546/221476926-dd31a75b-0abe-4c2d-94ea-7fb914af5db9.png" alt="image" width="50%" style="display:block;">

### :heart: 8. GET /tutorials?title=[keyword]

<img src="https://user-images.githubusercontent.com/72481546/221477020-7edfaa0d-e2dd-4b71-9cc1-3530bbce4f18.png" alt="image" width="50%" style="display:block;">
