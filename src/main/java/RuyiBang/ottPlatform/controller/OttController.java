package RuyiBang.ottPlatform.controller;

import RuyiBang.ottPlatform.domain.MovieSearchRequest;
import RuyiBang.ottPlatform.domain.MovieSearchResponse;
import RuyiBang.ottPlatform.domain.tmdb.TmdbMovieSearchResponse;
import RuyiBang.ottPlatform.domain.tmdb.TmdbProviderResponse;
import RuyiBang.ottPlatform.service.ChatService;
import RuyiBang.ottPlatform.service.TmdbService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class OttController {

    private final ChatService chatService;

    private final TmdbService tmdbService;

    @PostMapping("public/aiMovieSearchRequest")
    @PreAuthorize("hasRole('ROLE_PRM_USER')")
    public MovieSearchResponse getAiResponse(@RequestBody @Valid MovieSearchRequest searchRequest, HttpServletRequest request) {
        return chatService.ottMoviesearch(searchRequest, request);
    }

    @PostMapping("public/aiMovieSearchNews")
    @PreAuthorize("hasRole('ROLE_PRM_USER')")
    public Flux<String> getAiNewsResponse(@RequestBody @Valid MovieSearchRequest searchRequest, @RequestParam String year) {
        return chatService.streamMovieNews(searchRequest.getMovieName(), year);
    }

    @PostMapping("public/tmdb/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    public TmdbMovieSearchResponse callTmdbSearchApi(@RequestBody @Valid MovieSearchRequest searchRequest) {
        return tmdbService.callTmdbApi(searchRequest);
    }

    @PostMapping("public/tmdb/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public TmdbProviderResponse callTmdbProviderApi(@RequestBody @Valid MovieSearchRequest searchRequest, @PathVariable String id) {
        return tmdbService.movieProviders(searchRequest, id);
    }
    }
