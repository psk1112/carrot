package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.entity.FaqKindEntity;
import kr.co.crewmate.carrot.model.entity.PostClaimKindEntity;
import kr.co.crewmate.carrot.model.entity.QuestionKindEntity;
import kr.co.crewmate.carrot.model.entity.UserClaimKindEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //회원 신고 카테고리 CRUD
    List<UserClaimKindEntity> selectUserKindList();
    int insertUserKind (UserClaimKindEntity userClaimKindEntity);
    int updateUserKind (UserClaimKindEntity userClaimKindEntity);


    //게시물 신고 카테고리 CRUD
    List<PostClaimKindEntity> selectPostKindList();
    int insertPostKind (PostClaimKindEntity postClaimKindDTO);
    int updatePostKind (PostClaimKindEntity postClaimKindDTO);


    //자주 묻는 질문 카테고리 CRUD
    List<FaqKindEntity> selectFaqKindList();
    int insertFaqKind (FaqKindEntity faqKindEntity);
    int updateFaqKind (FaqKindEntity faqKindEntity);


    //문의하기 카테고리 CRUD
    List<QuestionKindEntity> selectQuestionKindList();
    int insertQuestionKind (QuestionKindEntity questionKindEntity);
    int updateQuestionKind (QuestionKindEntity questionKindEntity);

    //삭제
    int deleteUserKind (int seq);
    int deletePostKind (int seq);
    int deleteFaqKind (int seq);
    int deleteQuestionKind (int seq);
}
