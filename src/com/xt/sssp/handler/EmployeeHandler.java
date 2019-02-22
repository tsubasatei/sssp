package com.xt.sssp.handler;

import com.xt.sssp.entity.Employee;
import com.xt.sssp.service.DepartmentService;
import com.xt.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xt
 * @date 2019/2/20 - 16:52
 * @description
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeService.delete(id);
        return "redirect:/emps";
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id,
                            Map<String, Object> map) {
        if (id != null) {
            Employee employee = employeeService.getById(id);
            employee.setDepartment(null);  // 修改关联属性，先置空
            map.put("employee", employee);
        }
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String update(Employee employee) {
        employeeService.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}")
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        Employee employee = employeeService.getById(id);
        map.put("employee", employee);
        map.put("departments", departmentService.getAll());

        return "emp/input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee) {
        employeeService.save(employee);
        return "redirect:/emps";
    }

    /**
     * 验证用户名是否可用
     * @param lastName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxValidateLastName", method = RequestMethod.POST)
    public String validateLastName(@RequestParam(value="lastName") String lastName) {
        Employee employee = employeeService.getByLastName(lastName);
        if (employee == null) {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 跳转新增页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {

        map.put("departments", departmentService.getAll());
        map.put("employee", new Employee());
        return "emp/input";
    }

    /**
     * 分页查询
     * @param pageNoStr
     * @param map
     * @return
     */
    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
                       Map<String, Object> map) {
        int pageNo = 1;
        try {
            // 对 pageNo 的校验
            pageNo = Integer.parseInt(pageNoStr);
            if (pageNo < 1) {
                pageNo = 1;
            }
        } catch (Exception e) {}
        Page<Employee> page = employeeService.getPage(pageNo, 5);
        map.put("page", page);
        return "emp/list";
    }
}
