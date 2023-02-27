
# :star2: Bài tập thực hành JWT :star2:


## :zap: http://localhost:8080/api/login :zap:

<img src="https://user-images.githubusercontent.com/72481546/221622601-18bce27f-fb2b-438c-8c41-99386198a26a.png">

## :zap: http://localhost:8080/api/login :zap:

<img src="https://user-images.githubusercontent.com/72481546/221623043-b9b45afc-fa39-4a75-9b2c-085e07bec73b.png">

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
 
## :green_heart: Yêu cầu:
```
Tại hàm preHandle với message ”Pre Handle method is Calling”
Tại hàm postHandle với message “Post Handle method is Calling”
Tại hàm afterCompletion với message "Request and Response is completed"
Sử dụng interceptor để thực hiện thống kê thời gian xử lý của mỗi request. Gợi ý như sau:
+ Tại preHandle thực hiện: request.setAttribute("startTime", startTime) với startTime là thời gian hiện tại 
+ Tại postHandle lấy giá trị "startTime”, dùng thời gian hiện tại thực hiện phép trừ và logging
 
 ```
 <img src ="https://user-images.githubusercontent.com/72481546/221402678-9af35a2a-8cd5-47f2-b6e4-27dac382b3f5.png" width ="50%"/>
 
 ## :green_heart: Thực hiện.
 ```
 Interceptor là một bộ lọc được sử dụng để thực hiện một số thao tác nghiệp vụ trước hoặc sau khi một request được xử lý bởi controller. Ví dụ như xác thực người dùng, kiểm tra quyền truy cập, thêm header vào response, v.v. Interceptor được sử dụng trong Spring framework và có thể được đăng ký với một hoặc nhiều interceptor chain.
 
 
 ```
 File ProductServiceInterceptor
 ``` java
 package com.example.exercise.interceptor;

import com.example.exercise.service.Impl.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class ProductServiceInterceptor implements HandlerInterceptor {
    private final Logger LOGGER =  LoggerFactory.getLogger(ProductServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Pre Handle method is Calling");
        Date date = new Date();
        long timeMilli = date.getTime();
        request.setAttribute("startTime",timeMilli);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Date date = new Date();
        long timeBefore = (long) request.getAttribute("startTime");
        long timeMilli = date.getTime();
        timeMilli-=timeBefore;
        LOGGER.info("Execution: "+ timeMilli+"ms");
        LOGGER.info("Post Handle method is Calling");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("Request and Response is completed");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
 ```
 
 ```
 Code trên định nghĩa một class ProductServiceInterceptor và implements interface HandlerInterceptor. 

HandlerInterceptor là một interface trong Spring Framework cho phép ta tương tác với request/response trước hoặc sau khi chúng được xử lý bởi một controller. Interface này bao gồm ba phương thức chính là preHandle(), postHandle(), và afterCompletion().

preHandle(): được gọi trước khi controller được xử lý request. Trả về giá trị boolean để xác định liệu controller có nên được tiếp tục xử lý hay không.
 
postHandle(): được gọi sau khi controller đã xử lý request và trả về ModelAndView (hoặc không). Cho phép ta thực hiện các thao tác bổ sung trên ModelAndView trước khi trả về cho client.
 
afterCompletion(): được gọi khi đã hoàn thành việc xử lý request và trả về cho client.
 
Trong class ProductServiceInterceptor, phương thức preHandle() được override để ghi log thông tin trước khi controller được gọi, lưu thời gian bắt đầu xử lý request vào thuộc tính startTime của request.

Phương thức postHandle() được override để ghi log thời gian xử lý request, tính toán thời gian xử lý bằng cách lấy thời gian hiện tại trừ đi thời gian bắt đầu lưu trong thuộc tính startTime, và ghi log thông tin sau khi controller đã xử lý request.

Phương thức afterCompletion() được override để ghi log khi đã hoàn thành việc xử lý request.
 ```
 
 ```
 Sau khi tạo Interceptor, chúng ta cần thêm nó vào dự án. Đoạn mã trên là để thêm Interceptor vào dự án.
 ```
 <img src ="https://user-images.githubusercontent.com/72481546/221433163-754dc4f0-063f-477b-9ccc-bff12f70a7d0.png" width ="50%"/> 
 
