package ru.itis.imare.services;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    void addFileToResponse(String fileName, HttpServletResponse response);
}
