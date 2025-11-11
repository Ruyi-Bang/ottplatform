package RuyiBang.ottPlatform.controller;

import RuyiBang.ottPlatform.domain.MovieSearchRequest;
import RuyiBang.ottPlatform.domain.MovieSearchResponse;
import RuyiBang.ottPlatform.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
public class OttController {

    private final ChatService chatService;
    @PostMapping("public/aiMovieSearchRequest")
    public MovieSearchResponse getResponse(@RequestBody @Valid MovieSearchRequest searchRequest, HttpServletRequest request) {
        return chatService.search(searchRequest, request);
    }
    }
