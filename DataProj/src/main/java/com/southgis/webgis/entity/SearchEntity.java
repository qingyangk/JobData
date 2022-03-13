package com.southgis.webgis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchEntity {

    @JsonProperty("search")
    public String model;

    public int pageNum;

    @JsonProperty("pageSize")
    public int count;
}
