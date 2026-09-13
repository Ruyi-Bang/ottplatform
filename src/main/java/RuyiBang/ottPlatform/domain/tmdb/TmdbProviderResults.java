package RuyiBang.ottPlatform.domain.tmdb;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class TmdbProviderResults {
    private String link;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TmdbFlatRate> flatrate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TmdbFlatRate> buy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TmdbFlatRate> rent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TmdbFlatRate> ads;
}
