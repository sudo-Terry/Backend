package team3.kummit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MusicSearchConfig {

    @Bean
    public RestTemplate getResetTemplate() {

        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

}
