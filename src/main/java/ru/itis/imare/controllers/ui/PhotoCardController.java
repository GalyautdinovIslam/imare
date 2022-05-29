package ru.itis.imare.controllers.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.imare.dto.requests.PhotoCardRequest;
import ru.itis.imare.dto.responses.TitleAlbumResponse;
import ru.itis.imare.repositories.AccountRepository;
import ru.itis.imare.services.AlbumService;
import ru.itis.imare.services.PhotoCardService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/photos")
public class PhotoCardController {

    private final PhotoCardService photoCardService;
    private final AlbumService albumService;

    @GetMapping("/{id}")
    public String getPhotoPage(@PathVariable("id") Long id, Authentication authentication, Model model) {
        model.addAttribute("auth", authentication.getName());
        model.addAttribute("albums", albumService.getAlbumsList(authentication.getName()));
        model.addAttribute("photoId", id);
        return "photo";
    }

    @PostMapping("/{id}/update")
    public String updatePhoto(@PathVariable("id") Long id, PhotoCardRequest photoCardRequest,
                              Authentication authentication) {
        photoCardService.updatePhoto(id, photoCardRequest, authentication);
        return "redirect:/photos/" + id;
    }
}
