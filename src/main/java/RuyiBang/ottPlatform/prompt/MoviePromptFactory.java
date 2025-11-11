package RuyiBang.ottPlatform.prompt;

import lombok.experimental.UtilityClass;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;

import java.util.Map;

@UtilityClass
public class MoviePromptFactory {

    private static final String TEMPLATE = """
            You are an assistant that provides accurate streaming availability, buy/rent links, pricing, 
            and IMDb rating for movies.

            INPUT:
            - Movie Name: {movie}
            - Language: {language}
            - Country: {country}

            TASK:
            Using your best reliable global OTT knowledge,
            return ONLY the following structured information.
            Do NOT add explanations, notes, or extra text.

            OUTPUT FORMAT:
            Movie: <movie name>
            IMDb Rating: <rating or "Not available">
            Available On: 
              - <OTT Platform Name> : <Direct Link> 
            Buy/Rent:
              - <Platform Name> : <Direct Link> : <Price Information>

            RULES:
            1. If a platform offers subscription streaming → list it under "Available On".
            2. If a platform offers renting or purchase → list it under "Buy/Rent".
            3. Include direct, official watch page links for each platform.
            4. Include pricing when known (e.g., "Rent $3.99", "Buy $14.99").
            5. If IMDb rating cannot be confirmed, return "Not available".
            6. If streaming or buy/rent is not available, return:
               - Available On: Not available
               - Buy/Rent: Not available
            7. Keep the structure EXACTLY as specified.
            8. Do NOT guess prices or ratings.
            """;

    public static Prompt createPrompt(String movie, String language, String country) {
        PromptTemplate pt = new PromptTemplate(TEMPLATE);
        return pt.create(Map.of("movie", movie, "language", language, "country", country), OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.2)
                .maxTokens(400)
                .build());
    }
}