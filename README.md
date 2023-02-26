# :star2: Thực hành lập trình hướng khía cạnh AOP :star2:


# :point_right: Phần 1: Spring Boot Properties, Logging, AOP, Validation

## 1) Cấu hình ứng dụng với các thuộc tính sau

### :boom: Port: 9081



file application.properties
``` 
server.port=9081
```
Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221401317-3b75e38e-7bea-42fd-8aa2-9cd499426dbe.png" alt="image" width="50%" style="display:block;">


### :boom: Đường dẫn ứng dụng: likelion


file application.properties
``` 
server.servlet.context-path=/likelion
```
Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221404776-e7a20f27-01ec-4bce-acb6-a30a4497405a.png" alt="image" width="50%" style="display:block; ">

### :boom: Tạo một pattern logging: pattern có thể tự chọn



```
 Pattern logging: là một chuỗi định dạng được sử dụng để hiển thị các thông tin log trong các bản ghi log của ứng dụng. Một pattern logging thường bao gồm các định dạng như thời gian, thread, tên logger, mức độ log, nội dung log message và nhiều thông tin khác tùy thuộc vào nhu cầu logging của ứng dụng.

 Tạo "logging.pattern.console trong file application.properties để cấu hình định dạng log được hiển thị trên console:

- %d{yyyy-MM-dd HH:mm:ss.SSS} là định dạng của ngày giờ trong log, được định dạng theo kiểu năm-tháng-ngày giờ-phút-giây và mili giây.

- [%thread] là thông tin về thread, được hiển thị trong ngoặc vuông.

- %-5level là mức độ của log, được hiển thị với 5 ký tự, nếu mức độ của log dài hơn 5 ký tự, nó sẽ được hiển thị đầy đủ.

- %logger{35} là tên của logger, với độ dài tối đa là 35 ký tự.

- %msg%n là nội dung của log, được định dạng với ký tự gạch ngang và xuống dòng mới.
```

file application.properties
``` 
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n
```

Kết quả 

<img src="https://user-images.githubusercontent.com/72481546/221406716-279c10d4-4532-4325-9624-535df2a19b14.png" alt="image" width="50%" style="display:block;">


### :boom:Cài đặt level của logging là trace



```
logging.level.root=trace
```



## 2) Tạo 2 dto chứa các thuộc tính sau và sử dụng các annotation thực hiện validation

## :information_desk_person: Yêu cầu:

```
EmployeeDto bao gồm:

- employeeld
- name: không được rỗng, độ dài từ 10 đến 50 kí tự

- birthDate
- gender
- email: không được rỗng và đúng với format gmail

DepartmentDto gồm

- departmentld
- deptName: không được rỗng, độ dài từ 10 đến 50 kí tự
- description: không được rỗng
- List<employeeDto>: valid được các thuộc annotaion đã cài đặt trong employeeDto
 
Tạo api de test valid employeeDto 

Tạo api để test valid departmentDto. Lưu ý phải valid được các thuộc tính trong List<employeeDto>
```
 
## :information_desk_person: Thêm vào pom.xml để sử dụng validation
 
 ```
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
 ```
 
## :information_desk_person: File EmployeeDto:
 
 <img src="https://user-images.githubusercontent.com/72481546/221407908-57802971-788f-4d50-b543-3143568c686d.png" alt="image" width="50%" style="display:block;">

 
##### name: không được rỗng, độ dài từ 10 đến 50 kí tự
 
 - name = 1 ký tự ==> Báo lỗi
 
  <img src="https://user-images.githubusercontent.com/72481546/221425356-bdc3a48a-42b6-476f-a001-0d0d87a9f870.png" alt="image" width="50%" style="display:block;">
 
 
##### - email: không được rỗng và đúng với format gmail
 
 - email rỗng  ==>  Báo lỗi 

  <img src="https://user-images.githubusercontent.com/72481546/221425690-fef46d28-172b-4754-a3db-9d9b1bc25be2.png" alt="image" width="50%" style="display:block;">
 

 
