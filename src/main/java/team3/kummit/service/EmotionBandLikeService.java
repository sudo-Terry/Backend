package team3.kummit.service;

import java.util.List;

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

                    // likeCount 감소
                    Integer currentBandLikeCount = band.getLikeCount() != null ? band.getLikeCount() : 0;
                    Integer currentMemberLikeCount = member.getLikeCount() != null ? member.getLikeCount() : 0;

                    EmotionBand updatedBand = band.toBuilder()
                            .id(band.getId())
                            .creator(band.getCreator())
                            .creatorName(band.getCreatorName())
                            .emotion(band.getEmotion())
                            .description(band.getDescription())
                            .endTime(band.getEndTime())
                            .likeCount(Math.max(0, currentBandLikeCount - 1))
                            .peopleCount(band.getPeopleCount())
                            .songCount(band.getSongCount())
                            .commentCount(band.getCommentCount())
                            .build();

                    Member updatedMember = member.toBuilder()
                            .likeCount(Math.max(0, currentMemberLikeCount - 1))
                            .build();

                    bandRepository.save(updatedBand);
                    memberRepository.save(updatedMember);

                    return false;
                })
                .orElseGet(() -> {
                    likeRepository.save(EmotionBandLike.builder()
                            .creator(member)
                            .emotionBand(band)
                            .build());

                    // likeCount 증가
                    Integer currentBandLikeCount = band.getLikeCount() != null ? band.getLikeCount() : 0;
                    Integer currentMemberLikeCount = member.getLikeCount() != null ? member.getLikeCount() : 0;

                    EmotionBand updatedBand = band.toBuilder()
                            .id(band.getId())
                            .creator(band.getCreator())
                            .creatorName(band.getCreatorName())
                            .emotion(band.getEmotion())
                            .description(band.getDescription())
                            .endTime(band.getEndTime())
                            .likeCount(currentBandLikeCount + 1)
                            .peopleCount(band.getPeopleCount())
                            .songCount(band.getSongCount())
                            .commentCount(band.getCommentCount())
                            .build();

                    Member updatedMember = member.toBuilder()
                            .likeCount(currentMemberLikeCount + 1)
                            .build();

                    bandRepository.save(updatedBand);
                    memberRepository.save(updatedMember);

                    return true;
                });
    }

    @Transactional(readOnly = true)
    public boolean isLiked(Long emotionBandId, Long memberId) {
        return likeRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId).isPresent();
    }

    public List<Long> findEmotionBandListByMemberId(Long memberId) {
        return bandRepository.findEmotionBandIdListByCreator(memberId);
    }
}
