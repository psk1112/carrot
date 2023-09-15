package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.dto.QuestionListResponseDTO;
import kr.co.crewmate.carrot.model.entity.File;
import kr.co.crewmate.carrot.model.entity.Question;
import kr.co.crewmate.carrot.model.entity.QuestionAnswer;
import kr.co.crewmate.carrot.model.entity.QuestionImage;
import kr.co.crewmate.carrot.model.form.QuestionAnswerCreateForm;
import kr.co.crewmate.carrot.model.form.QuestionAnswerDeleteForm;
import kr.co.crewmate.carrot.model.form.QuestionCreateForm;
import kr.co.crewmate.carrot.model.form.QuestionDeleteForm;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    void insertQuestion(QuestionListResponseDTO questionListResponseDTO);
    void insertQuestionImage(QuestionImage questionImage);
    void updateQuestion(QuestionListResponseDTO questionListResponseDTO);
    void updateQuestionImage(QuestionImage questionImage);
    void deleteQuestion (QuestionDeleteForm questionDeleteForm);

    List<QuestionListResponseDTO> selectQuestionListAll();
    int selectQuestionListAllCount();
    List<QuestionListResponseDTO> selectQuestionList(String questionKindSeq);
    int selectQuestionListCount(String questionKindSeq);
    QuestionListResponseDTO selectQuestionDetail(String questionSeq);

    List<QuestionListResponseDTO> selectMyQuestionListAll(int userSeq);
    List<QuestionListResponseDTO> selectMyQuestionList(int userSeq, String questionKindSeq);
    int selectMyQuestionListCount(int userSeq, String questionKindSeq);
    int selectMyQuestionListAllCount(int userSeq);

    void insertQuestionAnswer(QuestionAnswerCreateForm questionAnswerCreateForm);
    QuestionAnswer selectQuestionAnswer(String questionSeq);
    void updateQuestionAnswer(QuestionAnswer questionAnswer);
    void deleteQuestionAnswer(QuestionAnswerDeleteForm questionAnswerDeleteForm);
}
