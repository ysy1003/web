package com.ysy.pagedemo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ysy.pagedemo.mapper.RunInfoMapper;
import com.ysy.pagedemo.pojo.RunAppInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class RunInfoController {

    private static int count;
    @Autowired
    RunInfoMapper runInfoMapper;

    @PostMapping("/runInfo")
    @ResponseBody
    public String getRunInfo(@RequestBody RunAppInfo runAppInfo){
        if(runAppInfo == null){
            System.out.println("OK");
            return "OK";
        }
        runInfoMapper.addRunInfo(runAppInfo);
        //System.out.println(runAppInfo);
        return runAppInfo.toString();
    }

    @GetMapping("/runInfo")
    @ResponseBody
    public String showRunInfo(@RequestParam(value = "pkgName", required = false) String pkgName,
                              @RequestParam(value = "appName", required = false) String appName){
        return "pkgName:"+pkgName+","+"appName:"+appName;

    }

    @GetMapping("/runInfo/infolist")
    public String showRunInfoList(Model model){
        List<RunAppInfo> runAppInfos = runInfoMapper.queryRunInfo();
        count= runInfoMapper.queryMaxid();
        //System.out.println("count1===="+count);
        model.addAttribute("runInfos",runAppInfos);
        return "/runinfo/runinfolist";
    }

    @GetMapping("/runInfo/findnewinfo")
    @ResponseBody
    public String findNewRunInfo(){
        List<RunAppInfo> runAppInfos = runInfoMapper.queryNewInfo(count);
        count = runInfoMapper.queryMaxid();
        if (runAppInfos.isEmpty()){
            Gson gson = new GsonBuilder().setDateFormat("yyyy年MM月dd日 HH:mm:ss").create();
            int flag = 0;
            return gson.toJson(flag);
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy年MM月dd日 HH:mm:ss").create();
        return gson.toJson(runAppInfos);
    }

}
