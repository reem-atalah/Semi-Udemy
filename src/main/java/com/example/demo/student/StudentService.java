package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component
//to make it spring bean, can be used directly injected in another class
@Service
//Service and component are the same but Service is better for readability
//now it's a service class

//layer3
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addNewStudent(Student student) {
        //make validation that this email doesn't exist,
        //if exists, through exception
        checkEmailIsTaken(student.getEmail());
        studentRepository.save((student));//save in db

        System.out.println("I'm new student");
    }

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1111000,
                        "Reem",
                        "reem.atalah1@gmail.com",
//                        22,
                        LocalDate.of(2000, Month.JULY, 9)
                )
        );
    }

    public List<Student> getStudentsFromDB() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        boolean exists= studentRepository.existsById(id);
        if(!exists)
            throw new IllegalStateException(
                    "Student with id: " +id +" doesn't exist"
            );
        studentRepository.deleteById(id);
    }
    @Transactional
    public void updateStudent(Long studentID, String name,String email){
        Student retreiveStudent= studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id: " +studentID +" doesn't exist"
                ));
        if (name != null &&
            name.length() > 0 &&
            !Objects.equals(retreiveStudent.getName(), name))

            //this will update it in db, we don't need queries due to using  @Transactional
            retreiveStudent.setName(name);

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(retreiveStudent.getEmail(), email) ){

            checkEmailIsTaken(email);

            retreiveStudent.setEmail(email);
        }
    }
    //not work
    public void checkEmailIsTaken(String email){
        Optional<Student> studentOptioanl=
                studentRepository.findStudentByEmail(email);
        if(studentOptioanl.isPresent())
            throw new IllegalStateException("email is taken");
    }
}
