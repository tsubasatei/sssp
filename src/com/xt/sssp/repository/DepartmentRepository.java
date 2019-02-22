package com.xt.sssp.repository;

import com.xt.sssp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import sun.awt.SunHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @author xt
 * @date 2019/2/21 - 9:14
 * @description
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer>{


    @QueryHints({@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true")})
    @Query("from Department d")
    List<Department> getAll();
}
