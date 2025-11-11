package RuyiBang.ottPlatform.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieSearchRequest {
    @NotNull
    private String movieName;
    private String language;
    private String country;
}