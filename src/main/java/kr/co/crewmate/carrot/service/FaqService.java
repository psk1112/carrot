package kr.co.crewmate.carrot.service;

import com.fasterxml.jackson.core.JsonParser;
import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
import kr.co.crewmate.carrot.model.entity.Faq;
import kr.co.crewmate.carrot.model.form.FaqCreateForm;
import kr.co.crewmate.carrot.model.form.FaqDeleteForm;
import kr.co.crewmate.carrot.repository.FaqMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    /**
     * 자주 묻는 질문 등록
     * @param faqCreateForm
     */
    public boolean createFaq (FaqCreateForm faqCreateForm){

            Date nowDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String faqCreatedAt = dateFormat.format(nowDate);

        FaqListResponseDTO newFaqDTO = FaqListResponseDTO.builder()
                    .faqKindSeq(Integer.parseInt(faqCreateForm.getFaqKindSeq()))
                    .faqTitle(faqCreateForm.getFaqTitle())
                    .faqContent(faqCreateForm.getFaqContent())
                    .faqCreatedAt(faqCreatedAt)
                    .build();

        int rowsCreate = faqMapper.insertFaq(newFaqDTO);
        return rowsCreate > 0;
    }

    /**
     * 자주 묻는 질문 목록
     * @return List<FaqDTO>
     */
    public List<FaqListResponseDTO> retrieveFaqList (String faqKindSeq){
        return faqMapper.selectFaqList(faqKindSeq);
    }


    /**
     * 자주 묻는 질문 목록 갯수
     * @return int
     */
    public int retrieveFaqListCount (){
        return faqMapper.selectFaqListCount();
    }

    /**
     * 자주 묻는 질문 삭제
     * @param faqDeleteForm
     * @return boolean
     */
    public boolean deleteFaq (FaqDeleteForm faqDeleteForm){
        int seq = Integer.parseInt(faqDeleteForm.getCategorySeq());
        int deleteFaqCnt = faqMapper.deleteFaq(seq);
        return deleteFaqCnt > 0;
    }

    /**
     * 자주 묻는 질문 수정
     * @param faqSeq
     * @return boolean
     */
    public boolean modifyFaq (String faqSeq){
        int seq = Integer.parseInt(faqSeq);
        int modifyFaqCnt = faqMapper.modifyFaq(seq);
        return modifyFaqCnt > 0;
    }

    public Faq retrieveDetailFaq (Faq faq){
        int seq = faq.getFaqSeq();
        return faqMapper.retrieveDetailFaq(seq);
    }

}
