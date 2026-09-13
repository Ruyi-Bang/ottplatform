package RuyiBang.ottPlatform;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(
        scanBasePackages = {"RuyiBang.ottPlatform", "RuyiBang.ottPlatform.entity"}
)
@EntityScan(basePackages = "RuyiBang.ottPlatform.entity")
@EnableJpaRepositories(basePackages = "RuyiBang.ottPlatform.repository")
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OttPlatform {
    public static void main(String[] args) {
        SpringApplication.run(OttPlatform.class, args);
    }
}
