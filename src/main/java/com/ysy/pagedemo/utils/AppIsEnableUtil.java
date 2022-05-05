package com.ysy.pagedemo.utils;

import com.ysy.pagedemo.mapper.AppMapper;
import com.ysy.pagedemo.pojo.AppBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppIsEnableUtil {
    @Autowired
    AppMapper appMapper;

    public void change1(int userId,String dvMac,String pgName){
        Map<String ,Object> map = new HashMap<String,Object>();
        map.put("userId",userId);
        map.put("dvMac",dvMac);
        map.put("pgName",pgName);
        appMapper.changeEnable(map);
    }

    public void changeAll0(){
        appMapper.changeAllunEnable();
    }

}
