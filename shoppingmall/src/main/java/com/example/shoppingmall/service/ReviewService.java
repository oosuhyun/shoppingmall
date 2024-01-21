package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.ReviewReq;
import com.example.shoppingmall.dto.ReviewRes;
import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.ProductRepository;
import com.example.shoppingmall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    //후기 생성
    public void create(ReviewReq req, String imgURL){
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(EntityExistsException::new);
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(EntityExistsException::new);
        req.setReviewImg(imgURL);
        reviewRepository.save(req.toEntity(req, member, product));
    }

    //내가 작성한 후기 조회
    public Page<ReviewRes> findByMember_Id(Long id, Pageable pageable){
        return reviewRepository.findByMember_Id(id, pageable)
                .map(ReviewRes::toDTO);
    }

    //상품 후기 조회
    public Page<ReviewRes> findByProduct_ProductId(Long id, Pageable pageable){
        return reviewRepository.findByProduct_ProductId(id, pageable)
                .map(ReviewRes::toDTO);
    }

    //후기 삭제
    public void deleteById(Long id){
        reviewRepository.deleteById(id);
    }

    //이미지 업로드
    public String uploadFile(MultipartFile multipartFile){

        //현재시간 서울에 맞춰 불러오기
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        //현재시간 데이터 형식 지정해주기
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        //업로드 할 폴더 지정
        String uploadFolder = "C:\\upload\\";

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

        return fileRename.toString();
    }

}
