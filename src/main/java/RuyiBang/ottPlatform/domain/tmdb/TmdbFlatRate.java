package RuyiBang.ottPlatform.domain.tmdb;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TmdbFlatRate {
    private String logoPath;
    private int providerId;
    private String providerName;
    private int displayPriority;
}
