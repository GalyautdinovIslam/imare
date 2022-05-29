package ru.itis.imare.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.imare.dto.responses.AbbreviatedAccountResponse;
import ru.itis.imare.dto.responses.PhotoCardResponse;
import ru.itis.imare.dto.responses.PostResponse;
import ru.itis.imare.models.Post;

@Component
public class PostConverter implements Converter<Post, PostResponse> {

    @Override
    public PostResponse convert(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .owner(AbbreviatedAccountResponse.from(post.getOwner()))
                .createdAt(post.getCreatedAt())
                .photoCards(PhotoCardResponse.from(post.getPhotoCards()))
                .build();
    }
}
