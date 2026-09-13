package RuyiBang.ottPlatform.domain.tmdb;

import lombok.Data;

import java.util.Map;

@Data
public class TmdbProviderResponse {
    public int id;
    public Map<String, TmdbProviderResults> results;
}
