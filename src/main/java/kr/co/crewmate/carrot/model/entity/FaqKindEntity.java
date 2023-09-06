package kr.co.crewmate.carrot.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FaqKindEntity {
    private int faqKindSeq;
    private String faqKindName;

    public FaqKindEntity(int faqKindSeq, String faqKindName ){
        this.faqKindSeq = faqKindSeq;
        this.faqKindName = faqKindName;
    }
}
