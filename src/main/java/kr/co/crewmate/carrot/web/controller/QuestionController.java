package kr.co.crewmate.carrot.web.controller;

import kr.co.crewmate.carrot.model.dto.QuestionListResponseDTO;
import kr.co.crewmate.carrot.model.entity.File;
import kr.co.crewmate.carrot.model.entity.QuestionAnswer;
import kr.co.crewmate.carrot.model.entity.QuestionKind;
import kr.co.crewmate.carrot.model.form.QuestionAnswerCreateForm;
import kr.co.crewmate.carrot.service.CategoryKindService;
import kr.co.crewmate.carrot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
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
    public String retrieveAdminQuestion (Model model){
        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
        List<QuestionListResponseDTO> questions = questionService.retrieveQuestionListAll();
        int questionCnt = questionService.retrieveQuestionListAllCount();
        model.addAttribute("questionKind", questionKind);
        model.addAttribute("questions", questions);
        model.addAttribute("questionCnt", questionCnt);
        return "question/adminQuestionPage";
    }

    @GetMapping("/admin/question/{seq}")
    public String retrieveAdminQuestion (@PathVariable("seq") String questionKindSeq, Model model){
        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
        List<QuestionListResponseDTO> questions = questionService.retrieveQuestionList(questionKindSeq);
        int questionCnt = questionService.retrieveQuestionListCount(questionKindSeq);
        model.addAttribute("questionKind", questionKind);
        model.addAttribute("questions", questions);
        model.addAttribute("questionCnt", questionCnt);
        return "question/adminQuestionPage";
    }

    @GetMapping("/admin/answer-question/{seq}")
    public String questionAnswerPage(@PathVariable("seq") String questionSeq, Model model) {
        QuestionListResponseDTO question = questionService.retrieveQuestionDetail(questionSeq);

        model.addAttribute("question", question);
        return "question/questionAnswerPage";
    }

    @GetMapping("/admin/answered-question/{seq}")
    public String questionAnsweredPage(@PathVariable("seq") String questionSeq, Model model) {
        QuestionListResponseDTO question = questionService.retrieveQuestionDetail(questionSeq);
        QuestionAnswer questionAnswer = questionService.retrieveQuestionAnswer(questionSeq);

        model.addAttribute("question", question);
        model.addAttribute("questionAnswer", questionAnswer);
        return "question/questionAnsweredPage";
    }
}
