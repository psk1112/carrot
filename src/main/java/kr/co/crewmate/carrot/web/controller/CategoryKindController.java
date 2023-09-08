package kr.co.crewmate.carrot.web.controller;

import kr.co.crewmate.carrot.model.entity.*;
import kr.co.crewmate.carrot.service.CategoryKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CategoryKindController {
    private final CategoryKindService categoryKindService;

    /**
     * 카테고리 조회(양식)
     * @return category/categoryForm
     */
    @GetMapping("/category")
    public String retrieveCategory(Model model) {

        List<UserClaimKind> cateListU = categoryKindService.retrieveUserKindList();
        List<PostClaimKind> cateListP = categoryKindService.retrievePostKindList();
        List<ReplyClaimKind> cateListR = categoryKindService.retrieveReplyKindList();
        List<FaqKind> cateListF = categoryKindService.retrieveFaqKindList();
        List<QuestionKind> cateListQ = categoryKindService.retrieveQuestionKindList();

        model.addAttribute("cateListU", cateListU);
        model.addAttribute("cateListP", cateListP);
        model.addAttribute("cateListR", cateListR);
        model.addAttribute("cateListF", cateListF);
        model.addAttribute("cateListQ", cateListQ);

        return "category/categoryPage";
    }

}