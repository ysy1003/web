package com.ysy.pagedemo.mapper;

import com.ysy.pagedemo.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//Mapper注解，表示这是一个mybatis的mapper类，代替Dao层作用
@Mapper
@Repository
public interface EmpMapper {

    List<Employee> queryEmpList();

    List<Employee> queryEmpByDepartId(int id);

    Employee selectEmpById(int id);

    int selectIdByUser(Map<String,Object> map);

    int addEmp(Employee employee);

    int updateEmp(Employee employee);

    int deleteEmp(int id);

    Employee loginByUser(Map<String,Object> map);


}
