package com.southgis.webgis.controller;

import com.southgis.webgis.entity.Question;
import com.southgis.webgis.service.AnswerService;
import com.southgis.webgis.Response.ResponseInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 智能问答接口
 *
 * @author QingYang
 */
@RestController
@RequestMapping("/Answer")
public class AnswerController {

    @Resource
    AnswerService answerService;

    @PostMapping("/sentence")
    public ResponseInfo AnswerSentence(@RequestBody Question model) {
        return answerService.answerSentence(model);
    }
}
