package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.*;
import kr.co.crewmate.carrot.repository.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryInputInfoDTO processCategory(String requestData) {
        String categoryKind = null;
        String categorySeq = null;
        String inputValue = null;

        try {
            // JSON 문자열을 JSON 객체로 파싱
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(requestData);

            // JSON 객체에서 원하는 필드 추출
            JSONArray inputSeqArr = (JSONArray) jsonObject.get("inputSeq");
            JSONArray inputValueArr = (JSONArray) jsonObject.get("inputValue");

            for (int i = 0; i < inputSeqArr.size(); i++) {
                String inputSeqAll = (String) inputSeqArr.get(i);
                inputValue = (String) inputValueArr.get(i);
                String[] parts = inputSeqAll.split("_");

                if (parts.length > 1) {
                    categoryKind = parts[0];   // faq_34 =>faq
                    categorySeq = parts[1];   // faq_34 =>34
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // CategoryInfo 객체로 묶어서 반환
        return new CategoryInputInfoDTO(categoryKind, categorySeq, inputValue);
    }


    /**
     * 카테고리 등록(공통)
     * @param requestData
     */
    public boolean createCategory(String requestData){
        CategoryInputInfoDTO categoryInfo = new CategoryInputInfoDTO();
        String categoryKind = categoryInfo.getCategoryKind();
        String categorySeq = categoryInfo.getCategorySeq();
        String inputValue = categoryInfo.getInputValue();

        boolean success = true;

        if(categoryKind.equals("user")){ // 회원
            if ("newCate".equals(categorySeq)) { // 새로운 카테고리인 경우
                createUserKind(inputValue);
                if (!createUserKind(inputValue)) {
                    success = false;
                }
            }
        } else if (categoryKind.equals("post")) { // 게시물
            if ("newCate".equals(categorySeq)) { // 새로운 카테고리인 경우
                createPostKind(inputValue);
                if(!createPostKind(inputValue)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("faq")) { // 자주묻는 질문
            if ("newCate".equals(categorySeq)) { // 새로운 카테고리인 경우
                if(!createFaqKind(inputValue)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("ques")) { // 문의하기
            if ("newCate".equals(categorySeq)) { // 새로운 카테고리인 경우
                if (!createQuestionKind(inputValue)) {
                    success = false;
                }
            }
        }
        return success;
    }


    /**
     * 카테고리 수정(공통)
     * @param requestData
     */
    public boolean modifyCategory(String requestData){
        CategoryInputInfoDTO categoryInfo = new CategoryInputInfoDTO();
        String categoryKind = categoryInfo.getCategoryKind();
        String categorySeq = categoryInfo.getCategorySeq();
        String inputValue = categoryInfo.getInputValue();

        boolean success = true;

        if(categoryKind.equals("user")){ // 회원
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                UserClaimKindDTO userClaimKindDTO = new UserClaimKindDTO(Integer.parseInt(categorySeq), inputValue);
                if(!modifyUserKind(userClaimKindDTO)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("post")) { // 게시물
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                PostClaimKindDTO postClaimKindDTO = new PostClaimKindDTO(Integer.parseInt(categorySeq), inputValue);
                if (!modifyPostKind(postClaimKindDTO)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("faq")) { // 자주묻는 질문
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                FaqKindDTO faqKindDTO = new FaqKindDTO(Integer.parseInt(categorySeq), inputValue);
                if (!modifyFaqKind(faqKindDTO)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("ques")) { // 문의하기
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                QuestionKindDTO questionKindDTO = new QuestionKindDTO(Integer.parseInt(categorySeq), inputValue);
                if (!modifyQuestionKind(questionKindDTO)){
                    success = false;
                }
            }
        }

        return success;
    }


    /**
     * 회원 신고 카테고리 목록 조회
     * @return List<UserClaimKindDTO>
     */
    public List<UserClaimKindDTO> retrieveUserKindList(){
        return categoryMapper.selectUserKindList();
    }


    /**
     * 회원 신고 카테고리 생성
     * @param inputValue
     */
    public boolean createUserKind(String inputValue){
        UserClaimKindDTO userClaimKindDTO = UserClaimKindDTO.builder()
                .userClaimKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertUserKind(userClaimKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 회원 신고 카테고리 수정
     * @param userClaimKindDTO
     */
    public boolean modifyUserKind(UserClaimKindDTO userClaimKindDTO){
        int rowsModify = categoryMapper.updateUserKind(userClaimKindDTO);
        return rowsModify > 0;
    }


    /**
     * 게시물 신고 카테고리 목록 조회
     * @return List<PostKindDTO>
     */
    public List<PostClaimKindDTO> retrievePostKindList(){
        return categoryMapper.selectPostKindList();
    }


    /**
     * 게시물 신고 카테고리 생성
     * @param inputValue
     */
    public boolean createPostKind(String inputValue){
        PostClaimKindDTO postClaimKindDTO = PostClaimKindDTO.builder()
                .postClaimKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertPostKind(postClaimKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 게시물 신고 카테고리 수정
     * @param postClaimKindDTO
     */
    public boolean modifyPostKind(PostClaimKindDTO postClaimKindDTO){
        int rowsModify = categoryMapper.updatePostKind(postClaimKindDTO);
        return rowsModify > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 목록
     * @return List<FaqKindDTO>
     */
    public List<FaqKindDTO> retrieveFaqKindList(){
        return categoryMapper.selectFaqKindList();
    }


    /**
     * 자주 묻는 질문 카테고리 생성
     * @param inputValue
     */
    public boolean createFaqKind(String inputValue){
        FaqKindDTO faqKindDTO = FaqKindDTO.builder()
                .faqKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertFaqKind(faqKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 수정
     * @param faqKindDTO
     */
    public boolean modifyFaqKind(FaqKindDTO faqKindDTO){
        int rowsModify = categoryMapper.updateFaqKind(faqKindDTO);
        return rowsModify > 0;
    }


    /**
     * 문의하기 카테고리 목록
     * @return List<CategoryDto>
     */
    public List<QuestionKindDTO> retrieveQuestionKindList(){
        return categoryMapper.selectQuestionKindList();
    }


    /**
     * 문의하기 카테고리 생성
     * @param inputValue
     */
    public boolean createQuestionKind(String inputValue){
        QuestionKindDTO questionKindDTO = QuestionKindDTO.builder()
                .questionKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertQuestionKind(questionKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 문의하기 카테고리 수정
     * @param questionKindDTO
     */
    public boolean modifyQuestionKind(QuestionKindDTO questionKindDTO){
        int rowsModify = categoryMapper.updateQuestionKind(questionKindDTO);
        return rowsModify > 0;
    }

    /**
     * 삭제(공통)
     * @param inputSeq
     * @return
     */
    public boolean deleteCategory(String inputSeq){
        System.out.println(inputSeq);
        String[] parts = inputSeq.split("_");

        if (parts.length > 1) {
            String prontUnderscore = parts[0];   // faq_34 =>faq
            String afterUnderscore = parts[1];   // faq_34 =>34
            int seq = Integer.parseInt(afterUnderscore);

            if(prontUnderscore.equals("user")) {
                int rowsDeleted = categoryMapper.deleteUserKind(seq);
                return rowsDeleted > 0;
            } else if (prontUnderscore.equals("post")) {
                int rowsDeleted = categoryMapper.deletePostKind(seq);
                return rowsDeleted > 0;
            } else if (prontUnderscore.equals("faq")) {
                int rowsDeleted = categoryMapper.deleteFaqKind(seq);
                return rowsDeleted > 0;
            } else if (prontUnderscore.equals("ques")) {
                int rowsDeleted = categoryMapper.deleteQuestionKind(seq);
                return rowsDeleted > 0;
            }
        }
        return false;
    }

}