## :information_desk_person: File DepartmentDto
 
<img src="https://user-images.githubusercontent.com/72481546/221407942-4a253b87-e39c-4197-86b0-f2ce1aaa4c42.png" alt="image" width="50%" style="display:block;">

##### - deptName: không được rỗng, độ dài từ 10 đến 50 kí tự
 
 - deptName = 2 ký tự => Lỗi

<img src="https://user-images.githubusercontent.com/72481546/221425839-6e585f36-43ab-4f83-9a7b-95ad7cc1a4e4.png" alt="image" width="50%" style="display:block;">

##### - description: không được rỗng
 
 - description: rỗng => lỗi
 
<img src="https://user-images.githubusercontent.com/72481546/221425916-df574c37-77c3-4a73-9a06-95251ad3053f.png" alt="image" width="50%" style="display:block;">

##### - List<employeeDto>: valid được các thuộc annotaion đã cài đặt trong employeeDto

Câu lệnh @Valid được sử dụng trong Spring để kiểm tra tính hợp lệ của đối tượng trước khi chúng được xử lý bởi controller. Trong trường hợp này, nó được sử dụng để kiểm tra tính hợp lệ của danh sách employeeDtoList.

Khi sử dụng @Valid trên một đối tượng kiểu List, Spring sẽ kiểm tra tính hợp lệ của từng phần tử trong danh sách. Nếu một phần tử không hợp lệ, Spring sẽ trả về lỗi với thông tin chi tiết về vị trí và lý do của lỗi.
 
 ```
     @Valid
     List<EmployeeDto> employeeDtoList;
 ```
 
 - Ví dụ: Không nhập trường name cho employeeDtoList
 
<img src="https://user-images.githubusercontent.com/72481546/221426366-55d3fbf0-e22a-40bd-8252-74cde1b349ec.png" alt="image" width="50%" style="display:block;">

## 3) Tạo hai service tương ứng với hai dto tại (2). Mỗi service tạo một method tương ứng với dto
 
### :fire: Yêu cầu 
 
 ```
EmployeeDto getEmployeeDto(EmployeeDto employeeDto): xử lí trả về dto là đối số truyền vào

DepartmentDto getDepartmentDto(DepartmentDto departmentDto): xử lí trả về dto là đối số truyền vào

Mỗi service tạo một Logger và logging đối số truyền vào khi gọi method Ứng dụng AOP để logging như sau:

- Logging trước và sau khi gọi method getDepartmentDto: nội dụng logging tùy chọn
- Logging sau khi gọi method getEmployeeDto không thành công: nội dung logging tùy chọn
 
 ```

### :fire: EmployeeService
 ```
Ta định nghĩa một implementation của interface EmployeeService. Cụ thể là EmployeeServiceImpl được đánh dấu bằng annotation @Service.

Trong EmployeeServiceImpl, LOGGER được khởi tạo bằng LoggerFactory.getLogger(EmployeeServiceImpl.class). Đây là một instance của org.slf4j.Logger được sử dụng để ghi log.

Phương thức getEmployeeDto nhận một tham số là EmployeeDto, sử dụng LOGGER để ghi thông tin của employeeDto vào log và trả về employeeDto. LOGGER.info(employeeDto.toString()) sẽ ghi thông tin của employeeDto dưới dạng string vào file log với mức độ log là info.

Inưnput và output của phương thức getEmployeeDto:

Input: EmployeeDto với các thuộc tính như employeeId, name, birthDate, gender, email đã được khởi tạo.
 
Output: EmployeeDto với các thuộc tính giống như input và được trả về bởi phương thức. Ngoài ra, thông tin của EmployeeDto cũng được ghi vào file log thông qua LOGGER.
 ```

