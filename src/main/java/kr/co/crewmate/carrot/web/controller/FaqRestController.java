package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.form.FaqDeleteForm;
import kr.co.crewmate.carrot.service.CategoryKindService;
import kr.co.crewmate.carrot.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FaqRestController {
    private final FaqService faqService;
    private final CategoryKindService categoryKindService;

//    /**
//     * 자주 묻는 질문 등록
//     * @param faqDTO
//     * @param bindingResult
//     * @return
//     */
//    @PostMapping("/faq")
//    public ResponseEntity<String> saveData (@Valid @ModelAttribute FaqDTO faqDTO, BindingResult bindingResult){
//
//        System.out.println(faqDTO.getFaqKindSeq()+"::::"+faqDTO.getFaqTitle()+":::"+ faqDTO.getFaqContent());
//
//        if(bindingResult.hasErrors()){
//            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
//            public String saveData(@Valid FaqDTO faqDTO, BindingResult bindingResult, Model model) {
//
//                System.out.println(faqDTO.getFaqKindSeq()+"::::::::"+faqDTO.getFaqTitle()+"::::::::"+faqDTO.getFaqContent());
//
//                if (bindingResult.hasErrors()) {
//                    List<FaqKindDTO> faqKind = categoryService.retrieveFaqKind();
//                    model.addAttribute("faqKind", faqKind);
//                    return "faq/write";
//                }
//                faqService.processCreateFaq(faqDTO);
//                return "redirect:/faq/list";
//
//                faqService.processCreateFaq(faqDTO);
//                return ResponseEntity.ok("성공적으로 저장되었습니다.");
//            }
//
            @DeleteMapping("/faq")
            public CommonResponse deleteFaq(@RequestBody FaqDeleteForm faqDeleteForm){
                CommonResponse response = new CommonResponse();
                boolean deleted = faqService.deleteFaq(faqDeleteForm);
                if (deleted) {
                    response.setStatusCode(200);
                } else {
                    response.setStatusCode(400);
                }
                return response;
            }

//            @PostMapping("/modify")
//            public String modifyForm(@RequestBody @ModelAttribute String faqSeq){
//                return "faq/modifyForm";
//            }
//
//            @PostMapping("/modifyData")
//            @ResponseBody
//            public CommonResponse modifyData(@RequestBody String faqSeq){
//
//                boolean modify = faqService.modifyFaq(faqSeq);
//                CommonResponse response = new CommonResponse();
//
//                if (modify) {
//                    response.setStatusCode(200);
//                } else {
//                    response.setStatusCode(403);
//                }
//                return response;
//            }
}
