package com.example.demo.student;

import com.example.demo.course.Course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

//take this data of course to create table in db using spring data JPA to add, delete ..etc
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name ="student_sequence",
            sequenceName =  "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    //lombok instead of setters and getters
    private @Getter Long id;
    private @Getter @Setter String name;
    private @Getter @Setter String email;
    private @Getter @Setter LocalDate dob;
    @ManyToOne
    private @Getter @Setter Course course; // assume now that a student can assign to one course

    //make age not a column in db
    //and remove it from constructor
//    @Transient
//    private @Getter Integer age =  Period.between(this.dob, LocalDate.now()).getYears();

    public String getIdString() {
        return String.valueOf(id);
    }


    public Student() {
    }
    //without id
//    this uses @Id @SequenceGenerator @GeneratedValue,according to sequence of id
    public Student(String name, String email, LocalDate dob, Long courseId) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.course=new Course(courseId);
    }


}
