package kr.co.crewmate.carrot.model.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserClaimKindEntity {

    private int userClaimKindSeq;

    @NotEmpty(message = "카테고리명은 필수 입력값입니다.")
    private String userClaimKindName;
}
