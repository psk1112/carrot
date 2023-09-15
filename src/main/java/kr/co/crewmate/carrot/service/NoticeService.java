package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.dto.NoticeListResponseDTO;
import kr.co.crewmate.carrot.repository.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public List<NoticeListResponseDTO> retrieveNoticeListAll (NoticeListResponseDTO noticeListResponseDTO){
        noticeListResponseDTO.setPostKind(1);   //0_게시판 1_공지사항

        List<NoticeListResponseDTO> notices = noticeMapper.selectNoticeListAll(noticeListResponseDTO);

        return notices;
    }

    public int retrieveNoticeListAllCount (NoticeListResponseDTO noticeListResponseDTO){
        noticeListResponseDTO.setPostKind(1);   //0_게시판 1_공지사항
        return noticeMapper.selectNoticeListAllCount(noticeListResponseDTO);
    }

}
