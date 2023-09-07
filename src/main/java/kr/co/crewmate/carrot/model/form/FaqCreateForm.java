package kr.co.crewmate.carrot.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqCreateForm {

    @NotEmpty(message = "카테고리 등록 후 이용하세요.")
    private String faqKindSeq;

    @NotEmpty(message ="제목을 입력하세요.")
    private String faqTitle;

    @NotEmpty(message ="내용을 입력하세요.")
    private String faqContent;
}
