package kr.co.crewmate.carrot.repository;


import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import kr.co.crewmate.carrot.model.entity.Faq;
import kr.co.crewmate.carrot.model.form.FaqDeleteForm;
import kr.co.crewmate.carrot.model.form.FaqModifyForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {

    //자주묻는 질문 CRUD
    void insertFaq (FaqListResponseDTO faqListResponseDTO);
    List<FaqListResponseDTO> selectFaqListAll ();
    int selectFaqListAllCount ();
    List<FaqListResponseDTO> selectFaqList (String faqKindSeq);
    int selectFaqListCount (String faqKindSeq);
    Faq selectDetailFaq (String faqSeq);
    void deleteFaq (FaqDeleteForm faqDeleteForm);
    void updateFaq (FaqModifyForm faqModifyForm);
}
