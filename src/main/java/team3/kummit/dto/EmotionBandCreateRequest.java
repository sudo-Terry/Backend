package team3.kummit.dto;


import lombok.Data;

@Data
public class EmotionBandCreateRequest {

    private String emotion;
    private String description;
    private SongDto song;

    @Data
    public static class SongDto{
        private String title;
        private String artist;
        private String albumImageLink;
        private String previewLink;
    }


}
