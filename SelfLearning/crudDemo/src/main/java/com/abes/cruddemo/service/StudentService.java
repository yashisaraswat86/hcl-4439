package com.abes.cruddemo.service;


import com.abes.cruddemo.entity.Student;
import com.abes.cruddemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
    @Autowired
    private StudentRepository studentRepository ;

    public Student createStudent(Student student){
            return studentRepository.save(student);
    }
}
