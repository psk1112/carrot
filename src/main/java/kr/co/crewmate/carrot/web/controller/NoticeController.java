package kr.co.crewmate.carrot.web.controller;

import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import kr.co.crewmate.carrot.model.dto.NoticeListResponseDTO;
import kr.co.crewmate.carrot.model.dto.QuestionListResponseDTO;
import kr.co.crewmate.carrot.model.entity.FaqKind;
import kr.co.crewmate.carrot.model.entity.QuestionAnswer;
import kr.co.crewmate.carrot.model.entity.QuestionKind;
import kr.co.crewmate.carrot.service.CategoryKindService;
import kr.co.crewmate.carrot.service.NoticeService;
import kr.co.crewmate.carrot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

//    @GetMapping("/user/question")
//    public String questionPage (Model model){
//        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
//        model.addAttribute("questionKind", questionKind);
//        return "question/questionPage";
//    }
//
//    @GetMapping("/user/my-question")
//    public String myQuestionPage (Model model){
//        int userSeq = 0; //임의로 값 넣은거야
//        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
//        List<QuestionListResponseDTO> questions = questionService.retrieveMyQuestionListAll(userSeq);
//        int questionCnt = questionService.retrieveMyQuestionListAllCount(userSeq);
//        model.addAttribute("questionKind", questionKind);
//        model.addAttribute("questions", questions);
//        model.addAttribute("questionCnt", questionCnt);
//        return "question/myQuestionPage";
//    }
//
//    @GetMapping("/user/my-question/{seq}")
//    public String questionPage (@PathVariable("seq") String questionKindSeq, Model model){
//        int userSeq = 0; //임의로 값 넣은거야
//        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
//        List<QuestionListResponseDTO> questions = questionService.retrieveMyQuestionList(userSeq, questionKindSeq);
//        int questionCnt = questionService.retrieveMyQuestionListCount(userSeq, questionKindSeq);
//        model.addAttribute("questionKind", questionKind);
//        model.addAttribute("questions", questions);
//        model.addAttribute("questionCnt", questionCnt);
//        return "question/myQuestionPage";
//    }
//
//    @GetMapping("/user/modify-question/{seq}")
//    public String questionModifyPage (@PathVariable("seq") String questionSeq, Model model){
//        int userSeq = 0; //임의로 값 넣은거야
//        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
//        QuestionListResponseDTO question = questionService.retrieveQuestionDetail(questionSeq);
//        model.addAttribute("questionKind", questionKind);
//        model.addAttribute("question", question);
//        return "question/questionModifyPage";
//    }

    @GetMapping("/admin/notice")
    public String adminNoticePage (NoticeListResponseDTO noticeListResponseDTO, Model model){
        List<NoticeListResponseDTO> notices = noticeService.retrieveNoticeListAll(noticeListResponseDTO);
        int noticeCnt = noticeService.retrieveNoticeListAllCount(noticeListResponseDTO);
        model.addAttribute("notices", notices);
        model.addAttribute("noticeCnt", noticeCnt);
        return "notice/noticePage";
    }

    @GetMapping("admin/write-notice")
    public String faqWritePage(Model model){

        return "notice/noticeWritePage";
    }

//    @GetMapping("/admin/notice/{seq}")
//    public String adminNoticePage (@PathVariable("seq") String questionKindSeq, Model model){
//        List<QuestionKind> questionKind = categoryKindService.retrieveQuestionKindList();
//        List<QuestionListResponseDTO> questions = questionService.retrieveQuestionList(questionKindSeq);
//        int questionCnt = questionService.retrieveQuestionListCount(questionKindSeq);
//        model.addAttribute("questionKind", questionKind);
//        model.addAttribute("questions", questions);
//        model.addAttribute("questionCnt", questionCnt);
//        return "question/adminQuestionPage";
//    }
//
//    @GetMapping("/admin/answer-question/{seq}")
//    public String questionAnswerPage(@PathVariable("seq") String questionSeq, Model model) {
//        QuestionListResponseDTO question = questionService.retrieveQuestionDetail(questionSeq);
//
//        model.addAttribute("question", question);
//        return "question/adminQuestionAnswerPage";
//    }
//
//    @GetMapping("/admin/answered-question/{seq}")
//    public String questionAnsweredPage(@PathVariable("seq") String questionSeq, Model model) {
//        QuestionListResponseDTO question = questionService.retrieveQuestionDetail(questionSeq);
//        QuestionAnswer questionAnswer = questionService.retrieveQuestionAnswer(questionSeq);
//
//        model.addAttribute("question", question);
//        model.addAttribute("questionAnswer", questionAnswer);
//        return "question/adminQuestionAnsweredPage";
//    }
//
//    @GetMapping("/user/answered-question/{seq}")
//    public String userQuestionAnsweredPage(@PathVariable("seq") String questionSeq, Model model) {
//        QuestionListResponseDTO question = questionService.retrieveQuestionDetail(questionSeq);
//        QuestionAnswer questionAnswer = questionService.retrieveQuestionAnswer(questionSeq);
//
//        model.addAttribute("question", question);
//        model.addAttribute("questionAnswer", questionAnswer);
//        return "question/questionAnsweredPage";
//    }
}
