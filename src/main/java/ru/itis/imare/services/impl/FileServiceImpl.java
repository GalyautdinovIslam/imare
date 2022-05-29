package ru.itis.imare.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.imare.exceptions.notfound.FileNotFoundException;
import ru.itis.imare.models.FileInfo;
import ru.itis.imare.repositories.FileInfoRepository;
import ru.itis.imare.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    @Value("${imare.files-storage-path}")
    private String storagePath;

    private final FileInfoRepository fileInfoRepository;

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        System.out.println(fileName);
        FileInfo fileInfo = fileInfoRepository.findByStorageFileName(fileName).orElseThrow(FileNotFoundException::new);

        response.setContentType(fileInfo.getContentType());
        response.setContentLength(fileInfo.getSize().intValue());
        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");

        try {
            IOUtils.copy(new FileInputStream(storagePath + "\\" + fileInfo.getStorageFileName()),
                    response.getOutputStream());
        } catch (IOException e) {
            throw new FileNotFoundException(e);
        }
    }
}
