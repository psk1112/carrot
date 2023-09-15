package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryKindMapper {

    //회원 신고 카테고리 CRUD
    List<UserClaimKind> selectUserKindList();
    void insertUserKind (UserClaimKind userClaimKind);
    void updateUserKind (UserClaimKind userClaimKind);


    //게시물 신고 카테고리 CRUD
    List<PostClaimKind> selectPostKindList();
    void insertPostKind (PostClaimKind postClaimKind);
    void updatePostKind (PostClaimKind postClaimKind);


    //댓글 신고 카테고리 CRUD
    List<ReplyClaimKind> selectReplyKindList();

    void insertReplyKind(ReplyClaimKind replyClaimKind);

    void updateReplyKind(ReplyClaimKind replyClaimKind);


    //자주 묻는 질문 카테고리 CRUD
    List<FaqKind> selectFaqKindList();
    void insertFaqKind (FaqKind faqKind);
    void updateFaqKind (FaqKind faqKind);


    //문의하기 카테고리 CRUD
    List<QuestionKind> selectQuestionKindList();
    void insertQuestionKind (QuestionKind questionKind);
    void updateQuestionKind (QuestionKind questionKind);

    //댓글신고 카테고리 CRUD


    //삭제
    void deleteUserKind (UserClaimKind userClaimKind);
    void deletePostKind (PostClaimKind postClaimKind);
    void deleteReplyKind (ReplyClaimKind replyClaimKind);
    void deleteFaqKind (FaqKind faqKind);
    void deleteQuestionKind (QuestionKind questionKind);
}
