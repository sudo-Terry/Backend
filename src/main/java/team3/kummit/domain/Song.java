package team3.kummit.domain;

import java.time.LocalDateTime;

public class Song {

    private Long id;

    private EmotionBand emotionBand;
    private Member creator;
    private String creatorName;

    private String title;
    private String artist;
    private String albumImageLink;
    private LocalDateTime createTime;

}
