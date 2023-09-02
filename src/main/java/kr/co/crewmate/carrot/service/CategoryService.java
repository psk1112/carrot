package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.FaqKindDTO;
import kr.co.crewmate.carrot.model.PostClaimKindDTO;
import kr.co.crewmate.carrot.model.QuestionKindDTO;
import kr.co.crewmate.carrot.model.UserClaimKindDTO;
import kr.co.crewmate.carrot.repository.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;


    /**
     * 카테고리를 등록,수정하는 과정
     * @param requestData
     */
    public boolean processCategoryies(String requestData){
        boolean success = true;
        try {
            // JSON 문자열을 JSON 객체로 파싱
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(requestData);

            // JSON 객체에서 원하는 필드 추출
            JSONArray inputSeqArr = (JSONArray) jsonObject.get("inputSeq");
            JSONArray inputValueArr = (JSONArray) jsonObject.get("inputValue");

            for (int i = 0; i < inputSeqArr.size(); i++) {

                String inputSeqAll = (String) inputSeqArr.get(i);
                String inputValue = (String) inputValueArr.get(i);
                String[] parts = inputSeqAll.split("_");

                if (parts.length > 1) {

                    String prontUnderscore = parts[0];   // faq_34 =>faq
                    String afterUnderscore = parts[1];   // faq_34 =>34

                    if(prontUnderscore.equals("user")){ // 회원
                        if ("newCate".equals(afterUnderscore)) { // 새로운 카테고리인 경우
                            if (!createUserKind(inputValue)) {
                                success = false;
                            }
                        } else {
                            // 기존 카테고리 수정
                            UserClaimKindDTO userClaimKindDTO = new UserClaimKindDTO(Integer.parseInt(afterUnderscore), inputValue);
                            if(!modifyUserKind(userClaimKindDTO)){
                                success = false;
                            }
                        }
                    } else if (prontUnderscore.equals("post")) { // 게시물
                        if ("newCate".equals(afterUnderscore)) { // 새로운 카테고리인 경우
                            createPostKind(inputValue);
                            if(!createPostKind(inputValue)){
                                success = false;
                            }
                        } else {
                            // 기존 카테고리 수정
                            PostClaimKindDTO postClaimKindDTO = new PostClaimKindDTO(Integer.parseInt(afterUnderscore), inputValue);
                            if (!modifyPostKind(postClaimKindDTO)){
                                success = false;
                            }
                        }
                    } else if (prontUnderscore.equals("faq")) { // 자주묻는 질문
                        if ("newCate".equals(afterUnderscore)) { // 새로운 카테고리인 경우
                            if(!createFaqKind(inputValue)){
                                success = false;
                            }
                        } else {
                            // 기존 카테고리 수정
                            FaqKindDTO faqKindDTO = new FaqKindDTO(Integer.parseInt(afterUnderscore), inputValue);
                            if (!modifyFaqKind(faqKindDTO)){
                                success = false;
                            }
                        }
                    } else if (prontUnderscore.equals("ques")) { // 문의하기
                        if ("newCate".equals(afterUnderscore)) { // 새로운 카테고리인 경우
                            if(!createQuestionKind(inputValue)){
                                success = false;
                            }
                        } else {
                            // 기존 카테고리 수정
                            QuestionKindDTO questionKindDTO = new QuestionKindDTO(Integer.parseInt(afterUnderscore), inputValue);
                            if (!modifyQuestionKind(questionKindDTO)){
                                success = false;
                            }
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }


    /**
     * 회원 신고 카테고리 목록
     * @return List<UserClaimKindDTO>
     */
    public List<UserClaimKindDTO> userKindList(){
        return categoryMapper.userKindList();
    }


    /**
     * 회원 신고 카테고리 생성
     * @param inputValue
     */
    public boolean createUserKind(String inputValue){
        UserClaimKindDTO userClaimKindDTO = UserClaimKindDTO.builder()
                .userClaimKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.createUserKind(userClaimKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 회원 신고 카테고리 수정
     * @param userClaimKindDTO
     */
    public boolean modifyUserKind(UserClaimKindDTO userClaimKindDTO){
        int rowsModify = categoryMapper.modifyUserKind(userClaimKindDTO);
        return rowsModify > 0;
    }


    /**
     * 게시물 신고 카테고리 목록
     * @return List<PostKindDTO>
     */
    public List<PostClaimKindDTO> postKindList(){
        return categoryMapper.postKindList();
    }


    /**
     * 게시물 신고 카테고리 생성
     * @param inputValue
     */
    public boolean createPostKind(String inputValue){
        PostClaimKindDTO postClaimKindDTO = PostClaimKindDTO.builder()
                .postClaimKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.createPostKind(postClaimKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 게시물 신고 카테고리 수정
     * @param postClaimKindDTO
     */
    public boolean modifyPostKind(PostClaimKindDTO postClaimKindDTO){
        int rowsModify = categoryMapper.modifyPostKind(postClaimKindDTO);
        return rowsModify > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 목록
     * @return List<FaqKindDTO>
     */
    public List<FaqKindDTO> faqKindList(){
        return categoryMapper.faqKindList();
    }


    /**
     * 자주 묻는 질문 카테고리 생성
     * @param inputValue
     */
    public boolean createFaqKind(String inputValue){
        FaqKindDTO faqKindDTO = FaqKindDTO.builder()
                .faqKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.createFaqKind(faqKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 자주 묻는 질문 카테고리 수정
     * @param faqKindDTO
     */
    public boolean modifyFaqKind(FaqKindDTO faqKindDTO){
        int rowsModify = categoryMapper.modifyFaqKind(faqKindDTO);
        return rowsModify > 0;
    }


    /**
     * 문의하기 카테고리 목록
     * @return List<CategoryDto>
     */
    public List<QuestionKindDTO> questionKindList(){
        return categoryMapper.questionKindList();
    }


    /**
     * 문의하기 카테고리 생성
     * @param inputValue
     */
    public boolean createQuestionKind(String inputValue){
        QuestionKindDTO questionKindDTO = QuestionKindDTO.builder()
                .questionKindName(inputValue)
                .build();
        int rowsCreate = categoryMapper.createQuestionKind(questionKindDTO);
        return rowsCreate > 0;
    }


    /**
     * 문의하기 카테고리 수정
     * @param questionKindDTO
     */
    public boolean modifyQuestionKind(QuestionKindDTO questionKindDTO){
        int rowsModify = categoryMapper.modifyQuestionKind(questionKindDTO);
        return rowsModify > 0;
    }

    //삭제
    public boolean deleteData(String requestData){
        System.out.println(requestData);
        String[] parts = requestData.split("_");

        if (parts.length > 1) {
            String prontUnderscore = parts[0];   // faq_34 =>faq
            String afterUnderscore = parts[1];   // faq_34 =>34
            int seq = Integer.parseInt(afterUnderscore);

            if(prontUnderscore.equals("user")) {
                int rowsDeleted = categoryMapper.deleteUserKind(seq);
                return rowsDeleted > 0;
            } else if (prontUnderscore.equals("post")) {
                int rowsDeleted = categoryMapper.deletePostKind(seq);
                return rowsDeleted > 0;
            } else if (prontUnderscore.equals("faq")) {
                int rowsDeleted = categoryMapper.deleteFaqKind(seq);
                return rowsDeleted > 0;
            } else if (prontUnderscore.equals("ques")) {
                int rowsDeleted = categoryMapper.deleteQuestionKind(seq);
                return rowsDeleted > 0;
            }
        }
        return false;
    }

}
