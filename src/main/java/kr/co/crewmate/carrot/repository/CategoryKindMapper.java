package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryKindMapper {

    //회원 신고 카테고리 CRUD
    List<UserClaimKind> selectUserKindList();
    int insertUserKind (UserClaimKind userClaimKind);
    int updateUserKind (UserClaimKind userClaimKind);


    //게시물 신고 카테고리 CRUD
    List<PostClaimKind> selectPostKindList();
    int insertPostKind (PostClaimKind postClaimKind);
    int updatePostKind (PostClaimKind postClaimKind);


    //댓글 신고 카테고리 CRUD
    List<ReplyClaimKind> selectReplyKindList();

    int insertReplyKind(ReplyClaimKind replyClaimKind);

    int updateReplyKind(ReplyClaimKind replyClaimKind);


    //자주 묻는 질문 카테고리 CRUD
    List<FaqKind> selectFaqKindList();
    int insertFaqKind (FaqKind faqKind);
    int updateFaqKind (FaqKind faqKind);


    //문의하기 카테고리 CRUD
    List<QuestionKind> selectQuestionKindList();
    int insertQuestionKind (QuestionKind questionKind);
    int updateQuestionKind (QuestionKind questionKind);

    //댓글신고 카테고리 CRUD


    //삭제
    int deleteUserKind (UserClaimKind userClaimKind);
    int deletePostKind (PostClaimKind postClaimKind);
    int deleteReplyKind (ReplyClaimKind replyClaimKind);
    int deleteFaqKind (FaqKind faqKind);
    int deleteQuestionKind (QuestionKind questionKind);
}
