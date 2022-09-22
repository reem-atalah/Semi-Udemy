package com.example.demo.student;

import com.example.demo.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

//@Component
//to make it spring bean, can be used directly injected in another class

//Service and component are the same but Service is better for readability
//now it's a service class
//layer2
@Service

public class StudentService {

    private final StudentRepository studentRepository;
    private Utility utility;

    @Autowired
    public StudentService(StudentRepository studentRepository,Utility utility) {
        this.studentRepository = studentRepository;
        this.utility = utility;
    }

    public void addNewStudent(Student student) {
        //make validation that this email doesn't exist,
        //if exists, through exception
        utility.checkEmailIsTaken(studentRepository,student.getEmail());
        studentRepository.save((student));//save in db

        System.out.println("I'm new course");
    }

    public List<Student> getStudentsFromDB() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        boolean exists= studentRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException(
                    "Course with id: " +id +" doesn't exist"
            );
        studentRepository.deleteById(id);
    }

    // use transactional to update directly in the course retrieved from db, without going to db again
    // i.e. we don't make  studentRepository.save((course));
    // Note: we can use "studentRepository.save((course))" to update
    // as for same id, it will change name and email in existing id
    @Transactional
    public void updateStudent(Long studentID, String name,String email){
        Student retrieveStudent= studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id: " +studentID +" doesn't exist"
                ));
        if (name != null &&
            name.length() > 0 &&
            !Objects.equals(retrieveStudent.getName(), name))

            //this will update it in db, we don't need queries due to using  @Transactional
            retrieveStudent.setName(name);

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(retrieveStudent.getEmail(), email) ){

            utility.checkEmailIsTaken(studentRepository,email);

            retrieveStudent.setEmail(email);
        }
    }

    public Student getStudent(Long studentID) {
        return getStudentsFromDB().stream().filter(t-> t.getIdString().equals(studentID.toString())).findFirst().get();
//        or we can simply say:
//        return studentRepository.findById(studentID)
//                .orElseThrow(() -> new IllegalStateException(
//                //we must put this to convert from Optional<Course> to Course
//                "Course with id: " +studentID +" doesn't exist"
//        ));
    }

    public List<Student> getStudentsWithCourse(Long courseID) {
        return studentRepository.findByCourseId(courseID);
    }

    @Transactional
    public Course addCourseToStudent(Long studentID, Long courseID) {
        Student retrieveStudent= studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id: " +studentID +" doesn't exist"
                ));
        retrieveStudent.setCourse(new Course(courseID));
        //don't need to put name and description, they'll be put automatically from id referenced
        return retrieveStudent.getCourse();

    }
}

