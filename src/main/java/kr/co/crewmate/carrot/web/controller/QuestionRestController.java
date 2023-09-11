package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.form.QuestionCreateForm;
import kr.co.crewmate.carrot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class QuestionRestController {

    private final QuestionService questionService;

    @PostMapping("/question")
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
}
