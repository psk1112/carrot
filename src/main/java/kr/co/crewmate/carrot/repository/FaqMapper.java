package kr.co.crewmate.carrot.repository;


import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import kr.co.crewmate.carrot.model.entity.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {

    //자주묻는 질문 CRUD
    int insertFaq (FaqListResponseDTO faqListResponseDTO);
    List<FaqListResponseDTO> selectFaqList (String faqKindSeq);
    Faq retrieveDetailFaq (int faqSeq);
    int selectFaqListCount ();
    int deleteFaq (int faqSeq);
    int modifyFaq (int faqSeq);
}
