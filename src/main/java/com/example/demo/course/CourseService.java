package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addNewCourse(Course course) {
        courseRepository.save((course));
        System.out.println("I'm new course");
    }

    public List<Course> getCoursesFromDB() {
        return courseRepository.findAll();
    }

    public void deleteCourse(Long courseID) {
        // should be name instead of id, bec, user should has no access to id
        boolean exists= courseRepository.existsById(courseID);
        if(!exists)
            throw new IllegalStateException(
                    "Course with id: " +courseID +" doesn't exist"
            );
        courseRepository.deleteById(courseID);
    }

    public void updateCourse(Course course){
        courseRepository.save(course);
    }

    public Course getCourse(Long courseID) {
        return getCoursesFromDB().stream().filter(t-> t.getIdString().equals(courseID.toString())).findFirst().get();
    }
}

