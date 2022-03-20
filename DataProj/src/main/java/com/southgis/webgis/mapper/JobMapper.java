package com.southgis.webgis.mapper;

import com.southgis.webgis.entity.table.DataEntity;
import com.southgis.webgis.entity.table.RegionJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface JobMapper {
    //获取城市列表
    List<Map<String, Object>> cList();

    //以城市去筛选平均薪资
    List<DataEntity> avgSalary(String city);

    void inCS(String city, double avgsalary);

    //以城市去筛选区县平均薪资
    List<RegionJob>regionS(String city);
}
