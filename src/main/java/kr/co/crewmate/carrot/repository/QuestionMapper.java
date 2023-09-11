package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.dto.QuestionListResponseDTO;
import kr.co.crewmate.carrot.model.entity.File;
import kr.co.crewmate.carrot.model.entity.Question;
import kr.co.crewmate.carrot.model.entity.QuestionImage;
import kr.co.crewmate.carrot.model.form.QuestionCreateForm;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    public void insertQuestion(QuestionListResponseDTO questionListResponseDTO);
    public void insertQuestionImage(QuestionImage questionImage);

    public List<Question> selectQuestionListAll();

    public int selectQuestionListAllCount();
}
