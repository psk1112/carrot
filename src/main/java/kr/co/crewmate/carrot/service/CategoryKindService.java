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
    public void createCategory(CategoryCreateForm categoryCreateForm){
        String categoryKind = categoryCreateForm.getCategoryKind();
        List <String> categoryNames = categoryCreateForm.getCategoryNames();

        switch (categoryKind) {
            case "user" -> {  // 회원
                for (String cateName : categoryNames) {
                    createUserKind(cateName);
                }
            }
            case "post" -> {  // 게시물
                for (String cateName : categoryNames) {
                    createPostKind(cateName);
                }
            }
            case "reply" -> {  // 댓글
                for (String cateName : categoryNames) {
                    createReplyKind(cateName);
                }
            }
            case "faq" -> {  // 자주묻는 질문
                for (String cateName : categoryNames) {
                    createFaqKind(cateName);
                }
            }
            case "ques" -> {  // 문의하기
                for (String cateName : categoryNames) {
                    createQuestionKind(cateName);
                }
            }
        }
    }


    /**
     * 카테고리 수정(공통)
     * @param categoryModifyForm
     */
    public void modifyCategory(CategoryModifyForm categoryModifyForm){
        List<String> categoryKinds = categoryModifyForm.getCategoryKinds();
        List<String> categorySeqs = categoryModifyForm.getCategorySequences();
        List<String> getCategoryNames = categoryModifyForm.getCategoryNames();

        for (int i = 0; i < categorySeqs.size(); i++) {
            String categoryKind = categoryKinds.get(i);
            int categorySeq = Integer.parseInt(categorySeqs.get(i));
            String categoryName = getCategoryNames.get(i);

            switch (categoryKind) {
                case "user" -> {  // 회원
                    UserClaimKind userClaimKind = new UserClaimKind(categorySeq, categoryName);
                    modifyUserKind(userClaimKind);
                }
                case "post" -> {  // 게시물
                    PostClaimKind postClaimKindDTO = new PostClaimKind(categorySeq, categoryName);
                    modifyPostKind(postClaimKindDTO);
                }
                case "reply" -> {  // 댓글
                    ReplyClaimKind replyClaimKind = new ReplyClaimKind(categorySeq, categoryName);
                    modifyReplyKind(replyClaimKind);
                }
                case "faq" -> {  // 자주묻는 질문
                    FaqKind faqKind = new FaqKind(categorySeq, categoryName);
                    modifyFaqKind(faqKind);
                }
                case "ques" -> {  // 문의하기
                    QuestionKind questionKind = new QuestionKind(categorySeq, categoryName);
                    modifyQuestionKind(questionKind);
                }
            }
        }
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
    public void createUserKind(String cateName){
        UserClaimKind userClaimKind = UserClaimKind.builder()
                .userClaimKindName(cateName)
                .build();
        categoryKindMapper.insertUserKind(userClaimKind);
    }


    /**
     * 회원 신고 카테고리 수정
     * @param userClaimKind
     */
    public void modifyUserKind(UserClaimKind userClaimKind){

        categoryKindMapper.updateUserKind(userClaimKind);
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
    public void createPostKind(String cateName){
        PostClaimKind postClaimKindDTO = PostClaimKind.builder()
                .postClaimKindName(cateName)
                .build();
        categoryKindMapper.insertPostKind(postClaimKindDTO);
    }


    /**
     * 게시물 신고 카테고리 수정
     * @param postClaimKindDTO
     */
    public void modifyPostKind(PostClaimKind postClaimKindDTO){
        categoryKindMapper.updatePostKind(postClaimKindDTO);
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
    public void createReplyKind(String cateName){
        ReplyClaimKind replyClaimKind = ReplyClaimKind.builder()
                .replyClaimKindName(cateName)
                .build();
        categoryKindMapper.insertReplyKind(replyClaimKind);
    }


    /**
     * 댓글 신고 카테고리 수정
     * @param replyClaimKind
     * @return boolean
     */
    public void modifyReplyKind(ReplyClaimKind replyClaimKind){
        categoryKindMapper.updateReplyKind(replyClaimKind);
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
    public void createFaqKind(String cateName){
        FaqKind faqKind = FaqKind.builder()
                .faqKindName(cateName)
                .build();
        categoryKindMapper.insertFaqKind(faqKind);
    }


    /**
     * 자주 묻는 질문 카테고리 수정
     * @param faqKind
     */
    public void modifyFaqKind(FaqKind faqKind){
        categoryKindMapper.updateFaqKind(faqKind);
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
    public void createQuestionKind(String cateName){
        QuestionKind questionKind = QuestionKind.builder()
                .questionKindName(cateName)
                .build();
        categoryKindMapper.insertQuestionKind(questionKind);
    }


    /**
     * 문의하기 카테고리 수정
     * @param questionKind
     */
    public void modifyQuestionKind(QuestionKind questionKind){
        categoryKindMapper.updateQuestionKind(questionKind);
    }

    /**
     * 삭제(공통)
     * @param categoryDeleteForm
     * @return
     */
    public void deleteCategory(CategoryDeleteForm categoryDeleteForm){

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
                    categoryKindMapper.deleteUserKind(userClaimKind);
                }
                case "post" -> {
                    PostClaimKind postClaimKind = PostClaimKind.builder()
                            .postClaimKindSeq(seq)
                            .build();
                    categoryKindMapper.deletePostKind(postClaimKind);
                }
                case "reply" -> {
                    ReplyClaimKind replyClaimKind = ReplyClaimKind.builder()
                            .replyClaimKindSeq(seq)
                            .build();
                    categoryKindMapper.deleteReplyKind(replyClaimKind);
                }
                case "faq" -> {
                    FaqKind faqKind = FaqKind.builder()
                            .faqKindSeq(seq)
                            .build();
                    categoryKindMapper.deleteFaqKind(faqKind);
                }
                case "ques" -> {
                    QuestionKind questionKind = QuestionKind.builder()
                            .questionKindSeq(seq)
                            .build();
                    categoryKindMapper.deleteQuestionKind(questionKind);
                }
            }
        }
    }
}
