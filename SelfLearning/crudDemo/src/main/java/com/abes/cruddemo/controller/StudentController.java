package com.abes.cruddemo.controller;

import com.abes.cruddemo.entity.Student;
import com.abes.cruddemo.repository.StudentRepository;
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
    @Autowired
    private StudentRepository studentRepository;

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
        Student studentResp = studentService.readStudentById(id) ;
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id  , @RequestBody Student student){
            Student updatedStudent = studentService.updateStudent(id , student) ;
            try{
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(updatedStudent);
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

    }

    //delete student
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentRepository.deleteById(id);
        return "Student deleted successfully" ;
    }
}
