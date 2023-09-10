package kr.co.crewmate.carrot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {

    private int fileSeq;
    private Integer userSeq;
    private String filePath;
    private int fileSize;
}
