package ru.itis.imare.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.imare.models.Album;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResponse {

    private Long id;
    private String title;
    private Integer photosAmount;

    public static AlbumResponse from(Album album) {
        return AlbumResponse.builder()
                .id(album.getId())
                .title(album.getTitle())
                .photosAmount(album.getPhotoCards().size())
                .build();
    }

    public static List<AlbumResponse> from(List<Album> albums) {
        return albums.stream().map(AlbumResponse::from).collect(Collectors.toList());
    }
}
