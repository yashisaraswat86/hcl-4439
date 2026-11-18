package com.example.cruddtodemo.controller;

import com.example.cruddtodemo.dto.CreateStudentRequestDto;
import com.example.cruddtodemo.dto.CreateStudentResponseDto;
import com.example.cruddtodemo.dto.UpdateStudentRequestDto;
import com.example.cruddtodemo.dto.UpdateStudentResponseDto;
import com.example.cruddtodemo.entity.Student;
import com.example.cruddtodemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //create student
    @PostMapping()
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto student){
        CreateStudentResponseDto createdStudent = studentService.createStudent(student) ;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    //read student
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id){
        CreateStudentResponseDto studentResp = studentService.readStudentById(id , false) ;

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    //read student
    @GetMapping()
    public ResponseEntity<List<CreateStudentResponseDto>> readAllStudent() {
        List<CreateStudentResponseDto> students1 = studentService.readAllStudents() ;

        return ResponseEntity.status(HttpStatus.OK).body(students1);

    }

    // update student
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(
            @PathVariable Long id,
            @RequestBody UpdateStudentRequestDto student) {

            UpdateStudentResponseDto updatedStudent =
                    studentService.updateStudent(id, student);

            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);

    }

    //delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted student with id: " + id)  ;
    }

    //soft delete
    @PatchMapping("/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id){
        studentService.softDeleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully" ) ;
    }
}
