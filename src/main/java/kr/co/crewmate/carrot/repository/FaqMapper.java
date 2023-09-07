package kr.co.crewmate.carrot.repository;


import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {

    //자주묻는 질문 CRUD
    void createFaq (FaqListResponseDTO faqListResponseDTO);
    List<FaqListResponseDTO> selectFaqList ();
    int selectFaqListCount ();
    int deleteFaq (int faqSeq);
    int modifyFaq (int faqSeq);
}
