package team3.kummit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import team3.kummit.domain.EmotionBandLike;

public interface EmotionBandLikeRepository extends JpaRepository<EmotionBandLike, Long> {
    Optional<EmotionBandLike> findByCreatorIdAndEmotionBandId(Long memberId, Long emotionBandId);
    Long countByEmotionBandId(Long emotionBandId);

    @Query("SELECT eb.id FROM EmotionBand eb where eb.creator.id =:memberId")
    List<Long> findEmotionBandIdListByCreator(Long memberId);
}