<img src="https://user-images.githubusercontent.com/72481546/221427842-33ad8484-d045-47b6-8b9f-e5892eb2a604.png" alt="image" width="50%" style="display:block;">

<img src="https://user-images.githubusercontent.com/72481546/221427898-770e158f-47a4-4316-828e-99637434653e.png" alt="image" width="50%" style="display:block;">

### :fire: DepartmentService
 
Tương tự:
 
<img src="https://user-images.githubusercontent.com/72481546/221428044-b6ce7b7f-e501-45f3-af68-802603145e52.png" alt="image" width="50%" style="display:block;">
 
<img src="https://user-images.githubusercontent.com/72481546/221428055-a45b4a8c-226d-49ca-a742-7f65d6a81cb2.png" alt="image" width="50%" style="display:block;">

 ### :fire: Controller
 
 ```
 Phương thức POST nhận một đối tượng EmployeeDto từ yêu cầu được gửi đến dưới dạng một tham số @RequestBody.
 @Valid được sử dụng để đảm bảo rằng đối tượng EmployeeDto truyền vào là hợp lệ 
 và thỏa mãn các ràng buộc định nghĩa bởi các annotation như @NotEmpty, @Size và @Email.
 ```
 <img src="https://user-images.githubusercontent.com/72481546/221428441-a87bac04-50a0-4623-8ebc-6c6b98ed7914.png" alt="image" width="50%" style="display:block;">
 
 Tương  tự:
 
 <img src="https://user-images.githubusercontent.com/72481546/221428420-eeac4fdc-4687-4c4d-9f1a-b960889fb3d5.png" alt="image" width="50%" style="display:block;">
 
### :fire:  Áp dụng AOP
 
 ```
Đây là một aspect trong Spring AOP (Aspect Oriented Programming) dùng để tạo interceptor cho các method của package "com.example.exercise.service". Nó được đánh dấu với annotation "@Aspect" để nói với Spring rằng đây là một class chứa các advice (các hành động sẽ được thực hiện trước, sau hoặc sau khi xảy ra ngoại lệ khi method được gọi).

Annotation "@Configuration" được sử dụng để đánh dấu đây là một class cấu hình của Spring. Nó cho phép Spring biết rằng class này sẽ cung cấp các bean để được quản lý bởi Spring IoC (Inversion of Control) container.

Các advice trong class "ServiceAspect" được đánh dấu với các annotation "@Before", "@After" và "@AfterThrowing".

"@Before" được sử dụng để đánh dấu method "before()" và cho phép thực hiện các hành động trước khi method được gọi. Nó sẽ được áp dụng cho tất cả các method của package "com.example.exercise.service".

"@After" được sử dụng để đánh dấu method "after()" và cho phép thực hiện các hành động sau khi method đã được gọi. Nó cũng sẽ được áp dụng cho tất cả các method của package "com.example.exercise.service".

"@AfterThrowing" được sử dụng để đánh dấu method "afterWithBreak()" và cho phép thực hiện các hành động sau khi một ngoại lệ xảy ra trong method đã được gọi. Nó cũng sẽ được áp dụng cho tất cả các method của package "com.example.exercise.service".

Các JoinPoint được sử dụng trong các advice để xác định điểm cụ thể trong mã được thực thi trước, sau hoặc khi ngoại lệ được ném ra. Các điểm nối này sẽ được sử dụng để cung cấp thông tin về method, các đối số của method và các thông tin khác.

Đối tượng Logger được sử dụng để ghi lại các thông tin về quá trình thực thi của method và được tạo ra bằng cách sử dụng LoggerFactory. Nó sẽ cho phép ta ghi các thông tin cần thiết vào console hoặc file log để dễ dàng theo dõi và phân tích.
 ```
 
<img src="https://user-images.githubusercontent.com/72481546/221429078-a47e53e4-0fcd-4f19-bb81-124e0011f468.png" alt="image" width="50%" style="display:block;">
 
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



