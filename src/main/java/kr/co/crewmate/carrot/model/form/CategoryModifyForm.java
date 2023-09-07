package kr.co.crewmate.carrot.model.form;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModifyForm {

    private List<String> categoryKinds;
    private List<String> categorySequences;

    @Size(min = 1, message = "카테고리명을 입력하세요.")
    private List<String> categoryNames;

}
