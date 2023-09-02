package kr.co.crewmate.carrot.service;

import com.fasterxml.jackson.core.JsonParser;
import kr.co.crewmate.carrot.model.FaqDTO;
import kr.co.crewmate.carrot.repository.FaqMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    /**
     * 자주 묻는 질문 등록 데이터 처리 메서드
     * @param faqDTO
     */
    public void processCreateFaq (FaqDTO faqDTO){

            Date nowDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String faqCreatedAt = dateFormat.format(nowDate);

            FaqDTO newFaqDTO = FaqDTO.builder()
                    .faqKindSeq(faqDTO.getFaqKindSeq())
                    .faqTitle(faqDTO.getFaqTitle())
                    .faqContent(faqDTO.getFaqContent())
                    .faqCreatedAt(faqCreatedAt)
                    .build();

            faqMapper.createFaq(newFaqDTO);
    }

    /**
     * 자주 묻는 질문 목록
     * @return List<FaqDTO>
     */
    public List<FaqDTO> faqList (String faqKindSeq){
        return faqMapper.faqList(faqKindSeq);
    }


    /**
     * 자주 묻는 질문 목록 갯수
     * @return int
     */
    public int countFaqList (String faqKindSeq){
        return faqMapper.countFaqList(faqKindSeq);
    }


}
