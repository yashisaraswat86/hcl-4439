package com.abes.hcldatabase.repository;

import com.abes.hcldatabase.model.Attendance;
import com.abes.hcldatabase.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM attendance a WHERE a.employee.emp_id = :empId AND a.attendancedate = :attendanceDate")
    boolean existsByEmployeeIdAndDate(@Param("empId") Long empId, @Param("attendanceDate") LocalDate attendanceDate);

    boolean existsByEmployeeAndAttendancedate(Employee employee, LocalDate attendancedate);

    Optional<Attendance> findByEmployeeAndAttendancedate(Employee employee, LocalDate attendancedate);

    List<Attendance> findByEmployee(Employee employee);
}
