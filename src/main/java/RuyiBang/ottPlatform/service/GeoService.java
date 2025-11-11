package RuyiBang.ottPlatform.service;
import com.maxmind.geoip2.DatabaseReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

@Service
public class GeoService {

    private final DatabaseReader reader;

    public GeoService() throws IOException {
        InputStream db = getClass().getResourceAsStream("/GeoLite2-Country.mmdb");
        this.reader = new DatabaseReader.Builder(db).build();
    }

    public String getCountry(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            return reader.country(address).getCountry().getName();
        } catch (Exception e) {
            return "Unknown";
        }
    }
}