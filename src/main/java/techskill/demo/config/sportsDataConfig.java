package techskill.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class sportsDataConfig{

    @Value("${sportsdata.mma.base-url}")//pulls data from the application.properties into java
    private String baseUrl;

    @Value("${sportsdata.mma.api-key}")
    private String apiKey;

    @Bean // Tells Spring: "build this object once and share it across the whole app"
    public RestClient mmaRestClient() {
        return RestClient.builder()
            // Every request will start from this base URL automatically
            .baseUrl(baseUrl)
            
            // This header is how SportsData.io authenticates you.
            // Every single request will automatically include it — you don't
            // have to remember to add it manually each time.
            .defaultHeader("Ocp-Apim-Subscription-Key", apiKey)
            
            // Tell the API we want JSON back, not XML
            .defaultHeader("Accept", "application/json")
            .build();
    }
}