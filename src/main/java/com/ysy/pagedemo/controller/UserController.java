package com.ysy.pagedemo.controller;

import com.ysy.pagedemo.mapper.DepartMapper;
import com.ysy.pagedemo.mapper.EmpMapper;
import com.ysy.pagedemo.pojo.Department;
import com.ysy.pagedemo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//android
@RestController
public class UserController {
    @Autowired
    EmpMapper empMapper;
    @Autowired
    DepartMapper departMapper;

    @GetMapping("/loginquery")
    public Employee test1(@RequestParam(value = "name", required = false)String name,
                         @RequestParam(value = "email", required = false)String email,
                          @RequestParam(value = "departId", required = false)int departId){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name",name);
            map.put("email",email);
            map.put("departId",departId);
            return empMapper.loginByUser(map);
    }

    @GetMapping("/departlist")
    public List<Department> test2(){
        return departMapper.queryDepartList();
    }

    @GetMapping("/queryDepartId")
    public int test3(@RequestParam(value = "name", required = false)String name){
        return departMapper.queryIdByName(name);
    }

    @PostMapping("/createEmp")
    public boolean test4(@RequestBody Employee employee){
        int ret = empMapper.addEmp(employee);
        if (ret==1){
            return true;
        }
        return false;
    }


    @PostMapping("/test10086/{id}")
    public int test10086(@PathVariable("id")Integer id,@RequestBody Employee employee){
        return empMapper.updateEmp(employee);
    }



}
