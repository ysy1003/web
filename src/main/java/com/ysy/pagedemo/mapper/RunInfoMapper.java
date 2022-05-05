package com.ysy.pagedemo.mapper;

import com.ysy.pagedemo.pojo.RunAppInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RunInfoMapper {
    List<RunAppInfo> queryRunInfo();
    int addRunInfo(RunAppInfo runAppInfo);
    int queryMaxid();
    List<RunAppInfo> queryNewInfo(int id);
}