## 2) Sử dụng filter để detect các loại browser
 
 ## :sparkles: Yêu cầu:
 
```
###  Nếu request từ các browser thì sẽ được vào controller
###  Nếu request từ Postman sẽ thực hiện như sau:
###  - Response về với status là 403, và message là "Bạn đang yêu cầu từ Postman!"
 
 ```
 <img src ="https://user-images.githubusercontent.com/72481546/221402898-af1756fb-0ae1-4a80-a729-2e941350ae8c.png" width ="50%"/>
 
 ## :sparkles: Thực hiện

 <img src ="https://user-images.githubusercontent.com/72481546/221433556-f62964ed-682e-4738-9edd-6a281f8a863b.png" width ="50%"/>
 
```
 Filter trong Spring Boot là một thành phần giúp xử lý các yêu cầu của người dùng trước khi chúng được gửi đến servlet hoặc trả về sau khi servlet đã xử lý yêu cầu đó. Filter cung cấp cho các lập trình viên một cách để thực hiện các hoạt động tiền xử lý, xử lý ngoại lệ, ghi lại hoạt động và điều khiển truy cập. Filter thường được sử dụng để thực hiện các chức năng như kiểm soát truy cập, mã hóa yêu cầu và phản hồi, kiểm soát phiên và nhiều hơn nữa. Các filter trong Spring Boot được thiết kế để hoạt động với Spring MVC và Spring WebFlux và có thể được cấu hình bằng cách sử dụng các annotation như @Component và @Order hoặc thông qua các lớp cấu hình FilterRegistrationBean.
 ```
 
 
 ```
Class TransactionFilter implements interface Filter, trong đó có 3 phương thức cần phải triển khai:

Phương thức init(FilterConfig filterConfig): Phương thức này được gọi một lần duy nhất sau khi filter được tạo ra.
 
Phương thức doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain): Đây là phương thức quan trọng nhất của Filter, 
nó được gọi mỗi khi một request tới đích và trước khi response được trả về. Ở đoạn code này, phương thức kiểm tra header của request để xác định xem client có phải là Postman hay không. Nếu là Postman, phương thức sẽ trả về response với status code là 403 Forbidden và thông báo "Access is not allowed".
 
Phương thức destroy(): Phương thức này được gọi một lần khi filter bị hủy.
 
Class TransactionFilter được đánh dấu bằng @Order(1) để chỉ định thứ tự ưu tiên trong việc xử lý các filter. Với giá trị 1, TransactionFilter sẽ được thực hiện trước các filter khác có giá trị ưu tiên cao hơn.
 ```
 
 <img src ="https://user-images.githubusercontent.com/72481546/221434544-63acd642-7d56-4d04-b01c-c074fae9a20c.png" width ="50%"/>
 
 ```
 một lớp xử lý ngoại lệ (ExceptionHandler) được đánh dấu bằng @ControllerAdvice, được sử dụng để xử lý các ngoại lệ xảy ra trong quá trình thực thi các phương thức xử lý yêu cầu (@Controller).

Trong đoạn code này, lớp ValidationHandler kế thừa từ ResponseEntityExceptionHandler - một lớp cơ sở của Spring Boot được sử dụng để xử lý các ngoại lệ liên quan đến HTTP (HTTP Exceptions), chẳng hạn như MethodArgumentNotValidException, một ngoại lệ xảy ra khi tham số truyền vào phương thức xử lý yêu cầu không hợp lệ.

Phương thức handleMethodArgumentNotValid được sử dụng để xử lý ngoại lệ MethodArgumentNotValidException và trả về một đối tượng ResponseEntity chứa thông tin về các lỗi được tìm thấy trong các trường dữ liệu đầu vào không hợp lệ. Trong phương thức này, các lỗi được lưu trữ trong một Map với tên trường là khóa và thông báo lỗi là giá trị tương ứng.

HttpStatus.BAD_REQUEST được trả về để biểu thị rằng yêu cầu của người dùng không hợp lệ và trả về HTTP status code 400 (Bad Request).

 ```
### Kết quả

<img src ="https://user-images.githubusercontent.com/72481546/221494133-82c8bafb-3b78-492b-832d-cf5490d18aa7.png" width ="50%"/>
 





