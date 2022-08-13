package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseService.getCoursesFromDB();
    }

    @RequestMapping("/{courseID}")
    public Course getACourse(@PathVariable Long courseID){
        return courseService.getCourse(courseID);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Course course){
        courseService.addNewCourse(course);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCourse")
    public void registerNewStudentAnotherWay(@RequestBody Course course)
    {
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseID}")
    public void deleteStudent(@PathVariable("courseID") Long id){
        courseService.deleteCourse(id);
    }

    @PutMapping(path = "{courseID}")
    public void updateStudent(
            @PathVariable("courseID")  Long id,
            @RequestBody Course course){
        courseService.updateCourse(course);
    }
}
