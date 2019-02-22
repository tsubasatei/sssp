package com.xt.sssp.entity;

import javax.persistence.*;

/**
 * @author xt
 * @date 2019/2/20 - 10:41
 * @description
 */
@Cacheable
@Table(name = "SSSP_DEPARTMENTS")
@Entity
public class Department {

    private Integer id;
    private String departmentName;

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DEPARTMENT_NAME")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
