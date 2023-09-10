package kr.co.crewmate.carrot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswer {

    private String questionAnswerSeq;
    private String questionSeq;
    private String  questionAnswerContent;
    private String questionAnswerCreatedAt;

}
