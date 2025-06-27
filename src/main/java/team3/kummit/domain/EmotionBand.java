package team3.kummit.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class EmotionBand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_band_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member creator;
    private String creatorName; // 불필요한 조인 막기
    private String emotion;
    private String comment;
    private LocalDateTime createTime;

    private Integer likeCount;
    private Integer peopleCount;
    private Integer songCount;


}
