package kr.co.crewmate.carrot.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqModifyForm {

    private String faqSeq;

    @NotEmpty(message = "카테고리 등록 후 이용하세요.")
    private String faqKindSeq;

    @NotEmpty(message ="제목을 입력하세요.")
    private String faqTitle;

    @NotEmpty(message ="내용을 입력하세요.")
    private String faqContent;
}
