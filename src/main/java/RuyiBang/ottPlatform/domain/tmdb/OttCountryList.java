package RuyiBang.ottPlatform.domain.tmdb;

import RuyiBang.ottPlatform.domain.CountryName;
import lombok.Data;

import java.util.List;

@Data
public class OttCountryList {
    private List<CountryName> results;
}
