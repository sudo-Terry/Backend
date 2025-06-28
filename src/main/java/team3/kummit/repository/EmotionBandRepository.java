package team3.kummit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team3.kummit.domain.EmotionBand;

public interface EmotionBandRepository extends JpaRepository<EmotionBand, Long> {
}
