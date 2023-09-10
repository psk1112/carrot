package kr.co.crewmate.carrot.service;

import kr.co.crewmate.carrot.model.entity.File;
import kr.co.crewmate.carrot.model.form.QuestionCreateForm;
import kr.co.crewmate.carrot.repository.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;
    @Value("${diskPath.commonPath}")
    private String commonPath;

    @Value("${diskPath.questionPath}")
    private String questionPath;
    public void createQuestion (QuestionCreateForm questionCreateForm){

//        Date nowDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
//        String faqCreatedAt = dateFormat.format(nowDate);
//        List<MultipartFile> imageFiles = questionCreateForm.getUploadFiles();
//
//        createDirectory(commonPath+questionPath); // 업로드 경로에 폴더 생성
//
//        // 파일 업로드 처리
//        String storedFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//        Path filePath = Path.of(uploadPath, storedFilename);
//        Files.write(filePath, file.getBytes());
//
//        for (MultipartFile file : imageFiles) {
//            File files = File.builder()
//                    .fileSize()
//                    .filePath()
//                    .userSeq()
//                    .build();
//        }
    }

    // 파일 업로드 경로에 폴더 없으면 폴더 생성
    private void createDirectory(String path) {
        java.io.File directory = new java.io.File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
