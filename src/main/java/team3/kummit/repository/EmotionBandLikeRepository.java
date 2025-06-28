package team3.kummit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team3.kummit.domain.EmotionBandLike;

public interface EmotionBandLikeRepository extends JpaRepository<EmotionBandLike, Long> {
    Optional<EmotionBandLike> findByCreatorIdAndEmotionBandId(Long memberId, Long emotionBandId);
    Long countByEmotionBandId(Long emotionBandId);
}
