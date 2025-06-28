package team3.kummit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team3.kummit.domain.EmotionBand;
import team3.kummit.domain.EmotionBandArchive;
import team3.kummit.domain.Member;
import team3.kummit.dto.EmotionBandArchiveResponse;
import team3.kummit.exception.ResourceNotFoundException;
import team3.kummit.repository.EmotionBandArchiveRepository;
import team3.kummit.repository.EmotionBandRepository;
import team3.kummit.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class EmotionBandArchiveService {
    private final EmotionBandArchiveRepository archiveRepository;
    private final EmotionBandRepository emotionBandRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public EmotionBandArchiveResponse toggleArchive(Long emotionBandId, Long memberId) {
        // emotionBandId 검증
        EmotionBand emotionBand = emotionBandRepository.findById(emotionBandId)
                .orElseThrow(() -> new ResourceNotFoundException("감정밴드를 찾을 수 없습니다."));

        // memberId 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다."));

        // 보관 상태값
        boolean isArchived = archiveRepository.existsByCreatorIdAndEmotionBandId(memberId, emotionBandId);

        if (isArchived) {
            // 보관 해제 toggle
            archiveRepository.findByCreatorIdAndEmotionBandId(memberId, emotionBandId)
                    .ifPresent(archiveRepository::delete);
            return EmotionBandArchiveResponse.of(false, "보관이 해제되었습니다.");
        } else {
            // 보관 추가 toggle
            EmotionBandArchive archive = EmotionBandArchive.builder()
                    .creator(member)
                    .emotionBand(emotionBand)
                    .build();
            archiveRepository.save(archive);
            return EmotionBandArchiveResponse.of(true, "보관되었습니다.");
        }
    }

    @Transactional(readOnly = true)
    public boolean isArchived(Long emotionBandId, Long memberId) {
        return archiveRepository.existsByCreatorIdAndEmotionBandId(memberId, emotionBandId);
    }
}
