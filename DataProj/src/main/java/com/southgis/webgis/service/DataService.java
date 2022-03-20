package com.southgis.webgis.service;


import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.entity.CodeEntity;
import com.southgis.webgis.entity.Interface;
import com.southgis.webgis.entity.PageEntity;
import com.southgis.webgis.entity.SearchEntity;

import java.io.Serializable;

/**
 * 数据提取操作
 *
 * @author QingYang
 */
public interface DataService extends Serializable {

    /**
     * 职位及薪资
     */
    ResponseInfo querySalary(CodeEntity code);

    /**
     * 展示表格内容
     */
    ResponseInfo queryForm(PageEntity page);

    /**
     * 在表格中查询关键字
     */
    ResponseInfo queryAny(SearchEntity model);

    /**
     * 薪资与工作经验关系
     */
    ResponseInfo salaryRe();

    /**
     * 工作经验数量统计
     */
    ResponseInfo experienceCo();

    /**
     * 地区城市间的薪资top10
     */
    ResponseInfo salaryAreaTop(CodeEntity code);

    /**
     * 行业薪资top
     */
    ResponseInfo sIndustryTop();

    /**
     * 统计城市中各等级薪资的数量
     */
    ResponseInfo salaryCo(CodeEntity code);

    /**
     * 以id查询改公司所有信息
     */
    ResponseInfo queryAll(CodeEntity code);

    /**
     * 薪资界面查询信息
     */
//    ResponseInfo querySInfo();

    /**
     * 处理数据
     */
    ResponseInfo disData(CodeEntity code);

    /**
     * 查询区县薪资
     */
    ResponseInfo querySa(Interface model);
}
