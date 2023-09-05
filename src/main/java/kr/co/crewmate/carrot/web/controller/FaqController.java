/*
package kr.co.crewmate.carrot.web.controller;


import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.FaqDTO;
import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.service.CategoryService;
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
@RequestMapping("/faq")
public class FaqController {

    private final FaqService faqService;
    private final CategoryService categoryService;

    @GetMapping("/list{seq}")
    public String faqListForm(@RequestParam(name = "seq", required = false) String faqKindSeq, Model model){

        List<FaqDTO> faqList = faqService.faqList(faqKindSeq);
        int countList = faqService.countFaqList(faqKindSeq);
        List<FaqKindDTO> faqKind = categoryService.retrieveFaqKind();

<<<<<<< HEAD
        model.addAttribute("faqDTO", new FaqDTO());
=======
>>>>>>> 7dfe5840f6f2250de6912e1027347a1ea7ad350c
        model.addAttribute("faqList", faqList);
        model.addAttribute("countList", countList);
        model.addAttribute("faqKind", faqKind);

        return "faq/faqForm";
    }

    @GetMapping("/write")
    public String faqWriteForm(Model model){

        List<FaqKindDTO> faqKind = categoryService.retrieveFaqKind();
        FaqDTO faqDTO = new FaqDTO();

        model.addAttribute("faqDTO", faqDTO);
        model.addAttribute("faqKind", faqKind);
        return "faq/write";
    }

    @PostMapping("/saveData")
<<<<<<< HEAD
    public ResponseEntity<String> saveData (@Valid @ModelAttribute FaqDTO faqDTO, BindingResult bindingResult){

        System.out.println(faqDTO.getFaqKindSeq()+"::::"+faqDTO.getFaqTitle()+":::"+ faqDTO.getFaqContent());

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
=======
    public String saveData(@Valid FaqDTO faqDTO, BindingResult bindingResult, Model model) {

        System.out.println(faqDTO.getFaqKindSeq()+"::::::::"+faqDTO.getFaqTitle()+"::::::::"+faqDTO.getFaqContent());

        if (bindingResult.hasErrors()) {
            List<FaqKindDTO> faqKind = categoryService.retrieveFaqKind();
            model.addAttribute("faqKind", faqKind);
            return "faq/write";
>>>>>>> 7dfe5840f6f2250de6912e1027347a1ea7ad350c
        }
            faqService.processCreateFaq(faqDTO);
            return "redirect:/faq/list";

<<<<<<< HEAD
        faqService.processCreateFaq(faqDTO);
        return ResponseEntity.ok("성공적으로 저장되었습니다.");
=======
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
>>>>>>> 7dfe5840f6f2250de6912e1027347a1ea7ad350c
    }
}
*/
