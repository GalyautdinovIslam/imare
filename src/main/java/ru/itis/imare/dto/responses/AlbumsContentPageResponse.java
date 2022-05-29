package ru.itis.imare.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumsContentPageResponse {

    private String owner;
    private String title;
    private List<PhotoCardInAlbumResponse> photos;
    private Integer totalPage;

}
