package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//anything related to db access is convention to be called Repository
//this interface will be used in course service
//layer 3
@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {
    //interfaces from class course
    //long: type of ID
    // instead of JpaRepository, we can use CrudRepository
    //JPA repository extends CrudRepository and PagingAndSorting repository.
    //It inherits finders from crud repository such as findOne, save, gets and removes an entity.
    //Inherits from pagination and sorting,delete records in batch, flushing data directly to a database base

    //This converted to
    // SELECT * FROM Course WHERE email= email?
    //Course: Course entity name
//    @Query("SELECT s FROM Course s WHERE s.email= ?1")
//    Optional<Course> findStudentByEmail(String email);

    // or we can use just this:
    // we don't need to implement this method, as we use findBy property
    // findByEmail written in camel-case
    // spring data JPA will implement it itself! no need for @Query
    Optional<Student> findByEmail(String email);
    public List<Student> findByCourseId(Long courseId);
}
