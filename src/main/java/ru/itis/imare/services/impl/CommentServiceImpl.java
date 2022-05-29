package ru.itis.imare.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.imare.dto.requests.CommentRequest;
import ru.itis.imare.dto.responses.CommentResponse;
import ru.itis.imare.exceptions.notfound.AccountNotFoundException;
import ru.itis.imare.exceptions.notfound.CommentNotFoundException;
import ru.itis.imare.exceptions.ForbiddenRequestException;
import ru.itis.imare.exceptions.notfound.PhotoCardNotFoundException;
import ru.itis.imare.models.Account;
import ru.itis.imare.models.Comment;
import ru.itis.imare.models.PhotoCard;
import ru.itis.imare.repositories.AccountRepository;
import ru.itis.imare.repositories.CommentRepository;
import ru.itis.imare.repositories.PhotoCardRepository;
import ru.itis.imare.services.CommentService;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final AccountRepository accountRepository;
    private final PhotoCardRepository photoCardRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponse addComment(Long id, CommentRequest commentRequest, Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName())
                .orElseThrow(AccountNotFoundException::new);
        PhotoCard photoCard = photoCardRepository.findById(id).orElseThrow(PhotoCardNotFoundException::new);
        Comment comment = Comment.builder()
                .user(account)
                .photoCard(photoCard)
                .content(commentRequest.getContent())
                .build();
        return CommentResponse.from(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long id, Authentication authentication) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        if (comment.getUser().getUsername().equals(authentication.getName())
        || comment.getPhotoCard().getPost().getOwner().getUsername().equals(authentication.getName())) {
            commentRepository.delete(comment);
        } else {
            throw new ForbiddenRequestException();
        }
    }
}
