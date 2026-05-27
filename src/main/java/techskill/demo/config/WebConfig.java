package techskill.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                    // Apply to all /api endpoints
                .allowedOrigins("http://localhost:5173")  // Your React frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")                      // Allow all headers (important for JSON)
                .allowCredentials(false)                  // Set to true only if using cookies/JWT with credentials
                .maxAge(3600);                            // Cache preflight response for 1 hour
    }
}