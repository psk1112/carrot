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
public class CategoryCreateForm {

    private String categoryKind;    //카테고리 종류 (자주묻는질문, 공지사항, 게시판, 회원 등)

    @Size(min = 1, message = "카테고리명을 입력하세요.")
    private List<String> categoryNames;
}
