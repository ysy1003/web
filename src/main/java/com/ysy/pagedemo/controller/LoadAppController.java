package com.ysy.pagedemo.controller;


import com.sun.org.glassfish.external.statistics.annotations.Reset;
import com.ysy.pagedemo.mapper.AppMapper;
import com.ysy.pagedemo.mapper.EmpMapper;
import com.ysy.pagedemo.pojo.AppBean;
import com.ysy.pagedemo.utils.AppIsEnableUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoadAppController {

    @Autowired
    AppMapper appMapper;
    @Autowired
    EmpMapper empMapper;
    @Autowired
    AppIsEnableUtil appIsEnableUtil;

    ArrayList<AppBean> appBeanlist = null;


    @PostMapping("/loadapps")
    @ResponseBody
    public String loadApps(@RequestBody ArrayList<AppBean> appBeans){
        //System.out.println(appBeans);
        System.out.println(appBeans.size());
        appBeanlist = new ArrayList<AppBean>(appBeans);

        for (AppBean appBean : appBeanlist) {
            try {
                appMapper.addLoadApp(appBean);
            }catch (Exception e){
                //e.printStackTrace();
                continue;
            }
        }
        return "appBeans is OK";
    }


    @GetMapping("/devicetable/{id}")
    public String devicetable(@PathVariable Integer id, Model model){
        List <Map<String,String>> mapList = appMapper.queryDeviceByUserId(id);
        List<String> dvName = new ArrayList<>();
        List<String> dvMac =  new ArrayList<>();
        List<String> osName =  new ArrayList<>();


        for (Map<String, String> map : mapList) {
            dvName.add(map.get("dvName"));
            dvMac.add(map.get("dvMac"));
            osName.add(map.get("osName"));
        }
        model.addAttribute("dvName",dvName);
        model.addAttribute("dvMac",dvMac);
        model.addAttribute("osName",osName);
        model.addAttribute("userId",id);
       return "loadapp/devicetable";
    }

    @GetMapping("/apptable")
    public String apptable(@RequestParam(value = "dvName", required = false)String dvName,
                           @RequestParam(value = "dvMac", required = false)String dvMac,
                           @RequestParam(value = "userId", required = false)int userId,
                            Model model){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("dvName",dvName);
        map.put("dvMac",dvMac);
        map.put("userId",userId);
        List<AppBean> appBeans = appMapper.queryAppByDevice(map);
        model.addAttribute("appBeans",appBeans);
        model.addAttribute("appIsEnableUtil",appIsEnableUtil);
        return "loadapp/apptable";
    }

    @GetMapping("/changeEnable")
    public String changeEnable(@RequestParam(value = "dvName", required = false)String dvName,
                               @RequestParam(value = "dvMac", required = false)String dvMac,
                               @RequestParam(value = "userId", required = false)int userId,
                               @RequestParam(value= "pglist", required = false)List<String> pglist){
        //先把所有的isEnable置为0
        appIsEnableUtil.changeAll0();
        //遍历checkbox选择的应用，然后设置其为1
        for (String pgName : pglist) {
            appIsEnableUtil.change1(userId,dvMac,pgName);
        }

        return "redirect:/apptable?dvName="+dvName+"&dvMac="+dvMac+"&userId="+userId;
    }

    @GetMapping("/findAppEnable")
    @ResponseBody
    public String findAppEnable(@RequestParam(value = "dvName", required = false)String dvName,
                                @RequestParam(value = "dvMac", required = false)String dvMac,
                                @RequestParam(value = "userId", required = false)int userId){

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("dvName",dvName);
        map.put("dvMac",dvMac);
        map.put("userId",userId);
        List<String> pglist = appMapper.qureyAllEnable(map);
        StringBuffer applist = new StringBuffer();
        for (String s : pglist) {
            applist.append(s).append("=MDM=");
        }
        //System.out.println(applist.toString());
        return applist.toString();
    }

    @GetMapping("/test01")
    @ResponseBody
    public String test(){
        List <Map<String,String>> mapList = appMapper.queryDeviceByUserId(3);
        List<String> dvName = new ArrayList<>();
        List<String> dvMac =  new ArrayList<>();
        List<String> osName =  new ArrayList<>();

        for (Map<String, String> map : mapList) {
            dvName.add(map.get("dvName"));
            dvMac.add(map.get("dvMac"));
            osName.add(map.get("osName"));
        }
        return dvName.toString();
    }


}
