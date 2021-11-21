package com.southgis.webgis.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.Response.entity.EnumErrCode;
import com.southgis.webgis.entity.DataEntity;
import com.southgis.webgis.mapper.DataMapper;
import com.southgis.webgis.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service(DataImpl.SERVICE_BEAN_NAME)
public class DataImpl implements DataService {
    public final static String SERVICE_BEAN_NAME = "DataService";

    @Resource
    DataMapper dataMapper;

    public ResponseInfo querySalary() {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        try {
//            String field = "position";
//            List<String> text = getValue(field);
//            List<String> jieba = new ArrayList<>();
//            for (String sentence : text) {
//                List<SegToken> tokens = segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX);
//                System.out.println(tokens);
//            }

            int data[] = new int[6];
            for (int i = 0; i < Arrays.stream(data).count(); i++) {
                data[i] += i;
            }
            return new ResponseInfo(EnumErrCode.OK, data);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseInfo(EnumErrCode.BusinessError, ex.getMessage());
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


