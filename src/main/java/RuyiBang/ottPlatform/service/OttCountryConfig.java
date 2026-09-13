package RuyiBang.ottPlatform.service;

import RuyiBang.ottPlatform.domain.CountryName;
import RuyiBang.ottPlatform.domain.tmdb.OttCountryList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
@Data
@RequiredArgsConstructor
public class OttCountryConfig {
    private OttCountryList countries;

    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init(){
        try (InputStream input = getClass().getResourceAsStream("/tmdbOttRegionAvailability.json")) {
            if (input == null) {
                throw new IllegalStateException("tmdbOttRegionAvailability.json not found in classpath");
            }
            countries = objectMapper.readValue(input, new TypeReference<>() {});
            log.info("Loaded {} OTT country configurations", countries.getResults().size());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load tmdbOttRegionAvailability.json", e);
        }
    }

    public CountryName findByCode(String code) {
        return countries.getResults().stream()
                .filter(c -> c.getIso_3166_1().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

    }
    public CountryName findByCountryName(String countryName) {
        return countries.getResults().stream()
                .filter(c -> c.getEnglish_name().equalsIgnoreCase(countryName))
                .findFirst()
                .orElse(null);

    }
}
