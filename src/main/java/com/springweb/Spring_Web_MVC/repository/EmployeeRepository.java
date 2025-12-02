package com.springweb.Spring_Web_MVC.repository;

import com.springweb.Spring_Web_MVC.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
