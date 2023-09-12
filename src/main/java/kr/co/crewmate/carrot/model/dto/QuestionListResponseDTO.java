package kr.co.crewmate.carrot.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import kr.co.crewmate.carrot.model.entity.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionListResponseDTO {

    private int rownum;
    private int questionSeq;
    private int userSeq;
    private int fileCnt;
    private String questionKindSeq;
    private String questionKindName;
    private String questionTitle;
    private String questionContent;
    private String questionCreatedAt;
    private int questionAnswerSeq;
    private String hasAnswer;
    private List<String> filePaths;

}
