package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.form.FaqCreateForm;
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

    /**
     * 자주 묻는 질문 등록
     * @param faqCreateForm
     * @return
     */
    @PostMapping("/faq")
    public CommonResponse createFaq (@RequestBody @Valid FaqCreateForm faqCreateForm, BindingResult bindingResult){

        CommonResponse response = new CommonResponse();
        if(bindingResult.hasErrors()) {
            response.setStatusCode(401);

            //bindingResult.getFieldError 분기처리 해줘야될거 같음
            //한개씩 안했을 수도 있지만 둘다 안쓰거나 셋다 안할떄도 있자나.. 근데 같이 나오려나?
            //이거 여기서 안되니까 내일 수정해
            response.setBody(bindingResult.getFieldError("faqKindSeq").getDefaultMessage());
            response.setBody(bindingResult.getFieldError("faqTitle").getDefaultMessage());
            response.setBody(bindingResult.getFieldError("faqContent").getDefaultMessage());
            return response;
        }
            boolean save = faqService.createFaq(faqCreateForm);
            if(save){
                response.setStatusCode(200);
            }else{
                response.setStatusCode(400);
            }
            return response;
        }

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
