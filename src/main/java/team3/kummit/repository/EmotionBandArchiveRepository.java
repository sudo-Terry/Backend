package team3.kummit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import team3.kummit.domain.EmotionBandArchive;

public interface EmotionBandArchiveRepository extends JpaRepository<EmotionBandArchive, Long> {
    Optional<EmotionBandArchive> findByCreatorIdAndEmotionBandId(Long memberId, Long emotionBandId);
    boolean existsByCreatorIdAndEmotionBandId(Long memberId, Long emotionBandId);

    @Query("SELECT eb.id FROM EmotionBand eb where eb.creator.id =:memberId")
    List<Long> findEmotionBandIdListByCreator(Long memberId);
}
