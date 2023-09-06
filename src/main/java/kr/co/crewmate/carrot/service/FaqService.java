package kr.co.crewmate.carrot.service;

import com.fasterxml.jackson.core.JsonParser;
import kr.co.crewmate.carrot.model.dto.FaqListResponseDTO;
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
     * 자주 묻는 질문 등록 데이터 처리 메서드
     * @param faqListResponseDTO
     */
    public void processCreateFaq (FaqListResponseDTO faqListResponseDTO){

            Date nowDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String faqCreatedAt = dateFormat.format(nowDate);

        FaqListResponseDTO newFaqDTO = FaqListResponseDTO.builder()
                    .faqKindSeq(faqListResponseDTO.getFaqKindSeq())
                    .faqTitle(faqListResponseDTO.getFaqTitle())
                    .faqContent(faqListResponseDTO.getFaqContent())
                    .faqCreatedAt(faqCreatedAt)
                    .build();

            faqMapper.createFaq(newFaqDTO);
    }

    /**
     * 자주 묻는 질문 목록
     * @return List<FaqDTO>
     */
    public List<FaqListResponseDTO> faqList (String faqKindSeq){
        return faqMapper.faqList(faqKindSeq);
    }


    /**
     * 자주 묻는 질문 목록 갯수
     * @return int
     */
    public int countFaqList (String faqKindSeq){
        return faqMapper.countFaqList(faqKindSeq);
    }

    /**
     * 자주 묻는 질물 삭제
     * @param faqSeq
     * @return boolean
     */
    public boolean deleteFaq (String faqSeq){
        int seq = Integer.parseInt(faqSeq);
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


}
