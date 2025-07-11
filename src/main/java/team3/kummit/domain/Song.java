package team3.kummit.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emotion_band_id")
    private EmotionBand emotionBand;
    private String creatorName;

    private String title;
    private String artist;
    private String albumImageLink;
    private String previewLink;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
