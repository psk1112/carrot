package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.form.CategoryCreateForm;
import kr.co.crewmate.carrot.model.form.CategoryDeleteForm;
import kr.co.crewmate.carrot.model.form.CategoryModifyForm;
import kr.co.crewmate.carrot.model.entity.FaqKindEntity;
import kr.co.crewmate.carrot.model.entity.PostClaimKindEntity;
import kr.co.crewmate.carrot.model.entity.QuestionKindEntity;
import kr.co.crewmate.carrot.model.entity.UserClaimKindEntity;
import kr.co.crewmate.carrot.repository.CategoryKindMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryKindService {

    private final CategoryKindMapper categoryKindMapper;

    /**
     * 카테고리 등록(공통)
     * @param categoryCreateForm
     */
    public boolean createCategory(CategoryCreateForm categoryCreateForm){
        String categoryKind = categoryCreateForm.getCategoryKind();
        List <String> categoryNames = categoryCreateForm.getCategoryNames();

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
     * @param categoryModifyForm
     */
    public boolean modifyCategory(CategoryModifyForm categoryModifyForm){
        List<String> categoryKinds = categoryModifyForm.getCategoryKinds();
        List<String> categorySeqs = categoryModifyForm.getCategorySequences();
        List<String> getCategoryNames = categoryModifyForm.getCategoryNames();

        boolean success = true;
        for (int i = 0; i < categorySeqs.size(); i++) {
            String categoryKind = categoryKinds.get(i);
            int categorySeq = Integer.parseInt(categorySeqs.get(i));
            String categoryName = getCategoryNames.get(i);

        switch (categoryKind) {
            case "user" -> {  // 회원
                UserClaimKindEntity userClaimKindEntity = new UserClaimKindEntity(categorySeq, categoryName);
                if (!modifyUserKind(userClaimKindEntity)) {
                    success = false;
                }
            }
            case "post" -> {  // 게시물
                PostClaimKindEntity postClaimKindDTO = new PostClaimKindEntity(categorySeq, categoryName);
                if (!modifyPostKind(postClaimKindDTO)) {
                    success = false;
                }
            }
            case "faq" -> {  // 자주묻는 질문
                FaqKindEntity faqKindEntity = new FaqKindEntity(categorySeq, categoryName);
                if (!modifyFaqKind(faqKindEntity)) {
                    success = false;
                }
            }
            case "ques" -> {  // 문의하기
                QuestionKindEntity questionKindEntity = new QuestionKindEntity(categorySeq, categoryName);
                if (!modifyQuestionKind(questionKindEntity)) {
                    success = false;
                }
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
        return categoryKindMapper.selectUserKindList();
    }


    /**
     * 회원 신고 카테고리 생성
     * @param cateName
     */
    public boolean createUserKind(String cateName){
        UserClaimKindEntity userClaimKindEntity = UserClaimKindEntity.builder()
                .userClaimKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertUserKind(userClaimKindEntity);
        return rowsCreate > 0;
    }


    /**
     * 회원 신고 카테고리 수정
     * @param userClaimKindEntity
     */
    public boolean modifyUserKind(UserClaimKindEntity userClaimKindEntity){

        int rowsModify = categoryKindMapper.updateUserKind(userClaimKindEntity);
        return rowsModify > 0;
    }


    /**
     * 게시물 신고 카테고리 목록 조회
     * @return List<PostKindDTO>
     */
    public List<PostClaimKindEntity> retrievePostKindList(){
        return categoryKindMapper.selectPostKindList();
    }


    /**
     * 게시물 신고 카테고리 생성
     * @param cateName
     */
    public boolean createPostKind(String cateName){
        PostClaimKindEntity postClaimKindDTO = PostClaimKindEntity.builder()
                .postClaimKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertPostKind(postClaimKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 게시물 신고 카테고리 수정
     * @param postClaimKindDTO
     */
    public boolean modifyPostKind(PostClaimKindEntity postClaimKindDTO){
        int rowsModify = categoryKindMapper.updatePostKind(postClaimKindDTO);
        return rowsModify > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 목록
     * @return List<FaqKindDTO>
     */
    public List<FaqKindEntity> retrieveFaqKindList(){
        return categoryKindMapper.selectFaqKindList();
    }


    /**
     * 자주 묻는 질문 카테고리 생성
     * @param cateName
     */
    public boolean createFaqKind(String cateName){
        FaqKindEntity faqKindEntity = FaqKindEntity.builder()
                .faqKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertFaqKind(faqKindEntity);
        return rowsCreate > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 수정
     * @param faqKindEntity
     */
    public boolean modifyFaqKind(FaqKindEntity faqKindEntity){
        int rowsModify = categoryKindMapper.updateFaqKind(faqKindEntity);
        return rowsModify > 0;
    }


    /**
     * 문의하기 카테고리 목록
     * @return List<CategoryDto>
     */
    public List<QuestionKindEntity> retrieveQuestionKindList(){
        return categoryKindMapper.selectQuestionKindList();
    }


    /**
     * 문의하기 카테고리 생성
     * @param cateName
     */
    public boolean createQuestionKind(String cateName){
        QuestionKindEntity questionKindEntity = QuestionKindEntity.builder()
                .questionKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertQuestionKind(questionKindEntity);
        return rowsCreate > 0;
    }


    /**
     * 문의하기 카테고리 수정
     * @param questionKindEntity
     */
    public boolean modifyQuestionKind(QuestionKindEntity questionKindEntity){
        int rowsModify = categoryKindMapper.updateQuestionKind(questionKindEntity);
        return rowsModify > 0;
    }

    /**
     * 삭제(공통)
     * @param categoryDeleteForm
     * @return
     */
    public boolean deleteCategory(CategoryDeleteForm categoryDeleteForm){

        String[] parts = categoryDeleteForm.getCategorySeq().split("_");
        System.out.println(parts[0]);
        System.out.println(parts[1]);

        if (parts.length > 1) {
            String categoryKind = parts[0];   // faq_34 =>faq
            String categorySeq = parts[1];   // faq_34 =>34
            int seq = Integer.parseInt(categorySeq);

            switch (categoryKind) {
                case "user" -> {
                    UserClaimKindEntity userClaimKindEntity = UserClaimKindEntity.builder()
                            .userClaimKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deleteUserKind(userClaimKindEntity);
                    return rowsDeleted > 0;
                }
                case "post" -> {
                    PostClaimKindEntity postClaimKindEntity = PostClaimKindEntity.builder()
                            .postClaimKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deletePostKind(postClaimKindEntity);
                    return rowsDeleted > 0;
                }
                case "faq" -> {
                    FaqKindEntity faqKindEntity = FaqKindEntity.builder()
                            .faqKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deleteFaqKind(faqKindEntity);
                    return rowsDeleted > 0;
                }
                case "ques" -> {
                    QuestionKindEntity questionKindEntity = QuestionKindEntity.builder()
                            .questionKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deleteQuestionKind(questionKindEntity);
                    return rowsDeleted > 0;
                }
            }
        }
        return false;
    }

}
