package com.example.demo.course;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Course {
    @Id
    @SequenceGenerator(
            name ="course_sequence",
            sequenceName =  "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private @Getter long id;
    private @Getter @Setter String name;
    private @Getter @Setter String description;

    public Course(Long courseID) {
        this.id = courseID;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    //lombok instead of setters and getters

    public Course() {
    }
    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + description + '\'' +
                '}';
    }
}
