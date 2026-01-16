package com.toss.foodtruck.global.service;

import com.toss.foodtruck.global.exception.BusinessException;
import com.toss.foodtruck.global.exception.ErrorCode;
import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    // Spring Cloud AWS 3.x의 핵심 컴포넌트 (AmazonS3 대신 사용)
    private final S3Template s3Template;

    // application.yml에서 버킷 이름 주입
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    // 업로드 허용할 파일 확장자 (보안을 위해 이미지로 제한)
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(
        "image/jpeg", "image/png", "image/jpg", "image/heic"
    );

    // 파일 크기 제한 (10MB)
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    /**
     * [파일 업로드]
     * @param directory 저장할 폴더명 (예: "store", "menu")
     * @param file 프론트에서 보낸 파일 객체
     * @return 업로드된 파일의 S3 접근 URL
     */
    public String uploadFile(String directory, MultipartFile file) {
        // 1. 파일 검증 (크기, 확장자)
        validateFile(file);

        // 2. 파일명 중복 방지 (UUID 사용)
        String fileName = createUniqueFileName(directory, file.getOriginalFilename());

        try (InputStream inputStream = file.getInputStream()) {
            // 3. S3에 업로드 (Spring Cloud AWS가 자동으로 처리)
            URL url = s3Template.upload(bucket, fileName, inputStream,
                                    io.awspring.cloud.s3.ObjectMetadata.builder()
                                                                       .contentType(file.getContentType()) // 브라우저에서 바로 보기 위해 타입 설정
                                                                       .build())
                                .getURL();

            // 4. 업로드된 URL 반환
            return url.toString();

        } catch (IOException | S3Exception e) {
            log.error("S3 파일 업로드 실패: {}", e.getMessage());
            throw new BusinessException(ErrorCode.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * [파일 삭제]
     * @param fileUrl 삭제할 파일의 전체 URL
     */
    public void deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) return;

        try {
            // URL에서 실제 파일명(Key)만 추출하여 삭제 요청
            String fileName = extractFileNameFromUrl(fileUrl);
            s3Template.deleteObject(bucket, fileName);
        } catch (Exception e) {
            log.error("S3 파일 삭제 실패 (URL: {}): {}", fileUrl, e.getMessage());
            // 삭제 실패는 치명적이지 않으므로 로그만 남기고 넘어감
        }
    }

    // [내부 메서드] 고유한 파일명 생성 (폴더명/UUID.확장자)
    private String createUniqueFileName(String directory, String originalFileName) {
        String extension = extractExtension(originalFileName);
        return directory + "/" + UUID.randomUUID() + extension;
    }

    // [내부 메서드] URL에서 파일명 추출 (https://.../store/abc.jpg -> store/abc.jpg)
    private String extractFileNameFromUrl(String url) {
        try {
            // 도메인(.com/) 뒷부분부터 잘라냄
            String splitStr = ".com/";
            int index = url.indexOf(splitStr);
            if (index != -1) {
                return url.substring(index + splitStr.length());
            }
            // 도메인 형식이 다를 경우 슬래시 기준 마지막 부분 처리 (비상용)
            return url.substring(url.lastIndexOf("/") + 1);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.FILE_NOT_FOUND);
        }
    }

    // [내부 메서드] 확장자 추출
    private String extractExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new BusinessException(ErrorCode.INVALID_FILE_TYPE);
        }
    }

    // [내부 메서드] 파일 유효성 검사
    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (!ALLOWED_IMAGE_TYPES.contains(file.getContentType())) {
            throw new BusinessException(ErrorCode.INVALID_FILE_TYPE);
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new BusinessException(ErrorCode.FILE_SIZE_EXCEED);
        }
    }
}