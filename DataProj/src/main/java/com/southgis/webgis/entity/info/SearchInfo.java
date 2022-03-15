package com.southgis.webgis.entity.info;

import com.southgis.webgis.entity.table.JobEntity;
import lombok.Data;

import java.util.List;

@Data
public class SearchInfo {
    public long pages;
    public long total;
    public List<JobEntity> jobInfos;
}
