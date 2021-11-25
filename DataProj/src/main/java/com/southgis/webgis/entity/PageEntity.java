package com.southgis.webgis.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PageEntity {

    /**
     * 页数
     */
    @JsonProperty("pageNum")
    public int page;
}
