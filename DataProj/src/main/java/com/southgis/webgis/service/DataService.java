package com.southgis.webgis.service;


import com.southgis.webgis.Response.ResponseInfo;

import java.io.Serializable;

/**
 * 数据提取操作
 * @author QingYang
 */
public interface DataService extends Serializable {

    /**
     * 职位及薪资
     */
     ResponseInfo querySalary();
}