package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.imare.services.FileService;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @GetMapping("/{fileName:.+}")
    public void getFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        fileService.addFileToResponse(fileName, response);
    }
}
