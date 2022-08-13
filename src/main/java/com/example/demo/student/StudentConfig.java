//package com.example.demo.student;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class StudentConfig {
//
//    @Bean
//    //to make commandLineRunner run
//    CommandLineRunner commandLineRunner(StudentRepository repo)
//    {
//        return args -> {
//            //create student
//            Student Rema = new Student(
//                    "Reem",
//                    "reem.atalah1@gmail.com",
//                    LocalDate.of(2000, Month.JULY, 9)
//            );
//
//            Student Nadoon = new Student(
//                    "Nada",
//                    "nada.atalah1@gmail.com",
//                    LocalDate.of(2002, Month.JULY, 9)
//            );
//            repo.saveAll(
//                    List.of(Rema, Nadoon)
//            );
//        };
//    }
//}
