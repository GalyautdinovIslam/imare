package ru.itis.imare.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.imare.models.Account;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountProfileResponse {
    private String username;
    private String name;
    private String information;
    private Integer postsAmount;
    private Integer followersAmount;
    private Integer followingsAmount;

    public static AccountProfileResponse from(Account account) {
        return AccountProfileResponse.builder()
                .username(account.getUsername())
                .name(account.getName())
                .information(account.getInformation())
                .postsAmount(account.getPosts().size())
                .followersAmount(account.getFollowers().size())
                .followingsAmount(account.getFollowing().size())
                .build();
    }
}
