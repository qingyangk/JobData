package com.southgis.webgis.service;

import com.southgis.webgis.Response.ResponseInfo;
import com.southgis.webgis.entity.Question;

import java.io.Serializable;

/**
 * 智能问答接口
 *
 * @author QingYang
 */
public interface AnswerService extends Serializable {

    /**
     * 智能问答--传入问句
     *
     * @param model
     * @return
     */
    ResponseInfo answerSentence(Question model);

    /**
     * 词云
     *
     * @return
     */
    ResponseInfo wordCloud();
}
