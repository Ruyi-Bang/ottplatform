package RuyiBang.ottPlatform.domain;

import lombok.*;

import lombok.Data;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchResponse {
    private String movie;                 // e.g., "Kantara"
    private String imdbRating;            // e.g., "8.4/10" or "Rating not available"
    private List<Item> availableOn;       // subscription streaming
    private List<Item> buyRent;           // buy/rent with price

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String platform;          // e.g., "Amazon Prime Video"
        private String link;              // direct watch page
        private String price;             // e.g., "Rent $3.99" or "Price not available"
    }
}