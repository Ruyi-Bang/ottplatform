package RuyiBang.ottPlatform.domain.tmdb;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovieSearchTmdbResult {
    private boolean adult;
    private String backdropPath;
    private List<Integer> genreIds;
    private int id;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private float popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private boolean softcore;
    private boolean video;
    private float voteAverage;
    private int voteCount;
}
