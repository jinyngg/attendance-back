package com.toy4.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfig {

	@Value("${file.upload.path.windows}")
	private String fileUploadPathWindows;

	@Value("${file.upload.path.linux}")
	private String fileUploadPathLinux;


}
