package kr.co.crewmate.carrot.web.controller;

import kr.co.crewmate.carrot.model.entity.FaqKindEntity;
import kr.co.crewmate.carrot.model.entity.PostClaimKindEntity;
import kr.co.crewmate.carrot.model.entity.QuestionKindEntity;
import kr.co.crewmate.carrot.model.entity.UserClaimKindEntity;
import kr.co.crewmate.carrot.service.CategoryService;
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
    private final CategoryService categoryService;

    /**
     * 카테고리 조회(양식)
     * @return category/categoryForm
     */
    @GetMapping("/category")
    public String retrieveCategory(Model model) {

        List<UserClaimKindEntity> cateListU = categoryService.retrieveUserKindList();
        List<PostClaimKindEntity> cateListP = categoryService.retrievePostKindList();
        List<FaqKindEntity> cateListF = categoryService.retrieveFaqKindList();
        List<QuestionKindEntity> cateListQ = categoryService.retrieveQuestionKindList();

        model.addAttribute("cateListU", cateListU);
        model.addAttribute("cateListP", cateListP);
        model.addAttribute("cateListF", cateListF);
        model.addAttribute("cateListQ", cateListQ);

        return "category/categoryForm";
    }

}