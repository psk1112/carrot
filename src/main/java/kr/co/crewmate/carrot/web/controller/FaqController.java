package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.FaqDTO;
import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.service.CategoryService;
import kr.co.crewmate.carrot.service.FaqService;
import lombok.RequiredArgsConstructor;
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
        FaqDTO faqDTO = new FaqDTO();

        model.addAttribute("faqDTO", faqDTO);
        model.addAttribute("faqList", faqList);
        model.addAttribute("countList", countList);
        model.addAttribute("faqKind", faqKind);

        return "faq/faqForm";
    }


    @PostMapping("/saveData")
    public String saveData (@Validated FaqDTO faqDTO, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.hasErrors());
            return "faq/faqForm";
        }

        faqService.processCreateFaq(faqDTO);
        return "redirect:/faq/list";
    }
}
