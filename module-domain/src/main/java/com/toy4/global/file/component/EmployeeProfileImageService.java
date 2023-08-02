package com.toy4.global.file.component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.toy4.global.file.config.FileUploadConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class EmployeeProfileImageService {
    
    private final FileUploadConfig fileUploadConfig;
    
    // TODO 파일 저장을 위한 Dir 이름 고민
    public String saveFile(MultipartFile file) {
//        String directoryName = employeeId + "_" + System.currentTimeMillis() + "/";
        String directoryName = System.currentTimeMillis() + "/";
        String filePath = fileUploadConfig.getFileUploadPath() + directoryName;
        File directory = new File(filePath);
        if (!directory.mkdir()) {
            return null;
        }
        String originalFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + "_" +originalFileName;
        try {
            Path savePath = Paths.get(filePath + newFileName);
            file.transferTo(savePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return "/images/profile/" + directoryName + newFileName;
        return "/images/" + directoryName + newFileName;
    }
    public String getDefaultFile() {
        return "/images/default/user.png";
    }
    public void removeIfFileExists(String imagePath) {
        Path path = Paths.get(fileUploadConfig.getFileUploadPath() + imagePath.replace("/images/profile", ""));
        if (Files.exists(path)) {
            removeFile(path);
            path = path.getParent();
            if (Files.exists(path)) {
                removeFile(path);
            }
        }
    }
    private void removeFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}