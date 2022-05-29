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
public class PhotoCardResponse {

    private Long id;
    private AbbreviatedAccountResponse owner;
    private String fileName;
    private Float latitude;
    private Float longitude;
    private List<AbbreviatedAccountResponse> tags;
    private List<CommentResponse> comments;

    public static PhotoCardResponse from(PhotoCard photoCard) {
        return PhotoCardResponse.builder()
                .id(photoCard.getId())
                .owner(AbbreviatedAccountResponse.from(photoCard.getPost().getOwner()))
                .fileName(photoCard.getPhoto().getStorageFileName())
                .latitude(photoCard.getLatitude())
                .longitude(photoCard.getLongitude())
                .tags(AbbreviatedAccountResponse.from(photoCard.getTags()))
                .comments(CommentResponse.from(photoCard.getComments()))
                .build();
    }

    public static List<PhotoCardResponse> from(List<PhotoCard> photoCards) {
        return photoCards.stream().map(PhotoCardResponse::from).collect(Collectors.toList());
    }
}
