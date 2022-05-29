package ru.itis.imare.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.imare.models.Account;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsernameAccountResponse {

    private String username;

    public static UsernameAccountResponse from(Account account) {
        return UsernameAccountResponse.builder()
                .username(account.getUsername())
                .build();
    }

    public static List<UsernameAccountResponse> from(List<Account> accounts) {
        return accounts.stream().map(UsernameAccountResponse::from).collect(Collectors.toList());
    }
}
