package com.xt.sssp.repository;

import com.xt.sssp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xt
 * @date 2019/2/20 - 14:20
 * @description
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    Employee getByLastName(String lastName);
}
