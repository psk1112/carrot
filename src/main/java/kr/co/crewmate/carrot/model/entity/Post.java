package kr.co.crewmate.carrot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private int postSeq;
    private int userSeq;
    private int categorySeq;
    private String postKind;
    private String postTitle;
    private String postContent;
    private int postViewCount;
    private String postCreatedAt;
    private int postLocationSeq;
    private int postImportant;
}
