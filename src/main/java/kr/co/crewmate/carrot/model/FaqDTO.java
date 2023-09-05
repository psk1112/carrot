package kr.co.crewmate.carrot.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqDTO {

    private int rownum;
    private int faqSeq;
    @Min(value = 1, message = "카테고리를 먼저 등록 후 이용하세요.")
    private int faqKindSeq;
    private String faqKindName;
    @NotEmpty(message = "제목을 입력하세요.")
    private String faqTitle;
    @NotEmpty(message = "내용을 입력하세요.")
    private String faqContent;
    private int faqViewCount;
    private String faqCreatedAt;

}
