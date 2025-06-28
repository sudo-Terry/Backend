package team3.kummit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team3.kummit.domain.*;
import team3.kummit.exception.ResourceNotFoundException;
import team3.kummit.repository.EmotionBandLikeRepository;
import team3.kummit.repository.EmotionBandRepository;
import team3.kummit.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class EmotionBandLikeService {
    private final EmotionBandLikeRepository likeRepository;
    private final EmotionBandRepository bandRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean toggleLike(Long emotionBandId, Long memberId) {
        EmotionBand band = bandRepository.findById(emotionBandId)
                .orElseThrow(() -> new ResourceNotFoundException("감정밴드를 찾을 수 없습니다."));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다."));

        return likeRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId)
                .map(like -> {
                    likeRepository.delete(like);
                    EmotionBand updatedBand = band.updateLikeCount(band.getLikeCount() - 1);
                    bandRepository.save(updatedBand);
                    return false;
                })
                .orElseGet(() -> {
                    likeRepository.save(EmotionBandLike.builder()
                            .creator(member)
                            .emotionBand(band)
                            .build());
                    EmotionBand updatedBand = band.updateLikeCount(band.getLikeCount() + 1);
                    bandRepository.save(updatedBand);
                    return true;
                });
    }

    @Transactional(readOnly = true)
    public boolean isLiked(Long emotionBandId, Long memberId) {
        return likeRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId).isPresent();
    }
}
