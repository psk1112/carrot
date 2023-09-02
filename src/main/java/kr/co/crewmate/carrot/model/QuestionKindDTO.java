package kr.co.crewmate.carrot.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionKindDTO {

    private int questionKindSeq;
    private String questionKindName;

    public QuestionKindDTO( int questionKindSeq, String questionKindName ){
        this.questionKindSeq = questionKindSeq;
        this.questionKindName = questionKindName;
    }
}
