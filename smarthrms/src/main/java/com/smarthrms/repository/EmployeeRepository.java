
package com.smarthrms.repository;

import com.smarthrms.entity.Employee;
import com.smarthrms.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByCandidate_Id(Long candidateId);

    boolean existsByCandidate_Id(Long candidateId);

    List<Employee> findByStatus(EmployeeStatus status);

    List<Employee> findByDepartment_Id(Long departmentId);

    long countByDepartment_Id(Long departmentId);
}



