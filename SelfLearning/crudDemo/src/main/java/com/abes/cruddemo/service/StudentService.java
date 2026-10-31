package com.abes.cruddemo.service;


import com.abes.cruddemo.entity.Student;
import com.abes.cruddemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentRepository studentRepository ;

    public Student createStudent(Student student){
            return studentRepository.save(student);
    }

    public Student readStudentById(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> readAllStudents(){
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id  ,  Student student){
        Student updatedStudent = studentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        if(updatedStudent != null){
            updatedStudent.setId(student.getId());
            updatedStudent.setName(student.getName());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setAge(student.getAge());
            updatedStudent.setSubject(student.getSubject());
            updatedStudent.setRollNo(student.getRollNo());
        }
        return studentRepository.save(updatedStudent);
    }

    public void deleteStudentById(Long id){
         studentRepository.deleteById(id);
    }
}
