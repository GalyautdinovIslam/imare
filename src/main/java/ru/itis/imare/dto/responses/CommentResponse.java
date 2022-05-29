package ru.itis.imare.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.imare.models.Comment;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private UsernameAccountResponse user;
    private String content;
    private Instant createdAt;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .user(UsernameAccountResponse.from(comment.getUser()))
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static List<CommentResponse> from(List<Comment> comments) {
        return comments.stream().map(CommentResponse::from).collect(Collectors.toList());
    }
}
