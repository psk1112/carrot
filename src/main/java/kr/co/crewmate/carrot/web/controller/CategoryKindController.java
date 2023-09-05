package kr.co.crewmate.carrot.web.controller;

import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.model.PostClaimKindDTO;
import kr.co.crewmate.carrot.model.QuestionKindDTO;
import kr.co.crewmate.carrot.model.UserClaimKindDTO;
import kr.co.crewmate.carrot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<UserClaimKindDTO> cateListU = categoryService.retrieveUserKindList();
        List<PostClaimKindDTO> cateListP = categoryService.retrievePostKindList();
        List<FaqKindDTO> cateListF = categoryService.retrieveFaqKindList();
        List<QuestionKindDTO> cateListQ = categoryService.retrieveQuestionKindList();

        model.addAttribute("cateListU", cateListU);
        model.addAttribute("cateListP", cateListP);
        model.addAttribute("cateListF", cateListF);
        model.addAttribute("cateListQ", cateListQ);

        return "category/categoryForm";
    }

}