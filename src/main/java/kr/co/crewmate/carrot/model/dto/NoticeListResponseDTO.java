package kr.co.crewmate.carrot.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeListResponseDTO {

    private int rownum;
    private int postSeq;
    private int userSeq;
    private int categorySeq;
    private int postKind;
    private String postTitle;
    private String postContent;
    private int postViewCount;
    private String postCreatedAt;
    private int postLocationSeq;
    private int postImportant;
}
