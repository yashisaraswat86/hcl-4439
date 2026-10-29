package com.abes.hcldatabase.service;

import com.abes.hcldatabase.model.Attendance;
import com.abes.hcldatabase.model.Employee;
import com.abes.hcldatabase.repository.AttendanceRepo;
import com.abes.hcldatabase.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    public String markAttendance(Attendance attendance) {
        if (attendance.getEmployee() == null || attendance.getEmployee().getEmp_id() == null) {
            return "Employee details are required";
        }

        Long empId = attendance.getEmployee().getEmp_id();
        Optional<Employee> employeeOpt = employeeRepo.findById(empId);
        if (employeeOpt.isEmpty()) {
            return "Employee not found with ID: " + empId;
        }

        Employee employee = employeeOpt.get();
        attendance.setEmployee(employee);

        LocalDate attendanceDate = attendance.getAttendancedate() != null 
                ? attendance.getAttendancedate() 
                : LocalDate.now();
        attendance.setAttendancedate(attendanceDate);

        // Default inTime to current time if not provided
        if (attendance.getInTime() == null) {
            attendance.setInTime(LocalDateTime.now());
        }

        if (attendanceRepo.existsByEmployeeAndAttendancedate(employee, attendanceDate)) {
            return "Attendance already marked for today";
        }

        attendanceRepo.save(attendance);
        return "Attendance marked successfully";
    }

    public String checkOut(Long empId, String comment) {
        if (empId == null) {
            return "Employee ID is required";
        }

        Optional<Employee> employeeOpt = employeeRepo.findById(empId);
        if (employeeOpt.isEmpty()) {
            return "Employee not found with ID: " + empId;
        }

        Employee employee = employeeOpt.get();
        LocalDate today = LocalDate.now();

        Optional<Attendance> attendanceOpt = attendanceRepo.findByEmployeeAndAttendancedate(employee, today);
        if (attendanceOpt.isEmpty()) {
            return "No check-in record found for today. Please check in first.";
        }

        Attendance attendance = attendanceOpt.get();
        if (attendance.getOutTime() != null) {
            return "Check-out already marked at " + attendance.getOutTime().toLocalTime().withNano(0);
        }

        attendance.setOutTime(LocalDateTime.now());
        if (comment != null && !comment.isBlank()) {
            attendance.setComment(comment);
        }

        attendanceRepo.save(attendance);
        return "Check-out marked successfully";
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }
}
