package com.example.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PhotoService {

    //이미지 업로드
    public String uploadFile(MultipartFile multipartFile){

        //현재시간 서울에 맞춰 불러오기
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        //현재시간 데이터 형식 지정해주기
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        //업로드 할 폴더 지정
        String uploadFolder = "C:\\Users\\USER\\springboot\\shoppingmall\\shoppingmall\\src\\main\\frontend\\public\\images";

        //새폴더 생성
        File dir = new File(uploadFolder);

        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }

        //업로드 할 파일의 이름(사용자가 등록한 파일의 이름)
        String userFileName = multipartFile.getOriginalFilename();

        //확장자명 구하기
        String fileExtension = "";
        fileExtension = userFileName.substring(userFileName.lastIndexOf(".") + 1);

        //yyyyMMdd_HHmmss 형식의 새 이름 붙혀주기
        String uploadFileName = "";
        uploadFileName = now.format(format) + "." + fileExtension;

        //파일 저장
        File fileRename = new File(uploadFolder, uploadFileName);
        File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

        boolean success = saveFile.renameTo(fileRename);
        if (success) {
            System.out.println("File successfully renamed");
        } else {
            System.out.println("File renamed fail");
        }

        try {
            multipartFile.transferTo(fileRename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String imgUrl = "/images/"+uploadFileName;

        return imgUrl.toString();
    }

}
