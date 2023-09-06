package kr.co.crewmate.carrot.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionKindEntity {

    private int questionKindSeq;
    private String questionKindName;

    public QuestionKindEntity(int questionKindSeq, String questionKindName ){
        this.questionKindSeq = questionKindSeq;
        this.questionKindName = questionKindName;
    }
}
