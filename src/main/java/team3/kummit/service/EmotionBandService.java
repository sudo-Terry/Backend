package team3.kummit.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team3.kummit.domain.EmotionBand;
import team3.kummit.dto.CommentResponse;
import team3.kummit.dto.EmotionBandDetailResponse;
import team3.kummit.dto.EmotionBandListResponse;
import team3.kummit.dto.EmotionBandResponse;
import team3.kummit.dto.SongResponse;
import team3.kummit.exception.ResourceNotFoundException;
import team3.kummit.repository.EmotionBandRepository;
import team3.kummit.repository.SongRepository;

@Service
@RequiredArgsConstructor
public class EmotionBandService {
    private final EmotionBandRepository emotionBandRepository;
    private final EmotionBandLikeService emotionBandLikeService;
    private final CommentService commentService;
    private final SongRepository songRepository;

    @Transactional(readOnly = true)
    public EmotionBandListResponse getEmotionBandLists(Long memberId) {
        LocalDateTime currentTime = LocalDateTime.now();

        // 인기 밴드 조회 (좋아요 수 기준 상위 3개)
        List<EmotionBand> popularBands = emotionBandRepository.findTop3ByLikeCountAndEndTimeAfter(currentTime);
        List<EmotionBandResponse> popularBandResponses = popularBands.stream()
                .limit(3) // 최대 3개만
                .map(band -> {
                    boolean isLiked = memberId != null && emotionBandLikeService.isLiked(band.getId(), memberId);
                    return EmotionBandResponse.from(band, isLiked);
                })
                .collect(Collectors.toList());

        // 전체 밴드 조회 (최신순 상위 10개)
        List<EmotionBand> allBands = emotionBandRepository.findTop10ByEndTimeDescAndEndTimeAfter(currentTime);
        List<EmotionBandResponse> allBandResponses = allBands.stream()
                .limit(10) // 최대 10개만
                .map(band -> {
                    boolean isLiked = memberId != null && emotionBandLikeService.isLiked(band.getId(), memberId);
                    return EmotionBandResponse.from(band, isLiked);
                })
                .collect(Collectors.toList());

        return EmotionBandListResponse.builder()
                .popularBands(popularBandResponses)
                .allBands(allBandResponses)
                .build();
    }

    @Transactional(readOnly = true)
    public EmotionBandResponse getEmotionBand(Long emotionBandId, Long memberId) {
        EmotionBand band = emotionBandRepository.findById(emotionBandId)
                .orElseThrow(() -> new ResourceNotFoundException("감정밴드를 찾을 수 없습니다."));

        boolean isLiked = memberId != null && emotionBandLikeService.isLiked(emotionBandId, memberId);
        return EmotionBandResponse.from(band, isLiked);
    }

    @Transactional(readOnly = true)
    public EmotionBandDetailResponse getEmotionBandDetail(Long emotionBandId) {
        // 감정밴드 조회
        EmotionBand emotionBand = emotionBandRepository.findById(emotionBandId)
                .orElseThrow(() -> new ResourceNotFoundException("감정밴드를 찾을 수 없습니다."));

        // 음악 목록 조회
        List<SongResponse> songs = songRepository.findByEmotionBandIdOrderByCreatedAtDesc(emotionBandId)
                .stream()
                .map(SongResponse::from)
                .collect(Collectors.toList());

        // 댓글 목록 조회
        List<CommentResponse> comments = commentService.getComments(emotionBandId);

        return EmotionBandDetailResponse.from(emotionBand, songs, comments);
    }
}
