package com.example.cruddtodemo.service;

import com.example.cruddtodemo.dto.CreateStudentRequestDto;
import com.example.cruddtodemo.dto.CreateStudentResponseDto;
import com.example.cruddtodemo.dto.UpdateStudentRequestDto;
import com.example.cruddtodemo.dto.UpdateStudentResponseDto;
import com.example.cruddtodemo.entity.Student;
import com.example.cruddtodemo.exception.DuplicateResourseException;
import com.example.cruddtodemo.exception.ResourseNotFoundException;
import com.example.cruddtodemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepository ;

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReq){
        Student student = mapToEntity(studentReq) ;
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        if(emailExists(student)){
            throw new DuplicateResourseException("Student with email" + student.getEmail() + " already exists");
        }
        Student studentRes = studentRepository.save(student);
        return mapToDto(studentRes) ;

    }



    public CreateStudentResponseDto readStudentById(Long id , Boolean deleted){
       Student StudentResp = studentRepository.findByIdAndDeletedIsFalse(id)
               .orElseThrow(
                       () -> new ResourseNotFoundException("Student with id " + id + " not found"));
       return mapToDto(StudentResp);
    }

    public List<CreateStudentResponseDto> readAllStudents(){
        List<Student> students = studentRepository.findByDeletedFalse();
        return students.stream().
                map(this :: mapToDto).toList();
    }

    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto student) {
        Student updatedStudent = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourseNotFoundException("Student not found with id: " + id));

        updatedStudent.setName(student.getName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setSubject(student.getSubject());
        updatedStudent.setRollNo(student.getRollNo());
        updatedStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent =  studentRepository.save(updatedStudent);
        return mapToUpdatedDto(savedStudent) ;
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

    public void softDeleteById(Long id){
        Student student  = studentRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourseNotFoundException("Student with id " + id + " not found"));
        student.setDeleted(true);
        studentRepository.save(student);
    }

    private Student mapToEntity(CreateStudentRequestDto student){
        Student newstudent = new Student();
        newstudent.setName(student.getName());
        newstudent.setEmail(student.getEmail());
        newstudent.setAge(student.getAge());
        newstudent.setSubject(student.getSubject());
        newstudent.setRollNo(student.getRollNo());
        newstudent.setDeleted(false);
        return newstudent;
    }

    private CreateStudentResponseDto mapToDto(Student student){
        CreateStudentResponseDto studentResponseDto = new CreateStudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setRollNo(student.getRollNo());
        studentResponseDto.setCreatedAt(student.getCreatedAt());
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());
        studentResponseDto.setMessage("Student Saved Successfully");
        return studentResponseDto;

    }

    private UpdateStudentResponseDto mapToUpdatedDto(Student student){
        UpdateStudentResponseDto studentResponseDto = new UpdateStudentResponseDto ();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setRollNo(student.getRollNo());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());
        studentResponseDto.setMessage("Student Saved Successfully");
        return studentResponseDto;
    }

    private boolean emailExists(Student student ){
        return studentRepository.existsByEmail(student.getEmail()) ;
    }
}
