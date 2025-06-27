package team3.kummit.domain;


import java.time.LocalDateTime;

public class EmotionBand {

    private Long id;

    private Member creator;
    private String creatorName; // 불필요한 조인 막기
    private String emotion;
    private String comment;
    private LocalDateTime createTime;

    private Integer likeCount;
    private Integer peopleCount;
    private Integer songCount;


}
