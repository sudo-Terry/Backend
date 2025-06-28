package team3.kummit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import team3.kummit.domain.EmotionBand;
import team3.kummit.domain.EmotionBandLike;
import team3.kummit.domain.Member;
import team3.kummit.exception.ResourceNotFoundException;
import team3.kummit.repository.EmotionBandLikeRepository;
import team3.kummit.repository.EmotionBandRepository;
import team3.kummit.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class EmotionBandLikeServiceTest {

    @Mock
    private EmotionBandLikeRepository likeRepository;

    @Mock
    private EmotionBandRepository bandRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private EmotionBandLikeService emotionBandLikeService;

    private Member testMember;
    private EmotionBand testEmotionBand;
    private EmotionBandLike testLike;

    @BeforeEach
    void setUp() {
        testMember = Member.builder()
                .id(1L)
                .name("테스트유저")
                .email("test@test.com")
                .build();

        testEmotionBand = EmotionBand.builder()
                .id(1L)
                .creator(testMember)
                .creatorName("테스트유저")
                .emotion("기쁨")
                .description("테스트 감정밴드")
                .endTime(LocalDateTime.now().plusDays(1))
                .likeCount(5)
                .peopleCount(3)
                .songCount(2)
                .commentCount(3)
                .build();

        testLike = EmotionBandLike.builder()
                .id(1L)
                .creator(testMember)
                .emotionBand(testEmotionBand)
                .build();
    }

    @Test
    @DisplayName("좋아요 추가 성공")
    void toggleLike_AddLike_Success() {
        Long emotionBandId = 1L;
        Long memberId = 1L;

        when(bandRepository.findById(emotionBandId))
                .thenReturn(Optional.of(testEmotionBand));
        when(memberRepository.findById(memberId))
                .thenReturn(Optional.of(testMember));
        when(likeRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId))
                .thenReturn(Optional.empty());
        when(likeRepository.save(any(EmotionBandLike.class)))
                .thenReturn(testLike);
        when(bandRepository.save(any(EmotionBand.class)))
                .thenReturn(testEmotionBand.toBuilder().likeCount(6).build());
        when(memberRepository.save(any(Member.class)))
                .thenReturn(testMember.toBuilder().likeCount(11).build());

        boolean result = emotionBandLikeService.toggleLike(emotionBandId, memberId);

        assertThat(result).isTrue();
        verify(likeRepository).save(any(EmotionBandLike.class));
        verify(bandRepository).save(any(EmotionBand.class));
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("좋아요 해제 성공")
    void toggleLike_RemoveLike_Success() {
        Long emotionBandId = 1L;
        Long memberId = 1L;

        when(bandRepository.findById(emotionBandId))
                .thenReturn(Optional.of(testEmotionBand));
        when(memberRepository.findById(memberId))
                .thenReturn(Optional.of(testMember));
        when(likeRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId))
                .thenReturn(Optional.of(testLike));
        when(bandRepository.save(any(EmotionBand.class)))
                .thenReturn(testEmotionBand.toBuilder().likeCount(4).build());
        when(memberRepository.save(any(Member.class)))
                .thenReturn(testMember.toBuilder().likeCount(9).build());

        boolean result = emotionBandLikeService.toggleLike(emotionBandId, memberId);

        assertThat(result).isFalse();
        verify(likeRepository).delete(testLike);
        verify(bandRepository).save(any(EmotionBand.class));
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("존재하지 않는 감정밴드에 좋아요 시 예외 발생")
    void toggleLike_EmotionBandNotFound_ThrowsException() {
        Long emotionBandId = 999L;
        Long memberId = 1L;

        when(bandRepository.findById(emotionBandId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> emotionBandLikeService.toggleLike(emotionBandId, memberId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("감정밴드를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("존재하지 않는 사용자로 좋아요 시 예외 발생")
    void toggleLike_MemberNotFound_ThrowsException() {
        Long emotionBandId = 1L;
        Long memberId = 999L;

        when(bandRepository.findById(emotionBandId))
                .thenReturn(Optional.of(testEmotionBand));
        when(memberRepository.findById(memberId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> emotionBandLikeService.toggleLike(emotionBandId, memberId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("사용자를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("좋아요 여부 조회 - 좋아요한 경우")
    void isLiked_WhenLiked_ReturnsTrue() {
        Long emotionBandId = 1L;
        Long memberId = 1L;

        when(likeRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId))
                .thenReturn(Optional.of(testLike));

        boolean result = emotionBandLikeService.isLiked(emotionBandId, memberId);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("좋아요 여부 조회 - 좋아요하지 않은 경우")
    void isLiked_WhenNotLiked_ReturnsFalse() {
        Long emotionBandId = 1L;
        Long memberId = 1L;

        when(likeRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId))
                .thenReturn(Optional.empty());

        boolean result = emotionBandLikeService.isLiked(emotionBandId, memberId);

        assertThat(result).isFalse();
    }
}
