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
public class AccountsPageResponse {
    private List<AbbreviatedAccountResponse> accounts;
    private Integer totalPage;
}
