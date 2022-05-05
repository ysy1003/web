package com.ysy.pagedemo.controller;

import com.ysy.pagedemo.mapper.DepartMapper;
import com.ysy.pagedemo.mapper.EmpMapper;
import com.ysy.pagedemo.pojo.Department;
import com.ysy.pagedemo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DepartMapper departMapper;

    @RequestMapping("/empslist")
    public String showlist(Model model){
        List<Employee> employees = empMapper.queryEmpList();
        model.addAttribute("emps",employees);
        model.addAttribute("departMapper",departMapper);
        return "list";
    }
    //添加员工
    @GetMapping("/addEmp")
    public String toAddpage(Model model){
        List<Department> departments = departMapper.queryDepartList();
        model.addAttribute("departments", departments);
        return "crud/add";
    }

    @PostMapping("/addEmp")
    public String addEmp(Employee employee){
       empMapper.addEmp(employee);
       return "redirect:/empslist";
    }
    //员工列表修改页面
    @GetMapping("/empslist/{id}")
    public String toupdateEmp(@PathVariable("id")Integer id, Model model){
        List<Department> departments = departMapper.queryDepartList();
        Employee employee = empMapper.selectEmpById(id);
        model.addAttribute("emp",employee);
        model.addAttribute("departments", departments);
        return "crud/update";
    }
    @PostMapping("/empslist/{id}")
    public String updateEmp(Employee employee){
        empMapper.updateEmp(employee);
        return "redirect:/empslist";
    }

    @RequestMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        empMapper.deleteEmp(id);
        return "redirect:/empslist";
    }

}
