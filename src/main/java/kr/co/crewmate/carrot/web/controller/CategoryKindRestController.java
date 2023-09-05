package kr.co.crewmate.carrot.web.controller;

import kr.co.crewmate.carrot.model.CommonResponse;
import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CategoryKindRestController {
    private final CategoryService categoryService;

    /**
     * 카테고리 등록
     * @param requestData
     * @return response
     */
    @PostMapping("/category")
    public CommonResponse createCategory(@RequestBody String requestData){
        CommonResponse response = new CommonResponse();
        boolean save = categoryService.createCategory(requestData);

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
