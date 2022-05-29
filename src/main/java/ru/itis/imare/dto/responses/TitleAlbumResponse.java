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
public class TitleAlbumResponse {
    private Long id;
    private String owner;
    private String title;

    public static TitleAlbumResponse from(Album album) {
        return TitleAlbumResponse.builder()
                .id(album.getId())
                .owner(album.getOwner().getUsername())
                .title(album.getTitle())
                .build();
    }

    public static List<TitleAlbumResponse> from(List<Album> albums) {
        return albums.stream().map(TitleAlbumResponse::from).collect(Collectors.toList());
    }
}
