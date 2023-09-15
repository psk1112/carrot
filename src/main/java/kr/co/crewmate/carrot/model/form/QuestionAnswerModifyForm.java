package kr.co.crewmate.carrot.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerModifyForm {

    private int questionAnswerSeq;

    @NotEmpty(message ="내용을 입력하세요.")
    private String questionAnswerContent;

}
