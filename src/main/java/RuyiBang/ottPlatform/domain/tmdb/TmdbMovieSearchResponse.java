package RuyiBang.ottPlatform.domain.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TmdbMovieSearchResponse {
    private int page;
    @JsonProperty("results")
    private List<MovieSearchTmdbResult> movieSearchResults;
    private int totalPages;
    private int totalResults;
}
