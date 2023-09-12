package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.entity.*;
import kr.co.crewmate.carrot.model.form.CategoryCreateForm;
import kr.co.crewmate.carrot.model.form.CategoryDeleteForm;
import kr.co.crewmate.carrot.model.form.CategoryModifyForm;
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
            case "reply" -> {  // 댓글
                for (String cateName : categoryNames) {
                    if (!createReplyKind(cateName)) {
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
                UserClaimKind userClaimKind = new UserClaimKind(categorySeq, categoryName);
                if (!modifyUserKind(userClaimKind)) {
                    success = false;
                }
            }
            case "post" -> {  // 게시물
                PostClaimKind postClaimKindDTO = new PostClaimKind(categorySeq, categoryName);
                if (!modifyPostKind(postClaimKindDTO)) {
                    success = false;
                }
            }
            case "reply" -> {  // 댓글
                ReplyClaimKind replyClaimKind = new ReplyClaimKind(categorySeq, categoryName);
                if (!modifyReplyKind(replyClaimKind)) {
                    success = false;
                }
            }
            case "faq" -> {  // 자주묻는 질문
                FaqKind faqKind = new FaqKind(categorySeq, categoryName);
                if (!modifyFaqKind(faqKind)) {
                    success = false;
                }
            }
            case "ques" -> {  // 문의하기
                QuestionKind questionKind = new QuestionKind(categorySeq, categoryName);
                if (!modifyQuestionKind(questionKind)) {
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
    public List<UserClaimKind> retrieveUserKindList(){
        return categoryKindMapper.selectUserKindList();
    }


    /**
     * 회원 신고 카테고리 생성
     * @param cateName
     */
    public boolean createUserKind(String cateName){
        UserClaimKind userClaimKind = UserClaimKind.builder()
                .userClaimKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertUserKind(userClaimKind);
        return rowsCreate > 0;
    }


    /**
     * 회원 신고 카테고리 수정
     * @param userClaimKind
     */
    public boolean modifyUserKind(UserClaimKind userClaimKind){

        int rowsModify = categoryKindMapper.updateUserKind(userClaimKind);
        return rowsModify > 0;
    }


    /**
     * 게시물 신고 카테고리 목록 조회
     * @return List<PostKindDTO>
     */
    public List<PostClaimKind> retrievePostKindList(){
        return categoryKindMapper.selectPostKindList();
    }


    /**
     * 게시물 신고 카테고리 생성
     * @param cateName
     */
    public boolean createPostKind(String cateName){
        PostClaimKind postClaimKindDTO = PostClaimKind.builder()
                .postClaimKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertPostKind(postClaimKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 게시물 신고 카테고리 수정
     * @param postClaimKindDTO
     */
    public boolean modifyPostKind(PostClaimKind postClaimKindDTO){
        int rowsModify = categoryKindMapper.updatePostKind(postClaimKindDTO);
        return rowsModify > 0;
    }


    /**
     * 댓글 신고 카테고리 목록 조회
     * @return List<ReplyClaimKind>
     */
    public List<ReplyClaimKind> retrieveReplyKindList(){
        return categoryKindMapper.selectReplyKindList();
    }


    /**
     * 댓글 신고 카테고리 생성
     * @param cateName
     * @return boolean
     */
    public boolean createReplyKind(String cateName){
        ReplyClaimKind replyClaimKind = ReplyClaimKind.builder()
                .replyClaimKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertReplyKind(replyClaimKind);
        return rowsCreate > 0;
    }


    /**
     * 댓글 신고 카테고리 수정
     * @param replyClaimKind
     * @return boolean
     */
    public boolean modifyReplyKind(ReplyClaimKind replyClaimKind){
        int rowsModify = categoryKindMapper.updateReplyKind(replyClaimKind);
        return rowsModify > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 목록
     * @return List<FaqKindDTO>
     */
    public List<FaqKind> retrieveFaqKindList(){
        return categoryKindMapper.selectFaqKindList();
    }


    /**
     * 자주 묻는 질문 카테고리 생성
     * @param cateName
     */
    public boolean createFaqKind(String cateName){
        FaqKind faqKind = FaqKind.builder()
                .faqKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertFaqKind(faqKind);
        return rowsCreate > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 수정
     * @param faqKind
     */
    public boolean modifyFaqKind(FaqKind faqKind){
        int rowsModify = categoryKindMapper.updateFaqKind(faqKind);
        return rowsModify > 0;
    }


    /**
     * 문의하기 카테고리 목록
     * @return List<CategoryDto>
     */
    public List<QuestionKind> retrieveQuestionKindList(){
        return categoryKindMapper.selectQuestionKindList();
    }


    /**
     * 문의하기 카테고리 생성
     * @param cateName
     */
    public boolean createQuestionKind(String cateName){
        QuestionKind questionKind = QuestionKind.builder()
                .questionKindName(cateName)
                .build();
        int rowsCreate = categoryKindMapper.insertQuestionKind(questionKind);
        return rowsCreate > 0;
    }


    /**
     * 문의하기 카테고리 수정
     * @param questionKind
     */
    public boolean modifyQuestionKind(QuestionKind questionKind){
        int rowsModify = categoryKindMapper.updateQuestionKind(questionKind);
        return rowsModify > 0;
    }

    /**
     * 삭제(공통)
     * @param categoryDeleteForm
     * @return
     */
    public boolean deleteCategory(CategoryDeleteForm categoryDeleteForm){

        String[] parts = categoryDeleteForm.getCategorySeq().split("_");

        if (parts.length > 1) {
            String categoryKind = parts[0];   // faq_34 =>faq
            String categorySeq = parts[1];   // faq_34 =>34
            int seq = Integer.parseInt(categorySeq);

            switch (categoryKind) {
                case "user" -> {
                    UserClaimKind userClaimKind = UserClaimKind.builder()
                            .userClaimKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deleteUserKind(userClaimKind);
                    return rowsDeleted > 0;
                }
                case "post" -> {
                    PostClaimKind postClaimKind = PostClaimKind.builder()
                            .postClaimKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deletePostKind(postClaimKind);
                    return rowsDeleted > 0;
                }
                case "reply" -> {
                    ReplyClaimKind replyClaimKind = ReplyClaimKind.builder()
                            .replyClaimKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deleteReplyKind(replyClaimKind);
                    return rowsDeleted > 0;
                }
                case "faq" -> {
                    FaqKind faqKind = FaqKind.builder()
                            .faqKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deleteFaqKind(faqKind);
                    return rowsDeleted > 0;
                }
                case "ques" -> {
                    QuestionKind questionKind = QuestionKind.builder()
                            .questionKindSeq(seq)
                            .build();
                    int rowsDeleted = categoryKindMapper.deleteQuestionKind(questionKind);
                    return rowsDeleted > 0;
                }
            }
        }
        return false;
    }

}
