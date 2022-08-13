package com.example.demo.student;

import com.example.demo.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//layer1
//this is more organized than putting everything in demoApplication
//this is an api layer
@RestController
@RequestMapping(path = "api/v1/student") //instead of localhost:8081 it'll be localhost:8081/api/v1/course
public class StudentController {

    private final StudentService studentService;

    //to make CourseService auto injected in constructor
    //dependency injection
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudentsFromDB();
    }

    //studentID names are same so no need to say it inside PathVariable
    // use RequestMapping(its default is GetMapping) instead of GetMapping
    @RequestMapping("get_student/{studentID}")
    public Student getAStudent(@PathVariable Long studentID){
        return studentService.getStudent(studentID);
    }

    //map course, take it from request body
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    //another way for post request
    @RequestMapping(method = RequestMethod.POST, value = "/addStudent")
    public void registerNewStudentAnotherWay(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    //id in path url not in body
    //studentID in path differs from id in function
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

    @PutMapping(path = "{studentID}/{courseID}")
    public Course assignStudentToCourse(
            @PathVariable Long studentID,
            @PathVariable Long courseID){
        return studentService.addCourseToStudent(studentID, courseID);
    }

    @GetMapping(path = "get_students_with_course_id/{courseID}")
    public List<Student> getStudents(
            @PathVariable("courseID")  Long courseID
    ){
        return studentService.getStudentsWithCourse(courseID);
    }


}
