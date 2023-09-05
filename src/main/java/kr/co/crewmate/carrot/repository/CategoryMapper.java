package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.model.PostClaimKindDTO;
import kr.co.crewmate.carrot.model.QuestionKindDTO;
import kr.co.crewmate.carrot.model.UserClaimKindDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //회원 신고 카테고리 CRUD
    List<UserClaimKindDTO> selectUserKindList();
    int insertUserKind (UserClaimKindDTO userClaimKindDTO);
    int updateUserKind (UserClaimKindDTO userClaimKindDTO);


    //게시물 신고 카테고리 CRUD
    List<PostClaimKindDTO> selectPostKindList();
    int insertPostKind (PostClaimKindDTO postClaimKindDTO);
    int updatePostKind (PostClaimKindDTO postClaimKindDTO);


    //자주 묻는 질문 카테고리 CRUD
    List<FaqKindDTO> selectFaqKindList();
    int insertFaqKind (FaqKindDTO faqKindDTO);
    int updateFaqKind (FaqKindDTO faqKindDTO);


    //문의하기 카테고리 CRUD
    List<QuestionKindDTO> selectQuestionKindList();
    int insertQuestionKind (QuestionKindDTO questionKindDTO);
    int updateQuestionKind (QuestionKindDTO questionKindDTO);

    //삭제
    int deleteUserKind (int seq);
    int deletePostKind (int seq);
    int deleteFaqKind (int seq);
    int deleteQuestionKind (int seq);
}
