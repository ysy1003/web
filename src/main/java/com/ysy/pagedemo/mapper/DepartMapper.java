package com.ysy.pagedemo.mapper;

import com.ysy.pagedemo.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartMapper {

    List<Department> queryDepartList();
    Department selectDepartById(int id);
    int addDepart(Department department);
    int updateDepart(Department department);
    int deleteDepart(int id);

    int queryIdByName(String departmentName);

}
