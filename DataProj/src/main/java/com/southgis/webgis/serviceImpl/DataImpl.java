package com.southgis.webgis.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.Response.entity.EnumErrCode;
import com.southgis.webgis.entity.*;
import com.southgis.webgis.mapper.DataMapper;
import com.southgis.webgis.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据提取接口
 *
 * @author QingYang
 */
@Slf4j
@Service(DataImpl.SERVICE_BEAN_NAME)
public class DataImpl implements DataService {
    public final static String SERVICE_BEAN_NAME = "DataService";

    @Resource
    DataMapper dataMapper;

    public ResponseInfo querySalary(CodeEntity model) {

        try {
//            此处使用jieba分词，此部分在python中完成
//            JiebaSegmenter segmenter = new JiebaSegmenter();
//            String field = "position";
//            List<String> text = getValue(field);
//            List<String> jieba = new ArrayList<>();
//            for (String sentence : text) {
//                List<SegToken> tokens = segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX);
//                System.out.println(tokens);
//            }

            int[] value = new int[10];
            String[] position = new String[10];
            DataInfo data = new DataInfo();

            if (model.getCode() == 1) {
                value = new int[]{336, 293, 265, 177, 110, 86, 83, 81, 75, 68};
                position = new String[]{"销售", "工程师", "经理", "专员", "主管", "助理", "北京", "客服", "运营", "代表"};
            }
            if (model.getCode() == 2) {
                value = new int[]{36, 93, 65, 77, 10, 6, 3, 81, 5, 8};
                position = new String[]{"销", "工", "经", "专", "主", "助", "北", "客", "运", "代"};
            }
//            int[] value = {336, 293, 265, 177, 110, 86, 83, 81, 75, 68};
//            String[] position = {"销售", "工程师", "经理", "专员", "主管", "助理", "北京", "客服", "运营", "代表"};
            data.setValue(value);
            data.setPosition(position);
            return new ResponseInfo(EnumErrCode.OK, data);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.BusinessError, ex.getMessage());
        }
    }

    /**
     * 展示表格中的内容
     *
     * @param model
     * @return
     */
    public ResponseInfo queryForm(PageEntity model) {
        try {
            List<DataEntity> jobEntity = dataMapper.selectList(null);

            //符合条件要素个数
            int featureCount = jobEntity.size();
            //要素最小值
            int minCount = 0;
            //要素最大值
            int maxCount = featureCount;
            //计数
            int count = 0;
            int pageSize = 8;
            int PageNum = model.page;
            int pages;
            if (maxCount > 8) {
                if (maxCount % pageSize == 0) {
                    pages = maxCount / pageSize;
                } else {
                    pages = maxCount / pageSize + 1;
                }
            } else {
                pages = 1;
            }
            SearchInfo searchInfo = new SearchInfo();
            searchInfo.setTotal(maxCount);

            if (pageSize != 0 && PageNum != 0) {
                minCount = (PageNum - 1) * pageSize;  //要素最小值
                maxCount = PageNum * pageSize;  /* 要素最大值 */
            }

            List<JobInfo> jobInfos = new ArrayList<>();

            for (DataEntity entity : jobEntity) {
                count++;
                if (count > minCount && count <= maxCount) {
                    JobInfo jobInfo = new JobInfo();
                    jobInfo.company = entity.getCompany();
                    //jobInfo.experience = entity.getExperience();
                    jobInfo.position = entity.getPosition();
                    jobInfo.region = entity.getRegion();
                    jobInfo.salary = entity.getSalary();
                    //jobInfo.require = entity.getRequire();
                    jobInfo.time = entity.getTime();
                    jobInfo.type = entity.getType();
                    jobInfos.add(jobInfo);
                }
            }

            searchInfo.setJobInfos(jobInfos);
            searchInfo.setPages(pages);
            return new ResponseInfo(EnumErrCode.OK, searchInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }


    }

    /**
     * 关键字模糊查找--表格
     *
     * @param model
     * @return
     */
    public ResponseInfo queryAny(SearchEntity model) {
        String sql = model.getModel();
        try {
            QueryWrapper<DataEntity> queryWrapper = new QueryWrapper<>();
            //JobInfo jobInfo = new JobInfo();
            queryWrapper.like("company", sql).or().like("position", sql)
                    .or().like("region", sql)
                    .or().like("salary", sql)
                    .or().like("type", sql)
                    .or().like("time", sql);
            List<DataEntity> jobEntity = dataMapper.selectList(queryWrapper);

            //符合条件要素个数
            int featureCount = jobEntity.size();
            //要素最小值
            int minCount = 0;
            //要素最大值
            int maxCount = featureCount;
            //计数
            int count = 0;
            int pageSize = 8;
            int PageNum = model.pageNum;
            int pages;
            if (maxCount > 8) {
                if (maxCount % pageSize == 0) {
                    pages = maxCount / pageSize;
                } else {
                    pages = maxCount / pageSize + 1;
                }
            } else {
                pages = 1;
            }
            SearchInfo searchInfo = new SearchInfo();
            searchInfo.setTotal(maxCount);

            if (pageSize != 0 && PageNum != 0) {
                minCount = (PageNum - 1) * pageSize;  //要素最小值
                maxCount = PageNum * pageSize;  /* 要素最大值 */
            }


//            Page<DataEntity> objectPage = new Page<>(model.getPageNum(), 8);
//            Page<DataEntity> entityPage = dataMapper.selectPage(objectPage, queryWrapper);z
//            Page<JobInfo> jobInfoPage = new Page<JobInfo>();

            List<JobInfo> jobInfos = new ArrayList<>();
            for (DataEntity entity : jobEntity) {
                count++;
                if (count > minCount && count <= maxCount) {
                    JobInfo jobInfo = new JobInfo();
                    jobInfo.setCompany(entity.getCompany());
                    //jobInfo.experience = entity.getExperience();
                    jobInfo.setPosition(entity.getPosition());
                    jobInfo.setRegion(entity.getRegion());
                    jobInfo.setSalary(entity.getSalary());
                    //jobInfo.require = entity.getRequire();
                    jobInfo.setTime(entity.getTime());
                    jobInfo.setType(entity.getType());
                    jobInfos.add(jobInfo);
                }
                if (count > PageNum * pageSize)
                    break;
            }
            searchInfo.setJobInfos(jobInfos);
//            searchInfo.setTotal(maxCount); 此处需要提前赋值
            searchInfo.setPages(pages);
//            jobInfoPage.setRecords(jobInfos);
            return new ResponseInfo(EnumErrCode.OK, searchInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 获取某一字段中所有的值
     *
     * @param field
     * @return
     */
    public List<String> getValue(String field) {
        QueryWrapper<DataEntity> queryWrapper = new QueryWrapper<>();
        //获得某个字段的所有值
        queryWrapper.select(DataEntity.class, e -> field.equals(e.getColumn()));
        List<DataEntity> SalaryInfos = dataMapper.selectList(queryWrapper);
        List<String> text = new ArrayList<>();
        for (DataEntity data : SalaryInfos) {
            if (data == null) {
                break;
            }
            text.add(data.getPosition().toString());
        }
        return text;
    }
}


