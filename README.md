# :star2: Thực hành lập trình hướng khía cạnh AOP :star2:


## :point_right: Phần 1: Spring Boot Properties, Logging, AOP, Validation

### 1) Cấu hình ứng dụng với các thuộc tính sau
- **port: 9081**

file application.properties
``` 
server.port=9081
```
Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221401317-3b75e38e-7bea-42fd-8aa2-9cd499426dbe.png" alt="image" width="50%" style="display:block; margin:auto;">

- **Đường dẫn ứng dụng: likelion**
- **Tạo một pattern logging: pattern có thể tự chọn**
- **Cài đặt level của logging là trace**
### 2) Tạo 2 dto chứa các thuộc tính sau và sử dụng các annotation thực hiện validation
**EmployeeDto bao gồm:**
- **employeeld**
- **name: không được rỗng, độ dài từ 10 đến 50 kí tự**
- **birthDate**
- **gender**
- **email: không được rỗng và đúng với format gmail**

**DepartmentDto gồm**
- **departmentld**
- **deptName: không được rỗng, độ dài từ 10 đến 50 kí tự**
- **description: không được rỗng**
- **List<employeeDto>: valid được các thuộc annotaion đã cài đặt trong employeeDto**

**Tạo api de test valid employeeDto**

**Tạo api để test valid departmentDto. Lưu ý phải valid được các thuộc tính trong List<employeeDto>**

###  3) Tạo hai service tương ứng với hai dto tại (2). Mỗi service tạo một method tương ứng với dto

##### EmployeeDto getEmployeeDto(EmployeeDto employeeDto): xử lí trả về dto là đối số truyền vào

##### DepartmentDto getDepartmentDto(DepartmentDto departmentDto): xử lí trả về dto là đối số truyền vào

##### Mỗi service tạo một Logger và logging đối số truyền vào khi gọi method Ứng dụng AOP để logging như sau:

##### - Logging trước và sau khi gọi method getDepartmentDto: nội dụng logging tùy chọn
##### - Logging sau khi gọi method getEmployeeDto không thành công: nội dung logging tùy chọn

## :point_right: Phần II: Exception, Filter, Interceptor

### 1) Sử dụng interceptor để logging
##### Tại hàm preHandle với message ”Pre Handle method is Calling”
##### Tại hàm postHandle với message “Post Handle method is Calling”
##### Tại hàm afterCompletion với message "Request and Response is completed"
##### Sử dụng interceptor để thực hiện thống kê thời gian xử lý của mỗi request. Gợi ý như sau:
##### + Tại preHandle thực hiện: request.setAttribute("startTime", startTime) với startTime là thời gian hiện tại 
##### + Tại postHandle lấy giá trị "startTime”, dùng thời gian hiện tại thực hiện phép trừ và logging
<img src ="https://user-images.githubusercontent.com/72481546/221402678-9af35a2a-8cd5-47f2-b6e4-27dac382b3f5.png" width ="50%"/>

### 2) Sử dụng filter để detect các loại browser

##### Nếu request từ các browser thì sẽ được vào controller
##### Nếu request từ Postman sẽ thực hiện như sau:
##### - Response về với status là 403, và message là "Bạn đang yêu cầu từ Postman!"
<img src ="https://user-images.githubusercontent.com/72481546/221402898-af1756fb-0ae1-4a80-a729-2e941350ae8c.png" width ="50%"/>



