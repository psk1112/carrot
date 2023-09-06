package kr.co.crewmate.carrot.web.controller;

import jakarta.validation.Valid;
import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.dto.CategoryConditionDTO;
import kr.co.crewmate.carrot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoryKindRestController {
    private final CategoryService categoryService;

    /**
     * 카테고리 등록
     * @param categoryConditionDTO
     * @return response
     */
    @PostMapping("/category")
    public CommonResponse createCategory(@Valid @RequestBody CategoryConditionDTO categoryConditionDTO, BindingResult bindingResult){

        CommonResponse response = new CommonResponse();

        System.out.println(categoryConditionDTO.getCategoryKind()+":::::"+categoryConditionDTO.getNewCategoryNames().size());

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.hasErrors());
            response.setStatusCode(401);
            response.setBody(bindingResult.getFieldError("newCategoryNames"));
            return response;
        }

        boolean save = categoryService.createCategory(categoryConditionDTO);

        if(save){
            response.setStatusCode(200);
        }else {
            response.setStatusCode(400);
        }
        return response;
    }

    /**
     * 카테고리 수정
     * @param requestData
     * @return response
     */
    @PutMapping("/category")
    public CommonResponse modifyCategory(@RequestBody String requestData){
        CommonResponse response = new CommonResponse();
        boolean save = categoryService.modifyCategory(requestData);

        if(save){
            response.setStatusCode(200);
        }else {
            response.setStatusCode(400);
        }
        return response;
    }

    /**
     * 카테고리 삭제
     *
     * @param inputSeq
     * @return redirect:/category/create
     */
    @DeleteMapping("/category")
    public CommonResponse deleteData(@RequestBody String inputSeq) {
        CommonResponse response = new CommonResponse();
        boolean deleted = categoryService.deleteCategory(inputSeq);

        if (deleted) {
            response.setStatusCode(200);
        }else {
            response.setStatusCode(400);
        }
        return response;
    }
}
