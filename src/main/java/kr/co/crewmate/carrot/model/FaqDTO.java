package kr.co.crewmate.carrot.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class FaqDTO {

    private int rownum;
    private int faqSeq;
    private int faqKindSeq;
    private String faqKindName;
    @NotEmpty(message = "제목을 입력하세요.")
    private String faqTitle;
    @NotEmpty(message = "내용을 입력하세요.")
    private String faqContent;
    private int faqViewCount;
    private String faqCreatedAt;

    public FaqDTO(int rownum, int faqSeq, int faqKindSeq, String faqKindName,
                  String faqTitle, String faqContent, int faqViewCount, String faqCreatedAt) {
        this.rownum = rownum;
        this.faqSeq = faqSeq;
        this.faqKindSeq = faqKindSeq;
        this.faqKindName = faqKindName;
        this.faqTitle = faqTitle;
        this.faqContent = faqContent;
        this.faqViewCount = faqViewCount;
        this.faqCreatedAt = faqCreatedAt;
    }
}
