package RuyiBang.ottPlatform.domain.parser;

import RuyiBang.ottPlatform.domain.MovieSearchResponse;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

import java.util.*;

@Component
public class MovieResponseParser {

    public MovieSearchResponse parse(@NonNull String text) {
        // Expect exact blocks; be tolerant to leading/trailing spaces
        String[] lines = text.strip().split("\\R");
        String movie = null;
        String imdb = "Rating not available";
        List<MovieSearchResponse.Item> available = new ArrayList<>();
        List<MovieSearchResponse.Item> buyRent = new ArrayList<>();

        enum Section { NONE, AVAILABLE, BUYRENT }
        Section section = Section.NONE;

        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("Movie:")) {
                movie = line.substring("Movie:".length()).trim();
                section = Section.NONE;
                continue;
            }
            if (line.startsWith("IMDb Rating:")) {
                imdb = line.substring("IMDb Rating:".length()).trim();
                section = Section.NONE;
                continue;
            }
            if (line.startsWith("Available On:")) {
                section = Section.AVAILABLE;
                continue;
            }
            if (line.startsWith("Buy/Rent:")) {
                section = Section.BUYRENT;
                continue;
            }

            // Entries: "- Platform : Link"  or  "- Platform : Link : Price"
            if (line.startsWith("- ")) {
                String entry = line.substring(2).trim();
                if ("Not available".equalsIgnoreCase(entry)) {
                    // leave the list empty; caller can interpret as not available
                    continue;
                }

                if (section == Section.AVAILABLE) {
                    String[] parts = entry.split("\\s*:\\s*", 2);
                    String platform = parts.length > 0 ? parts[0] : "";
                    String link = parts.length > 1 ? parts[1] : "";
                    available.add(MovieSearchResponse.Item.builder()
                            .platform(platform)
                            .link(link)
                            .price("N/A")
                            .build());
                } else if (section == Section.BUYRENT) {
                    String[] parts = entry.split("\\s*: \\s*", 3);
                    String platform = parts.length > 0 ? parts[0] : "";
                    String link = parts.length > 1 ? parts[1] : "";
                    String price = parts.length > 2 ? parts[2] : "Price not available";
                    buyRent.add(MovieSearchResponse.Item.builder()
                            .platform(platform)
                            .link(link)
                            .price(price)
                            .build());
                }
            }
        }

        return MovieSearchResponse.builder()
                .movie(movie != null ? movie : "")
                .imdbRating(imdb)
                .availableOn(available)
                .buyRent(buyRent)
                .build();
    }
}