package kr.co.crewmate.carrot.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqListResponseDTO {

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
