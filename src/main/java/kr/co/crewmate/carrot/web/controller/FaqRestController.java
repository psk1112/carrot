package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import kr.co.crewmate.carrot.model.entity.FaqKind;
import kr.co.crewmate.carrot.model.form.FaqCreateForm;
import kr.co.crewmate.carrot.model.form.FaqDeleteForm;
import kr.co.crewmate.carrot.model.form.FaqModifyForm;
import kr.co.crewmate.carrot.service.CategoryKindService;
import kr.co.crewmate.carrot.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FaqRestController {
    private final FaqService faqService;
    private final CategoryKindService categoryKindService;

    /**
     * 자주 묻는 질문 등록
     *
     * @param faqCreateForm
     * @return
     */
    @PostMapping("/faq")
    public CommonResponse createFaq(@RequestBody @Valid FaqCreateForm faqCreateForm, BindingResult bindingResult) {

        CommonResponse response = new CommonResponse();
        Map<String, String> error = new HashMap<>();

        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError("faqKindSeq") != null) {
                error.put("faqKindSeq", bindingResult.getFieldError("faqKindSeq").getDefaultMessage());
            }
            if (bindingResult.getFieldError("faqTitle") != null) {
                error.put("faqTitle", bindingResult.getFieldError("faqTitle").getDefaultMessage());
            }
            if (bindingResult.getFieldError("faqContent") != null) {
                error.put("faqContent", bindingResult.getFieldError("faqContent").getDefaultMessage());
            }
            response.setStatusCode(401);
            response.setBody(error);

        } else {
            try {
                faqService.createFaq(faqCreateForm);
                response.setStatusCode(200);
            } catch (Exception e){
                response.setStatusCode(400);
            }
        }

        return response;
    }

    @DeleteMapping("/faq")
    public CommonResponse deleteFaq(@RequestBody FaqDeleteForm faqDeleteForm) {

        CommonResponse response = new CommonResponse();

        try {
            faqService.deleteFaq(faqDeleteForm);
            response.setStatusCode(200);
        } catch (Exception e){
            response.setStatusCode(400);
        }
        return response;
    }

    @PutMapping("/faq")
    public CommonResponse modifyForm(@Valid @RequestBody FaqModifyForm faqModifyForm, BindingResult bindingResult) {

        CommonResponse response = new CommonResponse();
        Map<String, String> error = new HashMap<>();

        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError("faqKindSeq") != null) {
                error.put("faqKindSeq", bindingResult.getFieldError("faqKindSeq").getDefaultMessage());
            }
            if (bindingResult.getFieldError("faqTitle") != null) {
                error.put("faqTitle", bindingResult.getFieldError("faqTitle").getDefaultMessage());
            }
            if (bindingResult.getFieldError("faqContent") != null) {
                error.put("faqContent", bindingResult.getFieldError("faqContent").getDefaultMessage());
            }
            response.setStatusCode(401);
            response.setBody(error);
        } else {
            try {
                faqService.modifyFaq(faqModifyForm);
                response.setStatusCode(200);
            } catch (Exception e){
                response.setStatusCode(403);
            }
        }
        return response;
    }
}
