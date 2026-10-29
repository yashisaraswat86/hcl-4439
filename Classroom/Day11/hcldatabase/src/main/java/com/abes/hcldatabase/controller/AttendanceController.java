package com.abes.hcldatabase.controller;

import com.abes.hcldatabase.model.Attendance;
import com.abes.hcldatabase.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/markattendance")
    public ResponseEntity<String> markAttendance(@RequestBody Attendance attendance) {
        String result = attendanceService.markAttendance(attendance);
        if ("Attendance marked successfully".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else if (result.contains("already marked")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        } else if (result.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PostMapping({"/checkout", "/markattendance/checkout"})
    public ResponseEntity<String> checkOut(@RequestBody Attendance attendance) {
        if (attendance.getEmployee() == null || attendance.getEmployee().getEmp_id() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee details are required");
        }

        String result = attendanceService.checkOut(
                attendance.getEmployee().getEmp_id(),
                attendance.getComment()
        );

        if ("Check-out marked successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else if (result.contains("already marked")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        } else if (result.contains("not found") || result.contains("No check-in")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @GetMapping("/attendance")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }
}
