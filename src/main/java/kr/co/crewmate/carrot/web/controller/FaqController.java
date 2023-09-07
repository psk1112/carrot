package kr.co.crewmate.carrot.web.controller;


import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import kr.co.crewmate.carrot.model.entity.FaqEntity;
import kr.co.crewmate.carrot.model.entity.FaqKindEntity;
import kr.co.crewmate.carrot.service.CategoryKindService;
import kr.co.crewmate.carrot.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FaqController {

    private final FaqService faqService;
    private final CategoryKindService categoryKindService;

    @GetMapping("/faq")
    public String retrieveFaq(Model model){

        List<FaqListResponseDTO> faqList = faqService.retrieveFaqList();
        int countList = faqService.retrieveFaqListCount();
        List<FaqKindEntity> faqKind = categoryKindService.retrieveFaqKindList();


        model.addAttribute("faqDTO", new FaqListResponseDTO());
        model.addAttribute("faqList", faqList);
        model.addAttribute("countList", countList);
        model.addAttribute("faqKind", faqKind);

        return "faq/faqForm";
    }

    @GetMapping("/write-faq")
    public String faqWritePage(Model model){

        List<FaqKindEntity> faqKind = categoryKindService.retrieveFaqKindList();
        FaqListResponseDTO faqDTO = new FaqListResponseDTO();

        model.addAttribute("faqDTO", faqDTO);
        model.addAttribute("faqKind", faqKind);
        return "faq/write";
    }


}