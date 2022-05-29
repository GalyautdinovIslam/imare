package ru.itis.imare.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.imare.models.Post;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbbreviatedPostResponse {

    private Long id;
    private String photoStorageFileName;

    public static AbbreviatedPostResponse from(Post post) {
        return AbbreviatedPostResponse.builder()
                .id(post.getId())
                .photoStorageFileName(post.getPhotoCards().get(0).getPhoto().getStorageFileName())
                .build();
    }

    public static List<AbbreviatedPostResponse> from(List<Post> posts) {
        return posts.stream().map(AbbreviatedPostResponse::from).collect(Collectors.toList());
    }
}
