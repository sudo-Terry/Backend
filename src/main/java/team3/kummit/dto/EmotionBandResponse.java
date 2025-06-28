package team3.kummit.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team3.kummit.domain.EmotionBand;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmotionBandResponse {
    private Long id;
    private String creatorName;
    private String emotion;
    private String description;
    private LocalDateTime endTime;
    private Integer likeCount;
    private Integer peopleCount;
    private Integer songCount;
    private Integer commentCount;
    private boolean isLiked;

    public static EmotionBandResponse from(EmotionBand emotionBand, boolean isLiked) {
        return EmotionBandResponse.builder()
                .id(emotionBand.getId())
                .creatorName(emotionBand.getCreatorName())
                .emotion(emotionBand.getEmotion())
                .description(emotionBand.getDescription())
                .endTime(emotionBand.getEndTime())
                .likeCount(emotionBand.getLikeCount())
                .peopleCount(emotionBand.getPeopleCount())
                .songCount(emotionBand.getSongCount())
                .commentCount(emotionBand.getCommentCount())
                .isLiked(isLiked)
                .build();
    }
}
