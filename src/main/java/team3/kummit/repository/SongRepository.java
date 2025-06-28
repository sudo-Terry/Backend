package team3.kummit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team3.kummit.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByEmotionBandIdOrderByCreatedAtDesc(Long emotionBandId);
}
