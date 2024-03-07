package com.example.shoppingmall.photo;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ImageDto {

    private String originImageName;
    private String imageName;
    private String imagePath;

    public ImageTest toEntity() {
        ImageTest build = ImageTest.builder()
                .originImageName(originImageName)
                .imageName(imageName)
                .imagePath(imagePath)
                .build();
        return build;
    }

}
