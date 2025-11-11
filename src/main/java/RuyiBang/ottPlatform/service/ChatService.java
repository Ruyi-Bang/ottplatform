package RuyiBang.ottPlatform.service;

import RuyiBang.ottPlatform.domain.MovieSearchRequest;
import RuyiBang.ottPlatform.domain.MovieSearchResponse;
import RuyiBang.ottPlatform.domain.parser.MovieResponseParser;
import RuyiBang.ottPlatform.prompt.MoviePromptFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatModel chatModel;
    private final MovieResponseParser parser;
    private final GeoService geoService;

    public MovieSearchResponse search(MovieSearchRequest searchRequest, HttpServletRequest request) {
        String country = searchRequest.getCountry();
        if (StringUtils.isBlank(country)) {
            String ip = request.getRemoteAddr();
            country = geoService.getCountry(ip);
            if (country.equals("Unknown")) {
                country = "USA";
            }
        }
        var prompt = MoviePromptFactory.createPrompt(searchRequest.getMovieName(), searchRequest.getLanguage(), country);


        // ✅ MODERN SPRING AI CALL
        ChatResponse response = chatModel.call(prompt);

        String text = response.getResult().getOutput().getText();

        return parser.parse(text);
    }

}