package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.form.*;
import kr.co.crewmate.carrot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QuestionRestController {

    private final QuestionService questionService;

    @PostMapping("/user/question")
    public CommonResponse createQuestion(@Valid QuestionCreateForm questionCreateForm , BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();
        Map<String, String> error = new HashMap<>();

        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError("questionTitle") != null) {
                error.put("questionTitle", bindingResult.getFieldError("questionTitle").getDefaultMessage());
            }
            if (bindingResult.getFieldError("questionContent") != null) {
                error.put("questionContent", bindingResult.getFieldError("questionContent").getDefaultMessage());
            }
            response.setStatusCode(401);
            response.setBody(error);

        } else {
          try {
              questionService.createQuestion(questionCreateForm);
              response.setStatusCode(200);
          }catch (Exception e){
              response.setStatusCode(500);
          }
        }
        return response;
    }


    @PutMapping("/user/question")
    public CommonResponse modifyQuestion(@Valid QuestionModifyForm questionModifyForm , BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();
        Map<String, String> error = new HashMap<>();
        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError("questionTitle") != null) {
                error.put("questionTitle", bindingResult.getFieldError("questionTitle").getDefaultMessage());
            }
            if (bindingResult.getFieldError("questionContent") != null) {
                error.put("questionContent", bindingResult.getFieldError("questionContent").getDefaultMessage());
            }
            response.setStatusCode(401);
            response.setBody(error);

        } else {
            try {
                questionService.modifyQuestion(questionModifyForm);
                response.setStatusCode(200);
            }catch (Exception e){
                response.setStatusCode(500);
            }
        }
        return response;
    }


    @DeleteMapping("/user/question")
    public CommonResponse deleteQuestion(@Valid @RequestBody QuestionDeleteForm questionDeleteForm, BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();

        try {
            questionService.deleteQuestion(questionDeleteForm);
            response.setStatusCode(200);
        }catch (Exception e){
            response.setStatusCode(500);
        }
        return response;
    }


    //관리자 답변
    @PostMapping("/admin/question")
    public CommonResponse createQuestionAnswer(@Valid @RequestBody QuestionAnswerCreateForm questionAnswerCreateForm , BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();
        Map<String, String> error = new HashMap<>();

        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError("questionAnswerContent") != null) {
                error.put("questionAnswerContent", bindingResult.getFieldError("questionAnswerContent").getDefaultMessage());
            }
            response.setStatusCode(401);
            response.setBody(error);

        } else {
            try {
                questionService.createQuestionAnswer(questionAnswerCreateForm);
                response.setStatusCode(200);
            }catch (Exception e){
                response.setStatusCode(500);
            }
        }
        return response;
    }


    @PutMapping("/admin/question")
    public CommonResponse modifyQuestionAnswer(@Valid @RequestBody QuestionAnswerModifyForm questionAnswerModifyForm , BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();
        Map<String, String> error = new HashMap<>();

        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError("questionAnswerContent") != null) {
                error.put("questionAnswerContent", bindingResult.getFieldError("questionAnswerContent").getDefaultMessage());
            }
            response.setStatusCode(401);
            response.setBody(error);

        } else {
            try {
                questionService.modifyQuestionAnswer(questionAnswerModifyForm);
                response.setStatusCode(200);
            }catch (Exception e){
                response.setStatusCode(500);
            }
        }
        return response;
    }

    @DeleteMapping("/admin/question")
    public CommonResponse deleteQuestionAnswer(@Valid @RequestBody QuestionAnswerDeleteForm questionAnswerDeleteForm, BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();

        try {
            questionService.deleteQuestionAnswer(questionAnswerDeleteForm);
            response.setStatusCode(200);
        }catch (Exception e){
            response.setStatusCode(500);
        }
        return response;
    }
}
