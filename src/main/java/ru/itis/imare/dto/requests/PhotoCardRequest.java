package ru.itis.imare.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoCardRequest {
    @Min(value = -90)
    @Max(value = 90)
    private Float latitude;
    @Min(value = -180)
    @Max(value = 180)
    private Float longitude;
    private String usernames;
}
