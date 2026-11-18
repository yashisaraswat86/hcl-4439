package com.abes.cruddemo.controller;

import com.abes.cruddemo.entity.Student;

import com.abes.cruddemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students") // common api
public class StudentController {
    @Autowired
    private StudentService studentService ;


    //create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student  createdStudent = studentService.createStudent(student) ;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    //read student
    @GetMapping("/read/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student studentResp = studentService.readStudentById(id , false) ;
        if(studentResp == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    //read student
    @GetMapping("/readall")
    public ResponseEntity<List<Student>> readAllStudent() {
        List<Student> students1 = studentService.readAllStudents() ;
        if(students1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(students1);

    }

    //update student
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(
            @RequestParam Long id,
            @RequestBody Student student) {

        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //delete student
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "Student deleted successfully" ;
    }

    //soft delete
    @PatchMapping("/softdelete/{id}")
    public String softDeleteStudent(@PathVariable Long id){
        studentService.softDeleteById(id);
        return "Student deleted successfully" ;
    }
}
