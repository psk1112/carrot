package kr.co.crewmate.carrot.repository;

import kr.co.crewmate.carrot.model.FaqDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {

    //자주묻는 질문 CRUD
    void createFaq (FaqDTO faqDTO);
    List<FaqDTO> faqList (String faqKindSeq);
    int countFaqList (String faqKindSeq);
    int deleteFaq (int faqSeq);
    int modifyFaq (int faqSeq);
}
