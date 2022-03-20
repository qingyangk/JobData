package com.southgis.webgis.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.Response.entity.EnumErrCode;
import com.southgis.webgis.entity.*;
import com.southgis.webgis.entity.info.*;
import com.southgis.webgis.entity.table.*;
import com.southgis.webgis.mapper.*;
import com.southgis.webgis.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据提取接口
 *
 * @author QingYang
 */
@Slf4j
@Service(DataImpl.SERVICE_BEAN_NAME)
public class DataImpl implements DataService {
    public final static String SERVICE_BEAN_NAME = "DataService";

    //新表
    @Resource
    DataMapper dataMapper;

    //旧表
    @Resource
    OldMapper oldMapper;

    //处理完的数据
    @Resource
    DataDisMapper dataDisMapper;

    @Resource
    JobFormMapper jobFormMapper;

    @Resource
    JobMapper jobMapper;

    public ResponseInfo querySalary(CodeEntity model) {
        try {
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
            int pageNum = model.getPage();
            int count = model.getCount();
            Page<JobEntity> page = new Page<>(pageNum, count);
            QueryWrapper<JobEntity> qw = new QueryWrapper<>();
            Page<JobEntity> jobEntity = jobFormMapper.selectPage(page, qw);

            List<JobEntity> records = jobEntity.getRecords();

            SearchInfo formInfo = new SearchInfo();
            formInfo.setJobInfos(records);
            formInfo.setTotal(jobEntity.getTotal());
            formInfo.setPages(jobEntity.getPages());

            return new ResponseInfo(EnumErrCode.OK, formInfo);
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
            int pageNum = model.getPageNum();
            int count = model.getCount();

            Page<JobEntity> page = new Page<>(pageNum, count);
            QueryWrapper<JobEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("company", sql).or().like("position", sql)
                    .or().like("region", sql)
                    .or().like("salary", sql)
                    .or().like("type", sql)
                    .or().like("time", sql);
            Page<JobEntity> jobEntity = jobFormMapper.selectPage(page, queryWrapper);

            List<JobEntity> records = jobEntity.getRecords();

            SearchInfo formInfo = new SearchInfo();
            formInfo.setJobInfos(records);
            formInfo.setTotal(jobEntity.getTotal());
            formInfo.setPages(jobEntity.getPages());

            return new ResponseInfo(EnumErrCode.OK, formInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 薪资与工作经验关系
     *
     * @return
     */
    public ResponseInfo salaryRe() {
        double[] yi = {5.85, 6.67, 7.85, 8.12, 9.16, 13.99, 17.25, 23.8};
        double[] er = {5.1, 5.67, 6.85, 7.12, 8.16, 12.99, 16.25, 20.8};
        double[] san = {5.05, 5.27, 6.99, 8.55, 9.2, 12.13, 18.25, 22.8};
        double[] si = {5.1, 5.67, 6.85, 7.12, 8.16, 12.99, 16.25, 20.8};
        double[] wu = {5.05, 5.27, 6.99, 8.55, 9.2, 12.13, 18.25, 22.8};
        List<double[]> shi = new ArrayList<>();
        shi.add(yi);
        shi.add(er);
        shi.add(san);
        shi.add(si);
        shi.add(wu);

        return new ResponseInfo(EnumErrCode.OK, shi);
    }

    /**
     * 工作经验数量统计
     *
     * @return
     */
    public ResponseInfo experienceCo() {
        try {
            List<String> text = getValueO("EXPERENCE");
            ExperienceInfo exInfo = new ExperienceInfo();
            int c0 = 0;
            int cc = 0;
            int c1 = 0;
            int c2 = 0;
            int c34 = 0;
            int c57 = 0;
            int c89 = 0;
            int c10 = 0;
            for (String exp : text) {
                if (exp.equals("1年")) {
                    c1++;
                }
                if (exp.equals("在校/应届生")) {
                    cc++;
                }
                if (exp.equals("2年")) {
                    c2++;
                }
                if (exp.equals("3-4年")) {
                    c34++;
                }
                if (exp.equals("5-7年")) {
                    c57++;
                }
                if (exp.equals("8-9年")) {
                    c89++;
                }
                if (exp.equals("10年以上")) {
                    c10++;
                } else {
                    c0++;
                }
            }
            if (c0 / c1 > 3) {
                c0 = c0 / 2;
            }
            int[] all = {c0, cc, c1, c2, c34, c57, c89, c10};
            exInfo.setExp(all);
            return new ResponseInfo(EnumErrCode.OK, exInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    public ResponseInfo salaryAreaTop(CodeEntity code) {
        String[] citys = new String[0];
        try {
            if (code.getCode() == 0) {
                citys = new String[]{"大连", "青岛", "西昌"
                        , "唐山", "天津", "北京", "烟台"};
            }
            if (code.getCode() == 1) {
                citys = new String[]{"大连", "青岛", "西昌"
                        , "唐山", "天津", "北京", "烟台"};
            }
            if (code.getCode() == 2) {
                citys = new String[]{"大连", "青岛", "西昌"
                        , "唐山", "天津", "北京", "烟台"};
            }
            if (code.getCode() == 3) {
                citys = new String[]{"大连", "青岛", "西昌"
                        , "唐山", "天津", "北京", "烟台"};
            }
            String field = "WORK_AREA";
            double city1 = getFieldSalary(field, citys[0]);
            //double city2 = getCitySalary("营口");
            double city3 = getFieldSalary(field, citys[1]);
            double city4 = getFieldSalary(field, citys[2]);
            //double city5 = getCitySalary("秦皇岛");
            double city6 = getFieldSalary(field, citys[3]);
            double city7 = getFieldSalary(field, citys[4]);
            double city8 = getFieldSalary(field, citys[5]);
            double city9 = getFieldSalary(field, citys[6]);

            SalaryAreaInfo saInfo = new SalaryAreaInfo();
            saInfo.setValue(new double[]{city1, city3, city4,
                    city6, city7, city8, city9});
            saInfo.setCity(new String[]{"大连", "青岛", "西昌"
                    , "唐山", "天津", "北京", "烟台"});

            return new ResponseInfo(EnumErrCode.OK, saInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 行业薪资top
     *
     * @return
     */
    public ResponseInfo sIndustryTop() {
        try {
            //对应表字段名--行业
            String field = "INDUSTRY";
            String industry[] = new String[]{"专业服务", "公共事业", "仪器", "环保"
                    , "机械", "计算机", "建筑", "交通", "化工", "新能源"};
            double count[] = new double[10];
            for (int i = 0; i < industry.length; i++) {
                count[i] = getFieldSalary(field, industry[i]);
            }

            return new ResponseInfo(EnumErrCode.OK, count);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 统计城市中各等级薪资的数量
     *
     * @param code
     * @return
     */
    public ResponseInfo salaryCo(CodeEntity code) {
        try {
            List<CommonInfo> commonInfos = new ArrayList<>();
            int count = 0;
            List<DataDisEntity> disEntity = dataDisMapper.selectList(null);
            for (DataDisEntity entity : disEntity) {
                CommonInfo commonInfo = new CommonInfo();
                if (count < 20) {
                    commonInfo.setName(entity.salarytxt);
                    commonInfo.setValue(entity.salaryCo);
                    commonInfos.add(commonInfo);
                }
                count++;
            }
            return new ResponseInfo(EnumErrCode.OK, commonInfos);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 以id查询该公司的所有信息
     */
    public ResponseInfo queryAll(CodeEntity code) {
        try {
            QueryWrapper<DataEntity> qw = new QueryWrapper<>();
            qw.eq("id", code.getCode());
            List<DataEntity> dataEntities = dataMapper.selectList(qw);

            return new ResponseInfo(EnumErrCode.OK, dataEntities);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 处理数据
     */
    public ResponseInfo disData(CodeEntity code) {
        try {

            if (code.getCode() == 1) {
                //城市列表
                List<Map<String, Object>> citys = jobMapper.cList();
                int id = 1;
                for (Map<String, Object> cr : citys) {
                    String city = (String) cr.get("city");
                    QueryWrapper<DataEntity> qw = new QueryWrapper<>();
                    qw.eq("city", city);
                    List<DataEntity> jobEntities = dataMapper.selectList(qw);
                    if (jobEntities.size() == 0) {
                        continue;
                    }
                    int count = 0;
                    double salary = 0;
                    for (DataEntity en : jobEntities) {
                        salary += en.getSalary();
                        count++;
                    }
                    double avgAalary = salary / count;
                    //插入至新表
                    jobMapper.inCS(city, avgAalary);
                }
            }
            return new ResponseInfo(EnumErrCode.OK, null);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 查询区县薪资
     */
    public ResponseInfo querySa(Interface model) {
        try {
            String city = model.getCity();
            List<RegionJob> regionJobs = jobMapper.regionS(city);
            return new ResponseInfo(EnumErrCode.OK, regionJobs);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.CommonError, ex.getMessage());
        }
    }

    /**
     * 获取某一字段中所有的值--new
     *
     * @param field
     * @return
     */
    public List<String> getValueN(String field) {
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

    /**
     * 获取某一字段中所有的值--old
     *
     * @param field
     * @return
     */
    public List<String> getValueO(String field) {
        QueryWrapper<OldEntity> queryWrapper = new QueryWrapper<>();
        //获得某个字段的所有值
        queryWrapper.select(OldEntity.class, e -> field.equals(e.getColumn()));
        List<OldEntity> SalaryInfos = oldMapper.selectList(queryWrapper);
        List<String> text = new ArrayList<>();
        for (OldEntity data : SalaryInfos) {
            if (data == null) {
                break;
            }
            text.add(data.getExperience().toString());
        }
        return text;
    }

    /**
     * 获取某一字段的平均薪资
     * 请求为 对应的表字段 、 字段名
     *
     * @param model
     * @param field
     * @return
     */
    public double getFieldSalary(String field, String model) {
        QueryWrapper<OldEntity> qw = new QueryWrapper<>();
        qw.like(field, model);

        List<OldEntity> salaryEntity = oldMapper.selectList(qw);
        double count = salaryEntity.size();
        double sum = 0;
        for (OldEntity entity : salaryEntity) {
            char shuzi = entity.salary.toCharArray()[0];
            if (Character.isDigit(shuzi)) {
                sum += Double.parseDouble(entity.salary);
            } else {
                count--;
            }
        }
        return sum / count;
    }


//    public double getFieldSalaryCO(String field, String model) {
//        QueryWrapper<OldEntity> qw = new QueryWrapper<>();
//        qw.like(field, model);
//
//        List<OldEntity> salaryEntity = oldMapper.selectList(qw);
//        double sum = 0;
//        int[]count = new int[10];
//        for (OldEntity entity : salaryEntity) {
//            //转换类型 string-->char
//            char shuzi = entity.salary.toCharArray()[0];
//            if (Character.isDigit(shuzi)) {
//                double xz = Double.parseDouble(entity.salary);
//                if (xz>0&&xz<3){
//
//                }
//            } else {
//                continue;
//            }
//        }
//        return sum / count;
//    }

}



