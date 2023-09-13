package kr.co.crewmate.carrot.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerCreateForm {

    private int questionSeq;

    @NotEmpty(message ="내용을 입력하세요.")
    private String questionAnswerContent;
    private String questionAnswerCreatedAt;

}
