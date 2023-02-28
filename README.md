# :collision: Thực hành Spring boot Test, Actuator, Devtool :collision:


# :zap:  1. Spring boot Test

### Sử dụng JUnit and Mockito để test

- Dependency:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>
```

### :fire: Test tầng Repository

- Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221853670-e37b4436-6bdb-4435-8d8c-dc9baf06490d.png" width = "50%" style ="display:block" />

### :fire: Test tầng Service

- Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221857131-81c14787-076e-4daa-9a19-9987ddaa9575.png" width = "50%" style ="display:block" />

### :fire: Test tầng Controller

- Kết quả:

<img src="https://user-images.githubusercontent.com/72481546/221857228-9dabbf21-1eab-4ec2-843c-d59ea694ce58.png" width = "50%" style ="display:block" />


# :zap: 2. Actuator
### :star: Thêm dependency actuator

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
### :star: Config application.properties 
```
management.server.port=8091
management.endpoints.web.exposure.include=*
management.endpoint.shutdown.enabled=true
```
### :fire: Kết nối actuator để xem các endpoints: 
```
http://localhost:8091/actuator/
```
<img src="https://user-images.githubusercontent.com/72481546/221858002-0edbbfeb-2576-40e1-a560-b1f760028acf.png" width = "50%" style ="display:block" />
<img src="https://user-images.githubusercontent.com/72481546/221858129-402734ee-dd54-4be8-bb3b-00f65694722f.png" width = "50%" style ="display:block" />
<img src="https://user-images.githubusercontent.com/72481546/221858211-0838d7ac-9408-445d-a792-0ce8838923d9.png" width = "50%" style ="display:block" />



### :fire: Truy vấn status ứng dụng: 
```
http://localhost:8091/actuator/health
```

<img src="https://user-images.githubusercontent.com/72481546/221857623-0409f611-3469-4164-a0f9-af516e1b3b93.png" width = "50%" style ="display:block" />


### :fire: Shutdown ứng dụng thông qua Actuator 

```
http://localhost:8091/actuator/shutdown
```

<img src="https://user-images.githubusercontent.com/72481546/221859348-f8fc9a43-ed44-4a12-b2d0-c0b0314cabe8.png" width = "50%" style ="display:block" />


# :zap: 3. Devtool

### :star: Cấu hình trong Run/ Debug configuration 

```
Mục đích: để sử dụng devtool
```
<img src="https://user-images.githubusercontent.com/72481546/221941488-27f87f6e-4730-471e-a375-e863e206bec5.png" width = "50%" style ="display:block" />

### :star: Đổi port sang 8093 bằng devtool 


```Không start lại server từ đầu```


 <img src="https://user-images.githubusercontent.com/72481546/221942545-5561b998-4965-419a-95d0-9eba0642e6df.png" width = "50%" style ="display:block" />

### :star: Kết quả
```
Màn hình Console:
 ```
 <img src="https://user-images.githubusercontent.com/72481546/221943050-98b9e59f-3b76-4d5c-8607-0cdd196ec541.png" width = "50%" style ="display:block" />

```
 http://localhost:8093/actuator
```

<img src="https://user-images.githubusercontent.com/72481546/221942666-5ad9655f-2534-44a9-8301-bd2df13442f3.png" width = "50%" style ="display:block" />

```
http://localhost:8093/actuator/health
```

<img src="https://user-images.githubusercontent.com/72481546/221942750-e7f855a4-6e89-4300-b34f-17aae0815b96.png" width = "50%" style ="display:block" />

