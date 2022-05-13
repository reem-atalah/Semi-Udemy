package com.example.demo;

import com.example.demo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
//@RestController

//layer1
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	//@GetMapping //to be restful, it won't be on web service without it
	public String hello(){
		return "hello Rema";
	}

	//@GetMapping //this is put for only one function
	public List<String> helloList(){
		return List.of("hello",  "Reema");
	}

//	@GetMapping
//	public List<Student> helloClass(){//get array of object
//		return List.of(
//				new Student(
//						1111000,
//						"Reem",
//						"reem.atalah1@gmail.com",
//						22,
//							LocalDate.of(2000, Month.JULY, 9)
//				)
//		);
//	}
}

