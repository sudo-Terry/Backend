package team3.kummit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class ITunesSearchService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://itunes.apple.com/search";

    public String searchMusic(String term, String country, int limit) {
        String encodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8);

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("term", encodedTerm)
                .queryParam("country", country)
                .queryParam("media", "music")
                .queryParam("limit", limit)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
