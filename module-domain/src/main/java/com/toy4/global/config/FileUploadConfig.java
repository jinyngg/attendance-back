package com.toy4.global.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfig {

	@Value("${file.upload.path.windows}")
	private String fileUploadPathWindows;

	@Value("${file.upload.path.linux}")
	private String fileUploadPathLinux;

	public String getFileUploadPath() {
		String separator = File.separator;
		return separator.equals("\\") ? fileUploadPathWindows : fileUploadPathLinux;
	}
}
