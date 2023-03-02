package com.example.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@Autowired
	private RedisTemplate template;
	@Bean
	CommandLineRunner  commandLineRunner(RedisTemplate<Object,Object> redisTemplate){
		return args -> {
			redisTemplate.opsForValue().set("likelion","hello redis");
			System.out.println("Value of key likelion:"+redisTemplate.opsForValue().get("likelion"));
			listExample();
		};
	}

	public void listExample (){
        List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("Redis");

		template.opsForList().rightPushAll("likelion_list",list);
		System.out.println("Size of key likelion:"+template.opsForList().size("likelion_list"));
	}
}
