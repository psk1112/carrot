package kr.co.crewmate.carrot.web.controller;

import kr.co.crewmate.carrot.model.entity.QuestionKind;
import kr.co.crewmate.carrot.service.CategoryKindService;
import kr.co.crewmate.carrot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final CategoryKindService categoryKindService;
    private final QuestionService questionService;

    @GetMapping("/user/question")
    public String questionPage (Model model){
        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
        model.addAttribute("questionKind", questionKind);
        return "question/questionPage";
    }

    @GetMapping("/user/myQuestion")
    public String myQuestionPage (Model model){
        return "question/myQuestionPage";
    }


    @GetMapping("/admin/question")
    public String adminQuestionPage (Model model){
        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
        questionService.retrieveQuestionListAll();
        questionService.retrieveQuestionListAllCount();
        model.addAttribute("questionKind", questionKind);
        return "question/adminQuestionPage";
    }

}
