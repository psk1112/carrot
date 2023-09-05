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
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 카테고리 양식
     *
     * @param model
     * @return category/categoryForm
     */
    @GetMapping("/create")
    public String createCategory(Model model) {

        List<UserClaimKindDTO> cateListU = categoryService.userKindList();
        List<PostClaimKindDTO> cateListP = categoryService.postKindList();
        List<FaqKindDTO> cateListF = categoryService.faqKindList();
        List<QuestionKindDTO> cateListQ = categoryService.questionKindList();

        model.addAttribute("cateListU", cateListU);
        model.addAttribute("cateListP", cateListP);
        model.addAttribute("cateListF", cateListF);
        model.addAttribute("cateListQ", cateListQ);

        return "category/categoryForm";
    }

    /**
     * 카테고리를 등록,수정하는 과정
     *
     * @param requestData
     * @return redirect:/category/create
     */
    @PostMapping("/saveData")
    public ResponseEntity<Map<String, String>> saveData(@RequestBody String requestData) {
        boolean save = categoryService.processCategoryies(requestData);
        //데이터 처리 개수 > 0 인지 확인
        if (save) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "처리 완료");

            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "처리 실패");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * 카테고리를 삭제하는 과정
     *
     * @param inputSeq
     * @return redirect:/category/create
     */
    @PostMapping("/deleteData")
    public ResponseEntity<Map<String, String>> deleteData(@RequestBody String inputSeq) {
        boolean deleted = categoryService.deleteData(inputSeq);
        //데이터 처리 개수 > 0 인지 확인
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("status", "success");
            response.put("message", "삭제 완료");

            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "삭제 실패");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}