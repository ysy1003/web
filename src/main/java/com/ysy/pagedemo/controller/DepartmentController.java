package com.ysy.pagedemo.controller;

import com.ysy.pagedemo.mapper.DepartMapper;
import com.ysy.pagedemo.mapper.EmpMapper;
import com.ysy.pagedemo.pojo.Department;
import com.ysy.pagedemo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private EmpMapper empMapper;

    @RequestMapping("/departslist")
    public String showDepart(Model model){
        List<Department> departments = departMapper.queryDepartList();
        model.addAttribute("departs",departments);
        return "departlist";
    }

    @GetMapping("/addDepart")
    public String toaddDepart(Model model){
        List<Department> departments = departMapper.queryDepartList();
        model.addAttribute("departs", departments);
        return "crud/addDepart";
    }
    @PostMapping("/addDepart")
    public String addDepart(Department department){
        departMapper.addDepart(department);
        return "redirect:/departslist";
    }

    @GetMapping("/departslist/{id}")
    public String toupdateDepart(@PathVariable("id")Integer id, Model model){
        Department department = departMapper.selectDepartById(id);
        model.addAttribute("depart",department);
        return "crud/updateDepart";
    }
    @PostMapping("/departslist/{id}")
    public String updateDepart(Department department){
        departMapper.updateDepart(department);
        return "redirect:/departslist";
    }

    //删除部门的时候要先去判断部门里面是否存在员工
    @RequestMapping("/deleteDepart/{id}")
    public String deleteDepart(@PathVariable("id")Integer id, RedirectAttributes redirectAttributes){

        if (!empMapper.queryEmpByDepartId(id).isEmpty()){
            //这个可以直接存到session并且重定向转发
            redirectAttributes.addFlashAttribute("tag","部门下有员工存在！");
            return "redirect:/departslist";
        }
        departMapper.deleteDepart(id);
        return "redirect:/departslist";
    }

    @RequestMapping("/depart/{id}")
    public String showEmpByDepart(@PathVariable("id")Integer id,Model model){
        List<Employee> employees = empMapper.queryEmpByDepartId(id);
        String departName = departMapper.selectDepartById(id).getDepartmentName();
        model.addAttribute("emps",employees);
        model.addAttribute("departMapper",departMapper);
        model.addAttribute("departName",departName);
        return "employees";
    }


}
