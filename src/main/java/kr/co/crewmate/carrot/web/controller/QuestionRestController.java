package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.form.QuestionCreateForm;
import kr.co.crewmate.carrot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class QuestionRestController {

    private final QuestionService questionService;

    @PostMapping("/question")
    public CommonResponse createQuestion(@Valid @RequestBody QuestionCreateForm questionCreateForm, BindingResult bindingResult) {
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
//            boolean save = questionService.createQuestion(questionCreateForm);
//            if (save) {
//                response.setStatusCode(200);
//            } else {
                response.setStatusCode(400);
//            }
        }
        return response;
    }
}
