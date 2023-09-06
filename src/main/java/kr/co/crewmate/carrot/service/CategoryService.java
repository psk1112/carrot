package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.dto.CategoryConditionDTO;
import kr.co.crewmate.carrot.model.entity.FaqKindEntity;
import kr.co.crewmate.carrot.model.entity.PostClaimKindEntity;
import kr.co.crewmate.carrot.model.entity.QuestionKindEntity;
import kr.co.crewmate.carrot.model.entity.UserClaimKindEntity;
import kr.co.crewmate.carrot.repository.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    /**
     * 카테고리 등록(공통)
     * @param categoryConditionDTO
     */
    public boolean createCategory(CategoryConditionDTO categoryConditionDTO){
        String categoryKind = categoryConditionDTO.getCategoryKind();
        List <String> categoryNames = categoryConditionDTO.getNewCategoryNames();

        boolean success = true;

        switch (categoryKind) {
            case "user" -> {  // 회원
                for (String cateName : categoryNames) {
                    if (!createUserKind(cateName)) {
                        success = false;
                    }
                }
            }
            case "post" -> {  // 게시물
                for (String cateName : categoryNames) {
                    if (!createPostKind(cateName)) {
                        success = false;
                    }
                }
            }
            case "faq" -> {  // 자주묻는 질문
                for (String cateName : categoryNames) {
                    if (!createFaqKind(cateName)) {
                        success = false;
                    }
                }
            }
            case "ques" -> {  // 문의하기
                for (String cateName : categoryNames) {
                    if (!createQuestionKind(cateName)) {
                        success = false;
                    }
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
        CategoryConditionDTO categoryInfo = new CategoryConditionDTO();
        String categoryKind = categoryInfo.getCategoryKind();
        String categorySeq = categoryInfo.getCategorySeq();
        String inputValue = categoryInfo.getInputValue();

        boolean success = true;

        if(categoryKind.equals("user")){ // 회원
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                UserClaimKindEntity userClaimKindEntity = new UserClaimKindEntity(Integer.parseInt(categorySeq), inputValue);
                if(!modifyUserKind(userClaimKindEntity)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("post")) { // 게시물
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                PostClaimKindEntity postClaimKindDTO = new PostClaimKindEntity(Integer.parseInt(categorySeq), inputValue);
                if (!modifyPostKind(postClaimKindDTO)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("faq")) { // 자주묻는 질문
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                FaqKindEntity faqKindEntity = new FaqKindEntity(Integer.parseInt(categorySeq), inputValue);
                if (!modifyFaqKind(faqKindEntity)){
                    success = false;
                }
            }
        } else if (categoryKind.equals("ques")) { // 문의하기
            if (!"newCate".equals(categorySeq)) {
                // 기존 카테고리 수정
                QuestionKindEntity questionKindEntity = new QuestionKindEntity(Integer.parseInt(categorySeq), inputValue);
                if (!modifyQuestionKind(questionKindEntity)){
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
    public List<UserClaimKindEntity> retrieveUserKindList(){
        return categoryMapper.selectUserKindList();
    }


    /**
     * 회원 신고 카테고리 생성
     * @param inputValue
     */
    public boolean createUserKind(String inputValue){
        UserClaimKindEntity userClaimKindEntity = UserClaimKindEntity.builder()
                .userClaimKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertUserKind(userClaimKindEntity);
        return rowsCreate > 0;
    }


    /**
     * 회원 신고 카테고리 수정
     * @param userClaimKindEntity
     */
    public boolean modifyUserKind(UserClaimKindEntity userClaimKindEntity){
        int rowsModify = categoryMapper.updateUserKind(userClaimKindEntity);
        return rowsModify > 0;
    }


    /**
     * 게시물 신고 카테고리 목록 조회
     * @return List<PostKindDTO>
     */
    public List<PostClaimKindEntity> retrievePostKindList(){
        return categoryMapper.selectPostKindList();
    }


    /**
     * 게시물 신고 카테고리 생성
     * @param inputValue
     */
    public boolean createPostKind(String inputValue){
        PostClaimKindEntity postClaimKindDTO = PostClaimKindEntity.builder()
                .postClaimKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertPostKind(postClaimKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 게시물 신고 카테고리 수정
     * @param postClaimKindDTO
     */
    public boolean modifyPostKind(PostClaimKindEntity postClaimKindDTO){
        int rowsModify = categoryMapper.updatePostKind(postClaimKindDTO);
        return rowsModify > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 목록
     * @return List<FaqKindDTO>
     */
    public List<FaqKindEntity> retrieveFaqKindList(){
        return categoryMapper.selectFaqKindList();
    }


    /**
     * 자주 묻는 질문 카테고리 생성
     * @param inputValue
     */
    public boolean createFaqKind(String inputValue){
        FaqKindEntity faqKindEntity = FaqKindEntity.builder()
                .faqKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertFaqKind(faqKindEntity);
        return rowsCreate > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 수정
     * @param faqKindEntity
     */
    public boolean modifyFaqKind(FaqKindEntity faqKindEntity){
        int rowsModify = categoryMapper.updateFaqKind(faqKindEntity);
        return rowsModify > 0;
    }


    /**
     * 문의하기 카테고리 목록
     * @return List<CategoryDto>
     */
    public List<QuestionKindEntity> retrieveQuestionKindList(){
        return categoryMapper.selectQuestionKindList();
    }


    /**
     * 문의하기 카테고리 생성
     * @param inputValue
     */
    public boolean createQuestionKind(String inputValue){
        QuestionKindEntity questionKindEntity = QuestionKindEntity.builder()
                .questionKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.insertQuestionKind(questionKindEntity);
        return rowsCreate > 0;
    }


    /**
     * 문의하기 카테고리 수정
     * @param questionKindEntity
     */
    public boolean modifyQuestionKind(QuestionKindEntity questionKindEntity){
        int rowsModify = categoryMapper.updateQuestionKind(questionKindEntity);
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
