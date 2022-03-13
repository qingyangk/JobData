package com.southgis.webgis.service;


import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.entity.CodeEntity;
import com.southgis.webgis.entity.PageEntity;
import com.southgis.webgis.entity.SearchEntity;

import java.io.Serializable;

/**
 * 数据提取操作
 * @author QingYang
 */
public interface DataService extends Serializable {

    /**
     * 职位及薪资
     */
     ResponseInfo querySalary(CodeEntity code);

    /**
     * 展示表格内容
     * @param page
     * @return
     */
     ResponseInfo queryForm(PageEntity page);

    /**
     * 在表格中查询关键字
     * @param model
     * @return
     */
     ResponseInfo queryAny(SearchEntity model);

    /**
     *薪资与工作经验关系
     * @return
     */
     ResponseInfo salaryRe();

    /**
     * 工作经验数量统计
     * @return
     */
     ResponseInfo experienceCo();

    /**
     * 地区城市间的薪资top10
     * @return
     */
    ResponseInfo salaryAreaTop(CodeEntity code);

    /**
     * 行业薪资top
     * @return
     */
    ResponseInfo sIndustryTop();

    /**
     * 统计城市中各等级薪资的数量
     */
    ResponseInfo salaryCo(CodeEntity code);

    ResponseInfo queryAll(CodeEntity coode);
}
