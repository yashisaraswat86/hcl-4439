package com.abes.hcldatabase.repository;

import com.abes.hcldatabase.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo  extends JpaRepository<Employee , Long>{



}
