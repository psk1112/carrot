package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.FaqDTO;
import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.service.CategoryService;
import kr.co.crewmate.carrot.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
@Validated
public class FaqController {

    private final FaqService faqService;
    private final CategoryService categoryService;

    @GetMapping("/list{seq}")
    public String faqListForm(@RequestParam(name = "seq", required = false) String faqKindSeq, Model model){

        List<FaqDTO> faqList = faqService.faqList(faqKindSeq);
        int countList = faqService.countFaqList(faqKindSeq);
        List<FaqKindDTO> faqKind = categoryService.faqKindList();

        model.addAttribute("faqDTO", new FaqDTO());
        model.addAttribute("faqList", faqList);
        model.addAttribute("countList", countList);
        model.addAttribute("faqKind", faqKind);

        return "faq/faqForm";
    }


    @PostMapping("/saveData")
    public ResponseEntity<String> saveData (@Valid @ModelAttribute FaqDTO faqDTO, BindingResult bindingResult){

        System.out.println(faqDTO.getFaqKindSeq()+"::::"+faqDTO.getFaqTitle()+":::"+ faqDTO.getFaqContent());

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        faqService.processCreateFaq(faqDTO);
        return ResponseEntity.ok("성공적으로 저장되었습니다.");
    }
}
