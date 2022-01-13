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

    ResponseInfo answerSentence(Question model);
}
