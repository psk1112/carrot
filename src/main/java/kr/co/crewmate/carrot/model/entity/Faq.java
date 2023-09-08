package kr.co.crewmate.carrot.model.entity;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faq {

    private int faqSeq;
    private int faqKindSeq;
    private String faqKindName;
    private String faqTitle;
    private String faqContent;
    private int faqViewCount;
    private String faqCreatedAt;
}
