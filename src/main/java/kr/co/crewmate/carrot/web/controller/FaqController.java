package kr.co.crewmate.carrot.web.controller;


import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import kr.co.crewmate.carrot.model.entity.Faq;
import kr.co.crewmate.carrot.model.entity.FaqKind;
import kr.co.crewmate.carrot.service.CategoryKindService;
import kr.co.crewmate.carrot.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FaqController {

    private final FaqService faqService;
    private final CategoryKindService categoryKindService;

    @GetMapping("/faq")
    public String retrieveFaq(@RequestParam (name = "seq", required = false) String faqKindSeq, Model model){

        List<FaqListResponseDTO> faqList = faqService.retrieveFaqList(faqKindSeq);
        int countList = faqService.retrieveFaqListCount();
        List<FaqKind> faqKind = categoryKindService.retrieveFaqKindList();


        model.addAttribute("faqDTO", new FaqListResponseDTO());
        model.addAttribute("faqList", faqList);
        model.addAttribute("countList", countList);
        model.addAttribute("faqKind", faqKind);

        return "faq/faqPage";
    }

    @GetMapping("/write-faq")
    public String faqWritePage(Model model){

        List<FaqKind> faqKind = categoryKindService.retrieveFaqKindList();
        FaqListResponseDTO faqDTO = new FaqListResponseDTO();

        model.addAttribute("faqDTO", faqDTO);
        model.addAttribute("faqKind", faqKind);
        return "faq/faqWritePage";
    }


    @PostMapping("/modify-faq")
    public String faqModifyPage(@RequestBody Faq faq, Model model){
        List<FaqKind> faqKind = categoryKindService.retrieveFaqKindList();
        Faq faqDetail = faqService.retrieveDetailFaq(faq);

        model.addAttribute("faqKind", faqKind);
        model.addAttribute("faqDetail", faqDetail);
        return "faq/faqModifyPage";
    }
}