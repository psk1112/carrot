package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.dto.QuestionListResponseDTO;
import kr.co.crewmate.carrot.model.entity.File;
import kr.co.crewmate.carrot.model.entity.Question;
import kr.co.crewmate.carrot.model.entity.QuestionAnswer;
import kr.co.crewmate.carrot.model.entity.QuestionImage;
import kr.co.crewmate.carrot.model.form.QuestionAnswerCreateForm;
import kr.co.crewmate.carrot.model.form.QuestionCreateForm;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    void insertQuestion(QuestionListResponseDTO questionListResponseDTO);
    void insertQuestionImage(QuestionImage questionImage);
    List<QuestionListResponseDTO> selectQuestionListAll();
    int selectQuestionListAllCount();
    List<QuestionListResponseDTO> selectQuestionList(String questionKindSeq);
    int selectQuestionListCount(String questionKindSeq);
    QuestionListResponseDTO selectQuestionDetail(String questionSeq);

    void insertQuestionAnswer(QuestionAnswerCreateForm questionAnswerCreateForm);

    QuestionAnswer selectQuestionAnswer(String questionSeq);
}
