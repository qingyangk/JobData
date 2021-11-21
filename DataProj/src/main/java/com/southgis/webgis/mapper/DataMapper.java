package com.southgis.webgis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southgis.webgis.entity.DataEntity;
import com.southgis.webgis.entity.DataInfo;

import java.util.List;

public interface DataMapper extends BaseMapper<DataEntity> {

    //List<DataEntity> selectList(@Param("ew") Wrapper<DataEntity> wrapper);
    List<DataInfo> querySalary();
}
