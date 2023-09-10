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
public class QuestionCreateForm {

    private String questionSeq;
    private String userSeq;
    private String questionKindSeq;

    @NotEmpty(message ="제목을 입력하세요.")
    private String questionTitle;

    @NotEmpty(message ="내용을 입력하세요.")
    private String questionContent;
    private List<MultipartFile> uploadFiles;

}
