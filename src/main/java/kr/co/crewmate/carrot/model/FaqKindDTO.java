package kr.co.crewmate.carrot.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FaqKindDTO {
    private int faqKindSeq;
    private String faqKindName;

    public FaqKindDTO( int faqKindSeq, String faqKindName ){
        this.faqKindSeq = faqKindSeq;
        this.faqKindName = faqKindName;
    }
}
