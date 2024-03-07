//package com.example.shoppingmall.photo;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoField;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/test")
//@Slf4j
//public class TestApiController {
//
//    private final TestService testService;
//
////    @PostMapping("/image/V1/api")
////    public ResponseEntity<Void> imageUploadV1(@RequestParam(name = "image") MultipartFile image) throws IOException {
////
////        // 폴더 생성과 파일명 새로 부여를 위한 현재 시간 알아내기
////        LocalDateTime now = LocalDateTime.now();
////        int year = now.getYear();
////        int month = now.getMonthValue();
////        int day = now.getDayOfMonth();
////        int hour = now.getHour();
////        int minute = now.getMinute();
////        int second = now.getSecond();
////        int millis = now.get(ChronoField.MILLI_OF_SECOND);
////
////        String absolutePath = new File("/Users/USER/Desktop").getAbsolutePath() + "/"; // 파일이 저장될 절대 경로
////        String newFileName = "image" + hour + minute + second + millis; // 새로 부여한 이미지명
////        String fileExtension = '.' + image.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1"); // 정규식 이용하여 확장자만 추출
////        String path = "images/test/" + year + "/" + month + "/" + day; // 저장될 폴더 경로
////
////        try {
////            if(!image.isEmpty()) {
////                File file = new File(absolutePath + path);
////                if(!file.exists()){
////                    file.mkdirs(); // mkdir()과 다르게 상위 폴더가 없을 때 상위폴더까지 생성
////                }
////                file = new File(absolutePath + path + "/" + newFileName + fileExtension);
////                image.transferTo(file);
////
////                ImageDto imgDto = ImageDto.builder()
////                        .originImageName(image.getOriginalFilename())
////                        .imagePath(path)
////                        .imageName(newFileName + fileExtension)
////                        .build();
////
////                testService.saveImage(imgDto);
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        return ResponseEntity
////                .status(HttpStatus.CREATED)
////                .build();
////    }
//
////    @ResponseBody
//    @PostMapping("/upload/api")
//    public void uploadFiles(
//            @RequestPart(value = "imageInfo") ImageDto dto,
//            @RequestPart(value = "file") List<MultipartFile> uploadFile
//    ) {
//        log.info("update ajax post . . . ");
//
//        //현재시간 서울에 맞춰 불러오기
//        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
//        //현재시간 데이터 형식 지정해주기
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
//
//        //업로드 할 폴더 지정
//        String uploadFolder = "C:\\upload\\";
//
//        //새폴더 생성
//        File dir = new File(uploadFolder);
//
//        if (!dir.exists() || !dir.isDirectory()) {
//            dir.mkdirs();
//        }
//
//        for (MultipartFile multipartFile : uploadFile) {
//            int id = 0;
//            id = id + 1;
//            log.info("----------------");
//            log.info("Upload date : " + now.format(format));
//            log.info("Upload File Name : " + multipartFile.getOriginalFilename());
//            log.info("Upload File Size : " + multipartFile.getSize());
//
//
//            //업로드 할 파일의 이름(사용자가 등록한 파일의 이름)
//            String userFileName = multipartFile.getOriginalFilename();
//
//            //확장자명 구하기
//            String fileExtension = "";
//            fileExtension = userFileName.substring(userFileName.lastIndexOf(".") + 1);
//            log.info("extension : ." + fileExtension);
//
//            //yyyyMMdd_HHmmss 형식의 새 이름 붙혀주기
//            String uploadFileName = "";
//            uploadFileName = now.format(format) + "_" + id + "." + fileExtension;
//            log.info("only file name : " + uploadFileName);
//
//            //파일 저장
//            File fileRename = new File(uploadFolder, uploadFileName);
//            File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
//
//            boolean success = saveFile.renameTo(fileRename);
//            if (success) {
//                System.out.println("File successfully renamed");
//            } else {
//                System.out.println("File renamed fail");
//            }
//
//            log.info("upload new file name : " + saveFile);
//
//            try {
//                multipartFile.transferTo(fileRename);
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.error(e.getMessage());
//            }
//
//        }
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity upload(@RequestPart MultipartFile file) {
//        String originalFileName = file.getOriginalFilename();
//        File destination = new File("upload/dir" + originalFileName);
//        try {
//            file.transferTo(destination);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(originalFileName);
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(originalFileName);
//    }
////     @RequestMapping(value = "/board", method = RequestMethod.POST)
////    public ResponseEntity<BoardDTO> postBoard(BoardDTO board, MultipartFile file) throws Exception {
////        if ((board.getId() == null) || (board.getContents() == null) || (board.getPassword() == null) || (board.getTitle() == null)) {
////            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
////        }
////        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
////        UUID uuid = UUID.randomUUID();
////        String fileName = uuid + "_" + file.getOriginalFilename();
////        File saveFile = new File(projectPath, fileName);
////        file.transferTo(saveFile);
////
////
////        boardDAO.newBoard(board , file);
////        return new ResponseEntity<>(board, HttpStatus.OK);
////    }
//}
