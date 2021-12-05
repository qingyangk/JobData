package com.southgis.webgis.entity.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataInfo {

    /**
     * 坐标
     */
    //public String x;

    /**
     * 坐标
     */
    //public String y;

    /**
     * 发布日期
     */
    //public Time time;
    /**
     * 薪水
     */
    @JsonProperty("value")
    public int[] value;

    /**
     * 职位
     */
    @JsonProperty("name")
    public String[] position;

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }
}
