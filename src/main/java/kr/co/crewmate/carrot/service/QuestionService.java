package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.dto.QuestionListResponseDTO;
import kr.co.crewmate.carrot.model.entity.File;
import kr.co.crewmate.carrot.model.entity.QuestionAnswer;
import kr.co.crewmate.carrot.model.entity.QuestionImage;
import kr.co.crewmate.carrot.model.form.QuestionAnswerCreateForm;
import kr.co.crewmate.carrot.model.form.QuestionCreateForm;
import kr.co.crewmate.carrot.repository.FileMapper;
import kr.co.crewmate.carrot.repository.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final FileMapper fileMapper;

    @Value("${diskPath.commonPath}")
    private String commonPath;

    @Value("${diskPath.questionPath}")
    private String questionPath;
    public void createQuestion (QuestionCreateForm questionCreateForm) {

        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String questionCreatedAt = dateFormat.format(nowDate);
        List<MultipartFile> questionFiles = questionCreateForm.getQuestionFiles();

        // 질문 DB삽입
        QuestionListResponseDTO newQuestion = new QuestionListResponseDTO();
//            newQuestion.setUserSeq(loginInfo.getUserSeq());
            newQuestion.setQuestionKindSeq(questionCreateForm.getQuestionKindSeq());
            newQuestion.setQuestionTitle(questionCreateForm.getQuestionTitle());
            newQuestion.setQuestionContent(questionCreateForm.getQuestionContent());
            newQuestion.setQuestionCreatedAt(questionCreatedAt);

            questionMapper.insertQuestion(newQuestion);

        try {
            createDirectory(commonPath + questionPath + "/" + dateFormat); // 업로드 경로에 폴더 생성

            // 파일 업로드 처리
            for (MultipartFile file : questionFiles) {
                if(!file.isEmpty()){
                    String storedFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    Path filePath = Path.of(commonPath+questionPath, storedFilename);
                    Files.write(filePath, file.getBytes());

                    File saveFile = new File();
                        saveFile.setFilePath(String.valueOf(filePath));
                        saveFile.setFileSize((int) file.getSize());
                        saveFile.setUserSeq(0);

                        fileMapper.insertFile(saveFile);

                    QuestionImage questionImage = new QuestionImage();
                        questionImage.setFileSeq(saveFile.getFileSeq());
                        questionImage.setQuestionSeq(newQuestion.getQuestionSeq());

                        questionMapper.insertQuestionImage(questionImage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일 업로드 경로에 폴더 없으면 폴더 생성
    private void createDirectory(String path) {
        java.io.File directory = new java.io.File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public List<QuestionListResponseDTO> retrieveQuestionListAll(){
        List<QuestionListResponseDTO> results = questionMapper.selectQuestionListAll();
        for (QuestionListResponseDTO result : results){
            if (result.getQuestionAnswerSeq() != 0){
                result.setHasAnswer("답변완료");
            } else {
                result.setHasAnswer("미답변");
            }
        }
        return results;
    }

    public int retrieveQuestionListAllCount(){
        return questionMapper.selectQuestionListAllCount();
    }

    public List<QuestionListResponseDTO> retrieveQuestionList(String questionKindSeq){
        List<QuestionListResponseDTO> results = questionMapper.selectQuestionList(questionKindSeq);
        for (QuestionListResponseDTO result : results){
            if (result.getQuestionAnswerSeq() != 0){
                result.setHasAnswer("답변완료");
            } else {
                result.setHasAnswer("미답변");
            }
        }
        return results;
    }

    public int retrieveQuestionListCount(String questionKindSeq){
        return questionMapper.selectQuestionListCount(questionKindSeq);
    }

    public QuestionListResponseDTO retrieveQuestionDetail(String questionSeq){
        QuestionListResponseDTO questionDetails = questionMapper.selectQuestionDetail(questionSeq);

            List<String> imageList = questionDetails.getFilePaths();
            List<String> filePaths = new ArrayList<>();
            for (String filePath : imageList) {
                String path = filePath.substring(filePath.indexOf("\\question"));
                filePaths.add(path);
            }
            questionDetails.setFilePaths(filePaths);

        return questionDetails;
    }

    public void createQuestionAnswer(QuestionAnswerCreateForm questionAnswerCreateForm){

        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String questionAnswerCreatedAt = dateFormat.format(nowDate);
        questionAnswerCreateForm.setQuestionAnswerCreatedAt(questionAnswerCreatedAt);

        questionMapper.insertQuestionAnswer(questionAnswerCreateForm);
    }

    public QuestionAnswer retrieveQuestionAnswer(String questionSeq){
        return questionMapper.selectQuestionAnswer(questionSeq);
    }
}
