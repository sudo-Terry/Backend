package team3.kummit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team3.kummit.domain.EmotionBandArchive;

public interface EmotionBandArchiveRepository extends JpaRepository<EmotionBandArchive, Long> {
    Optional<EmotionBandArchive> findByCreatorIdAndEmotionBandId(Long memberId, Long emotionBandId);
    boolean existsByCreatorIdAndEmotionBandId(Long memberId, Long emotionBandId);
}
