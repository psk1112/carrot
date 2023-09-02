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
    List<UserClaimKindDTO> userKindList();
    int createUserKind (UserClaimKindDTO userClaimKindDTO);
    int modifyUserKind (UserClaimKindDTO userClaimKindDTO);


    //게시물 신고 카테고리 CRUD
    List<PostClaimKindDTO> postKindList();
    int createPostKind (PostClaimKindDTO postClaimKindDTO);
    int modifyPostKind (PostClaimKindDTO postClaimKindDTO);


    //자주 묻는 질문 카테고리 CRUD
    List<FaqKindDTO> faqKindList();
    int createFaqKind (FaqKindDTO faqKindDTO);
    int modifyFaqKind (FaqKindDTO faqKindDTO);


    //문의하기 카테고리 CRUD
    List<QuestionKindDTO> questionKindList();
    int createQuestionKind (QuestionKindDTO questionKindDTO);
    int modifyQuestionKind (QuestionKindDTO questionKindDTO);

    //삭제
    int deleteUserKind (int seq);
    int deletePostKind (int seq);
    int deleteFaqKind (int seq);
    int deleteQuestionKind (int seq);
}
