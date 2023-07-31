package com.toy4.global.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmployeeProfileImageService {

	public static final String FILE_UPLOAD_PATH = "D:\\attendance\\images/";

	public String saveFile(Long employeeId, MultipartFile file) {
		String directoryName = employeeId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
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

		return "/images/profile/" + directoryName + newFileName;
	}

	public String getDefaultFile() {
		return "/images/default/user.png";
	}

	public void removeIfFileExists(String imagePath) {
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/profile", ""));
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
