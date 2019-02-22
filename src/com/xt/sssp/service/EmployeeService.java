package com.xt.sssp.service;

import com.xt.sssp.entity.Employee;
import com.xt.sssp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author xt
 * @date 2019/2/20 - 16:17
 * @description
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Employee getById(Integer id) {
        return employeeRepository.findOne(id);
    }

    @Transactional
    public void delete(Integer id) {
        employeeRepository.delete(id);
    }

    @Transactional
    public void save(Employee employee) {
        if(employee.getId() == null) {
            employee.setCreateTime(new Date());
        }
        employeeRepository.saveAndFlush(employee);
    }

    @Transactional(readOnly = true)
    public Employee getByLastName(String lastName) {
        Employee employee = employeeRepository.getByLastName(lastName);
        return employee;
    }

    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNo, int pageSize) {
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }
}
