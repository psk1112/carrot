package kr.co.crewmate.carrot.web.controller;


import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.FaqDTO;
import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.service.CategoryService;
import kr.co.crewmate.carrot.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;
    private final CategoryService categoryService;

    @GetMapping("/list{seq}")
    public String faqListForm(@RequestParam(name = "seq", required = false) String faqKindSeq, Model model){

        List<FaqDTO> faqList = faqService.faqList(faqKindSeq);
        int countList = faqService.countFaqList(faqKindSeq);
        List<FaqKindDTO> faqKind = categoryService.faqKindList();

        model.addAttribute("faqList", faqList);
        model.addAttribute("countList", countList);
        model.addAttribute("faqKind", faqKind);

        return "faq/faqForm";
    }

    @GetMapping("/write")
    public String faqWriteForm(Model model){

        List<FaqKindDTO> faqKind = categoryService.faqKindList();
        FaqDTO faqDTO = new FaqDTO();

        model.addAttribute("faqDTO", faqDTO);
        model.addAttribute("faqKind", faqKind);
        return "faq/write";
    }

    @PostMapping("/saveData")
    public String saveData(@Valid FaqDTO faqDTO, BindingResult bindingResult, Model model) {

        System.out.println(faqDTO.getFaqKindSeq()+"::::::::"+faqDTO.getFaqTitle()+"::::::::"+faqDTO.getFaqContent());

        if (bindingResult.hasErrors()) {
            List<FaqKindDTO> faqKind = categoryService.faqKindList();
            model.addAttribute("faqKind", faqKind);
            return "faq/write";
        }
            faqService.processCreateFaq(faqDTO);
            return "redirect:/faq/list";

    }

    @PostMapping("/deleteData")
    @ResponseBody
    public CommonResponse deleteData(@RequestBody String faqSeq){

        boolean deleted = faqService.deleteFaq(faqSeq);

        CommonResponse response = new CommonResponse();

        if (deleted) {
            response.setStatusCode(200);
        } else {
            response.setStatusCode(403);
        }
        return response;
    }

    @PostMapping("/modify")
    public String modifyForm(@RequestBody @ModelAttribute String faqSeq){
        return "faq/modifyForm";
    }

    @PostMapping("/modifyData")
    @ResponseBody
    public CommonResponse modifyData(@RequestBody String faqSeq){

        boolean modify = faqService.modifyFaq(faqSeq);
        CommonResponse response = new CommonResponse();

        if (modify) {
            response.setStatusCode(200);
        } else {
            response.setStatusCode(403);
        }
        return response;
    }
}
