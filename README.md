# :star2: Thực hành lập trình hướng khía cạnh AOP :star2:


# :point_right: Phần 1: Spring Boot Properties, Logging, AOP, Validation

## 1) Cấu hình ứng dụng với các thuộc tính sau
### - Port: 9081

---

file application.properties
``` 
server.port=9081
```
Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221401317-3b75e38e-7bea-42fd-8aa2-9cd499426dbe.png" alt="image" width="50%" style="display:block;">


### - Đường dẫn ứng dụng: likelion
---

file application.properties
``` 
server.servlet.context-path=/likelion
```
Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221404776-e7a20f27-01ec-4bce-acb6-a30a4497405a.png" alt="image" width="50%" style="display:block; ">

### - Tạo một pattern logging: pattern có thể tự chọn

---

- Pattern logging: là một chuỗi định dạng được sử dụng để hiển thị các thông tin log trong các bản ghi log của ứng dụng. Một pattern logging thường bao gồm các định dạng như thời gian, thread, tên logger, mức độ log, nội dung log message và nhiều thông tin khác tùy thuộc vào nhu cầu logging của ứng dụng.

 Tạo "logging.pattern.console trong file application.properties để cấu hình định dạng log được hiển thị trên console:

- %d{yyyy-MM-dd HH:mm:ss.SSS} là định dạng của ngày giờ trong log, được định dạng theo kiểu năm-tháng-ngày giờ-phút-giây và mili giây.

- [%thread] là thông tin về thread, được hiển thị trong ngoặc vuông.

- %-5level là mức độ của log, được hiển thị với 5 ký tự, nếu mức độ của log dài hơn 5 ký tự, nó sẽ được hiển thị đầy đủ.

- %logger{35} là tên của logger, với độ dài tối đa là 35 ký tự.

- %msg%n là nội dung của log, được định dạng với ký tự gạch ngang và xuống dòng mới.

file application.properties
``` 
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n
```

Kết quả 

<img src="https://user-images.githubusercontent.com/72481546/221406716-279c10d4-4532-4325-9624-535df2a19b14.png" alt="image" width="50%" style="display:block;">


### - Cài đặt level của logging là trace

--- 

```
logging.level.root=trace
```

---

## 2) Tạo 2 dto chứa các thuộc tính sau và sử dụng các annotation thực hiện validation

##### **EmployeeDto bao gồm:**

##### - **employeeld**
##### - **name: không được rỗng, độ dài từ 10 đến 50 kí tự**
##### - **birthDate**
##### - **gender**
##### - **email: không được rỗng và đúng với format gmail**

##### **DepartmentDto gồm**

##### - departmentld
##### - deptName: không được rỗng, độ dài từ 10 đến 50 kí tự
##### - description: không được rỗng
##### - List<employeeDto>: valid được các thuộc annotaion đã cài đặt trong employeeDto
 
 Thêm vào pom.xml để sử dụng validation
 
 ```
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
 ```
 file EmployeeDto:
 <img src="https://user-images.githubusercontent.com/72481546/221407908-57802971-788f-4d50-b543-3143568c686d.png" alt="image" width="50%" style="display:block;">
##### Kết quả: 
 ##### - **name: không được rỗng, độ dài từ 10 đến 50 kí tự**
 
 
file DepartmentDto
  <img src="https://user-images.githubusercontent.com/72481546/221407942-4a253b87-e39c-4197-86b0-f2ce1aaa4c42.png" alt="image" width="50%" style="display:block;">

 

###  Tạo api de test valid employeeDto
 
 

###  Tạo api để test valid departmentDto. Lưu ý phải valid được các thuộc tính trong List<employeeDto>

###  3) Tạo hai service tương ứng với hai dto tại (2). Mỗi service tạo một method tương ứng với dto

### EmployeeDto getEmployeeDto(EmployeeDto employeeDto): xử lí trả về dto là đối số truyền vào

### DepartmentDto getDepartmentDto(DepartmentDto departmentDto): xử lí trả về dto là đối số truyền vào

### Mỗi service tạo một Logger và logging đối số truyền vào khi gọi method Ứng dụng AOP để logging như sau:

### - Logging trước và sau khi gọi method getDepartmentDto: nội dụng logging tùy chọn
### - Logging sau khi gọi method getEmployeeDto không thành công: nội dung logging tùy chọn

# :point_right: Phần II: Exception, Filter, Interceptor

## 1) Sử dụng interceptor để logging
###  Tại hàm preHandle với message ”Pre Handle method is Calling”
###  Tại hàm postHandle với message “Post Handle method is Calling”
###  Tại hàm afterCompletion với message "Request and Response is completed"
###  Sử dụng interceptor để thực hiện thống kê thời gian xử lý của mỗi request. Gợi ý như sau:
###  + Tại preHandle thực hiện: request.setAttribute("startTime", startTime) với startTime là thời gian hiện tại 
###  + Tại postHandle lấy giá trị "startTime”, dùng thời gian hiện tại thực hiện phép trừ và logging
<img src ="https://user-images.githubusercontent.com/72481546/221402678-9af35a2a-8cd5-47f2-b6e4-27dac382b3f5.png" width ="50%"/>

## 2) Sử dụng filter để detect các loại browser

###  Nếu request từ các browser thì sẽ được vào controller
###  Nếu request từ Postman sẽ thực hiện như sau:
###  - Response về với status là 403, và message là "Bạn đang yêu cầu từ Postman!"
<img src ="https://user-images.githubusercontent.com/72481546/221402898-af1756fb-0ae1-4a80-a729-2e941350ae8c.png" width ="50%"/>



