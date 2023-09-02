package kr.co.crewmate.carrot.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserClaimKindDTO {

    private int userClaimKindSeq;
    private String userClaimKindName;

    public UserClaimKindDTO( int userClaimKindSeq, String userClaimKindName ){
        this.userClaimKindSeq = userClaimKindSeq;
        this.userClaimKindName = userClaimKindName;
    }
}
