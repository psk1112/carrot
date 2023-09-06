package kr.co.crewmate.carrot.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostClaimKindEntity {
    private int postClaimKindSeq;
    private String postClaimKindName;

    public PostClaimKindEntity(int postClaimKindSeq, String postClaimKindName ){
        this.postClaimKindSeq = postClaimKindSeq;
        this.postClaimKindName = postClaimKindName;
    }
}
