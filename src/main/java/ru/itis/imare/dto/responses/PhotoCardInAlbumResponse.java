package ru.itis.imare.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.imare.models.PhotoCard;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoCardInAlbumResponse {

    private Long id;
    private String filename;

    public static PhotoCardInAlbumResponse from(PhotoCard photoCard) {
        return PhotoCardInAlbumResponse.builder()
                .id(photoCard.getId())
                .filename(photoCard.getPhoto().getStorageFileName())
                .build();
    }

    public static List<PhotoCardInAlbumResponse> from(List<PhotoCard> photoCards) {
        return photoCards.stream().map(PhotoCardInAlbumResponse::from).collect(Collectors.toList());
    }
}
