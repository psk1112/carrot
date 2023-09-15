package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.form.CategoryCreateForm;
import kr.co.crewmate.carrot.model.form.CategoryDeleteForm;
import kr.co.crewmate.carrot.model.form.CategoryModifyForm;
import kr.co.crewmate.carrot.service.CategoryKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoryKindRestController {
    private final CategoryKindService categoryKindService;

    /**
     * 카테고리 등록
     * @param categoryCreateForm
     * @return response
     */
    @PostMapping("/category")
    public CommonResponse createCategory(@RequestBody @Valid CategoryCreateForm categoryCreateForm, BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();

        if (bindingResult.hasErrors()) {
            response.setStatusCode(401);
            response.setBody(bindingResult.getFieldError("categoryNames").getDefaultMessage());
            return response;
        } else {
            try {
                categoryKindService.createCategory(categoryCreateForm);
                response.setStatusCode(200);
            } catch (Exception e){
                response.setStatusCode(400);
            }

            return response;
        }
    }

    /**
     * 카테고리 수정
     * @param categoryModifyForm
     * @return response
     */
    @PutMapping("/category")
    public CommonResponse modifyCategory(@RequestBody @Valid CategoryModifyForm categoryModifyForm, BindingResult bindingResult) {
        CommonResponse response = new CommonResponse();

        if (bindingResult.hasErrors()) {
            response.setStatusCode(401);
            response.setBody(bindingResult.getFieldError("categoryNames").getDefaultMessage());
            return response;
        } else {
            try {
                categoryKindService.modifyCategory(categoryModifyForm);
                response.setStatusCode(200);
            } catch (Exception e){
                response.setStatusCode(400);
            }
        }

        return response;
    }

    /**
     * 카테고리 삭제
     *
     * @param categoryDeleteForm
     * @return redirect:/category/create
     */
    @DeleteMapping("/category")
    public CommonResponse deleteCategory(@RequestBody @Valid CategoryDeleteForm categoryDeleteForm) {
        CommonResponse response = new CommonResponse();

        try {
            categoryKindService.deleteCategory(categoryDeleteForm);
            response.setStatusCode(200);
        } catch (Exception e){
            response.setStatusCode(400);
        }
        return response;
    }

}
