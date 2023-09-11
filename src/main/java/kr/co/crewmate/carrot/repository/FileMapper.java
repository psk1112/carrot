package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insertFile(File file);

    List<File> selectByPostSeq(int postSeq);
}
