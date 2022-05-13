package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

//anything related to db access is convention to be called Repository
//this interface will be used in student service
@Repository

//layer 4
public interface StudentRepository
        extends JpaRepository<Student, Long> { //interfaces from class student
    //long: type of ID

    //This converted to
    // SELECT * FROM Student WHERE email= email?
    //Student: Student entity name
    @Query("SELECT s FROM Student s WHERE s.email= ?1")
    Optional<Student> findStudentByEmail(String email);
}
