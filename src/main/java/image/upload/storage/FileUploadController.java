package image.upload.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.logging.Logger;


@RestController
public class FileUploadController {

    private final Logger logger = Logger.getLogger(FileUploadController.class.getName());

    // `MultipartFile`가 없으면 null 반환
    @PostMapping("/upload")
    public ResponseEntity<String> uploadData(@RequestParam("file")MultipartFile file) throws Exception {

        // file 파라미터가 null인 경우 예외 처리
        if (file == null) {
            throw new RuntimeException("파일이 선택되지 않았습니다.");
        }

        // 객체 ( 예) java.io.FileInputStream@7092a46d )
        InputStream inputStream = file.getInputStream();

        // 파일 이름 ( 예) memo.jpg )
        String originalName = file.getOriginalFilename();

        // 파라미터 이름 ( 예) file )
        String name = file.getName();

        // 파일 유형 ( 예) image/jpeg )
        String contentType = file.getContentType();

        // 파일 사이즈 ( 예) 231574 )
        long size = file.getSize();

        logger.info("inputStream: " + inputStream);
        logger.info("originalName: " + originalName);
        logger.info("name: " + name);
        logger.info("contentType: " + contentType);
        logger.info("size: " + size);

        return new ResponseEntity<String>(originalName, HttpStatus.OK);
    }
}
