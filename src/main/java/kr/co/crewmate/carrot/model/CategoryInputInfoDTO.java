package kr.co.crewmate.carrot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInputInfoDTO {
    private String categoryKind;
    private String categorySeq;
    private String inputValue;
}
