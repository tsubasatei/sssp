package com.xt.sssp.test;

import com.xt.sssp.entity.Department;
import com.xt.sssp.repository.DepartmentRepository;
import org.hibernate.annotations.QueryHints;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author xt
 * @date 2019/2/20 - 10:02
 * @description
 */
public class SSSPTest {

    private ApplicationContext ctx = null;
    private DepartmentRepository departmentRepository = null;
    private EntityManagerFactory entityManagerFactory = null;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        departmentRepository = ctx.getBean(DepartmentRepository.class);
        entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
    }

    @Test
    public void testJpaSecondLevelCache() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String jpql = "FROM Department d";
        Query query = entityManager.createQuery(jpql);
        List<Department> departments = query.setHint(QueryHints.CACHEABLE, true).getResultList();
        entityManager.close();


        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        departments = query.setHint(QueryHints.CACHEABLE, true).getResultList();
        entityManager.close();
    }

    @Test
    public void testRepositorySecondLevelCache() {
        List<Department> departments = departmentRepository.getAll();
        departments = departmentRepository.getAll();
    }

    @Test
    public void testDataSource() throws Exception {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
}
