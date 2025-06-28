package team3.kummit.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import team3.kummit.domain.EmotionBand;

public interface EmotionBandRepository extends JpaRepository<EmotionBand, Long> {

    // 현재 시간이 endTime을 지나지 않은 밴드들 중 좋아요 수 기준 상위 3개
    @Query("SELECT e FROM EmotionBand e WHERE e.endTime > :currentTime ORDER BY e.likeCount DESC")
    List<EmotionBand> findTop3ByLikeCountAndEndTimeAfter(@Param("currentTime") LocalDateTime currentTime);

    // 현재 시간이 endTime을 지나지 않은 밴드들 중 endTime 기준 최신순 상위 10개
    @Query("SELECT e FROM EmotionBand e WHERE e.endTime > :currentTime ORDER BY e.endTime DESC")
    List<EmotionBand> findTop10ByEndTimeDescAndEndTimeAfter(@Param("currentTime") LocalDateTime currentTime);
}
