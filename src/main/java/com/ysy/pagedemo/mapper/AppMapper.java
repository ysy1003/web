package com.ysy.pagedemo.mapper;


import com.ysy.pagedemo.pojo.AppBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AppMapper {
    List<AppBean> queryLoadAppsByuserId(int userId);

    List<AppBean> queryAppByDevice(Map<String,Object> map);

    List<Map<String,String>> queryDeviceByUserId(int userId);

    int addLoadApp(AppBean appBean);
    int deleteLoadAppByuserId(int userId);

    int changeEnable(Map<String,Object> map);

    int changeAllunEnable();

    List<String> qureyAllEnable(Map<String,Object> map);

    AppBean selectBypgName(String pgName);


}
