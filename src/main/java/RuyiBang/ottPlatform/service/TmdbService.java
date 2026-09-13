package RuyiBang.ottPlatform.service;

import RuyiBang.ottPlatform.domain.MovieSearchRequest;
import RuyiBang.ottPlatform.domain.MovieSearchResponse;
import RuyiBang.ottPlatform.domain.tmdb.TmdbMovieSearchResponse;
import RuyiBang.ottPlatform.domain.tmdb.TmdbProviderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class TmdbService {

    @Value("${tmdb.auth.token}")
    private String authToken;

    @Value("${tmdb.url}")
    private String tmdbUrl;

    @Value("${content.country}")
    private String country;

    private WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(tmdbUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authToken)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public TmdbMovieSearchResponse callTmdbApi(MovieSearchRequest searchRequest) {
        return webClient().get()
                .uri(uri -> uri.path("/search/movie")
                .queryParam("query", searchRequest.getMovieName())
                        .build()).retrieve().bodyToMono(TmdbMovieSearchResponse.class).block();

    }

    public TmdbProviderResponse movieProviders(MovieSearchRequest searchRequest, String movieId) {
        return webClient().get()
                .uri(uri -> uri.path("/movie/{id}/watch/providers")

                        .build(movieId))
                .retrieve().bodyToMono(TmdbProviderResponse.class).block();
    }

    public MovieSearchResponse mapToResponse(TmdbProviderResponse providers, TmdbMovieSearchResponse movieSearch, String country, String id) {


        return MovieSearchResponse.builder()

               .build();

    }
}
