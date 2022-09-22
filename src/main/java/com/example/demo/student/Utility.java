package com.example.demo.student;

import org.springframework.context.annotation.Configuration;
import java.lang.String;
import java.util.Optional;

@Configuration
public class Utility {
    public void checkEmailIsTaken(StudentRepository studentRepository, String email){
        Optional<Student> studentOptional=
                studentRepository.findByEmail(email);
        if(studentOptional.isPresent())
            throw new IllegalStateException("email is taken");
    }
}
