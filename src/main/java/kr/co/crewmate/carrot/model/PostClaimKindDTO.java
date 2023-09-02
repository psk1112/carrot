package kr.co.crewmate.carrot.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostClaimKindDTO {
    private int postClaimKindSeq;
    private String postClaimKindName;

    public PostClaimKindDTO( int postClaimKindSeq, String postClaimKindName ){
        this.postClaimKindSeq = postClaimKindSeq;
        this.postClaimKindName = postClaimKindName;
    }
}
