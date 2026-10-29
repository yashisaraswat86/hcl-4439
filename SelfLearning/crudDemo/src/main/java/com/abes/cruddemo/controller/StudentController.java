package com.abes.cruddemo.controller;

import com.abes.cruddemo.entity.Student;
import com.abes.cruddemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students") // common api
public class StudentController {
    @Autowired
    private StudentService studentService ;

    //create student
    @PostMapping
    public RequestEntity<Student> createStudent(@RequestBody Student student){
        studentService.createStudent(student) ;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createStudent);
    }

    //read student
    @GetMapping("/read")
    public void readStudent(Student student){

    }

    //read student
    @GetMapping("/read")
    public void readAllStudent(Student student){

    }

    //update student
    @PutMapping("/update")
    public void updateStudent(Student student){

    }

    //delete student
    @DeleteMapping("/delete")
    public void deleteStudent(Student student){

    }
}
