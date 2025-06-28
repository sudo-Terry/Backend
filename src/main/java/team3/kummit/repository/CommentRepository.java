package team3.kummit.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team3.kummit.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByEmotionBandIdOrderByIdDesc(Long emotionBandId);
}
