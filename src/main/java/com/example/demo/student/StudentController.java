package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//this is more organizied than putting everything in demoApplication
//this is an api layer
@RestController
@RequestMapping(path = "api/v1/student") //instead of localhost:8081 it'll be localhost:8081/api/v1/student


//layer2
public class StudentController {

    private final StudentService studentService;

    @Autowired //to make StudentService auto injected in constructor
    //dependency injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudentsFromDB();
//        return studentService.getStudents();
//        return List.of(
//                new Student(
//                        1111000,
//                        "Reem",
//                        "reem.atalah1@gmail.com",
//                        22,
//                        LocalDate.of(2000, Month.JULY, 9)
//                )
//        );
    }

    //map student, take it from request body
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    //id in path url not in body
    public void deleteStudent(@PathVariable("studentID") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentID}")
    public void updateStudent(
            @PathVariable("studentID")  Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(id,name,email);
    }
}
