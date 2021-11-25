package com.southgis.webgis.entity;

import lombok.Data;

import java.util.List;

@Data
public class SearchInfo {
    public int pages;
    public int total;
    public List<JobInfo> jobInfos;
}
