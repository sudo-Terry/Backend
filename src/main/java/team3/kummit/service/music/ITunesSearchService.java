package team3.kummit.service.music;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ITunesSearchService {

    private final RestTemplate restTemplate;
    private final MusicApiResponseParser apiResponseParser;
    private static final String BASE_URL = "https://itunes.apple.com/search";

    public List<MusicResponse> searchMusic(String keyword) {
        String encodedUserKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("term", encodedUserKeyword)
                .queryParam("country", "KR")
                .queryParam("media", "music")
                .queryParam("limit", 50)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        return apiResponseParser.parseMusicResponse(response);
    }
}
