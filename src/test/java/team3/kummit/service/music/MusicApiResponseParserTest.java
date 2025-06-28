package team3.kummit.service.music;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MusicApiResponseParserTest {

    MusicApiResponseParser parser = new MusicApiResponseParser();

    @Test
    void 음악_API_파서_정상응답() {
        String json =
                "{\"resultCount\":1,\"results\":[{\"artworkUrl100\":\"https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/c8/ac/a5/c8aca553-673f-b716-4447-428b55de55e5/11..jpg/100x100bb.jpg\",\"trackName\":\"그대네요\",\"artistName\":\"성시경 & 아이유\",\"previewUrl\":\"https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview115/v4/cd/f9/0a/cdf90a95-e2f5-57c0-d2c4-f88c84bfe0cd/mzaf_2297134992439264805.plus.aac.p.m4a\"}]}";

        List<MusicResponse> musicResponses = parser.parseMusicResponse(json);

        Assertions.assertEquals(1, musicResponses.size());
    }
}