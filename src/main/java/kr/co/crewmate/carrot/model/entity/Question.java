package kr.co.crewmate.carrot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private String questionSeq;
    private String userSeq;
    private String questionKindSeq;
    private String questionTitle;
    private String questionContent;
    private String questionCreatedAt;
}
