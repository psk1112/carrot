package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.dto.NoticeListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeListResponseDTO> selectNoticeListAll(NoticeListResponseDTO noticeListResponseDTO);
    int selectNoticeListAllCount(NoticeListResponseDTO noticeListResponseDTO);

}
