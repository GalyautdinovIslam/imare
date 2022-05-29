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
public class AbbreviatedAccountResponse {

    private String username;
    private String name;

    public static AbbreviatedAccountResponse from(Account account) {
        return AbbreviatedAccountResponse.builder()
                .username(account.getUsername())
                .name(account.getName())
                .build();
    }

    public static List<AbbreviatedAccountResponse> from(List<Account> accounts) {
        return accounts.stream().map(AbbreviatedAccountResponse::from).collect(Collectors.toList());
    }
}
