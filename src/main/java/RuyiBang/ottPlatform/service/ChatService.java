package RuyiBang.ottPlatform.service;

import RuyiBang.ottPlatform.domain.MovieSearchRequest;
import RuyiBang.ottPlatform.domain.MovieSearchResponse;
import RuyiBang.ottPlatform.domain.parser.MovieResponseParser;
import RuyiBang.ottPlatform.prompt.MoviePromptFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {

    private final ChatModel chatModel;
    private final MovieResponseParser parser;
    private final GeoService geoService;
    private final ChatClient chatClient;

    @Value("${content.country}")
    private String country;

    ChatService(ChatModel chatModel, MovieResponseParser parser, GeoService geoService, ChatClient.Builder chatClientBuilder) {
        this.chatModel = chatModel;
        this.parser = parser;
        this.geoService = geoService;
        this.chatClient = chatClientBuilder.build();
    }
    public MovieSearchResponse ottMoviesearch(MovieSearchRequest searchRequest, HttpServletRequest request) {
        String country = searchRequest.getCountry();
        if (StringUtils.isBlank(country)) {
            String ip = request.getRemoteAddr();
            country = geoService.getCountry(ip);
            if (country.equals("Unknown")) {
                country = this.country;
            }
        }
        var prompt = MoviePromptFactory.createPrompt(searchRequest.getMovieName(), searchRequest.getLanguage(), country);


        // ✅ MODERN SPRING AI CALL
        ChatResponse response = chatModel.call(prompt);

        String text = response.getResult().getOutput().getText();

        return parser.parse(text);
    }

    public Flux<String> streamMovieNews(String movieTitle, String year) {
        String promptText = """
        Provide the latest news about the movie titled %s with release year %s.
        Include release info, trailers, cast, ratings, and any updates.
        """.formatted(movieTitle, year);

        return this.chatClient.prompt()
                .user(promptText)
                .stream().content();
    }

}