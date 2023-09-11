package kr.co.crewmate.carrot.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionListResponseDTO {

    private int rownum;
    private int questionSeq;
    private int userSeq;
    private String questionKindSeq;

    private String questionTitle;

    private String questionContent;
    private String questionCreatedAt;

}
