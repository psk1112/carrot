package kr.co.crewmate.carrot.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CategoryConditionDTO {
private String categoryKind;
private String categorySeq;
private String inputValue;
@Size(min = 1, message = "카테고리명을 입력하세요.")
private List<String> newCategoryNames;
}
