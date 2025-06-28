package team3.kummit.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team3.kummit.domain.Comment;
import team3.kummit.domain.EmotionBand;
import team3.kummit.domain.Member;
import team3.kummit.repository.CommentRepository;
import team3.kummit.repository.EmotionBandRepository;
import team3.kummit.repository.MemberRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final EmotionBandRepository emotionBandRepository;
    private final CommentRepository commentRepository;

    @Bean
    @Profile("dev") // 개발 환경에서만 실행
    public CommandLineRunner initData() {
        return args -> {
            log.info("더미 데이터 초기화 시작...");

            // 기존 데이터가 있으면 스킵
            if (memberRepository.count() > 0) {
                log.info("이미 데이터가 존재합니다. 더미 데이터 생성을 건너뜁니다.");
                return;
            }

            // 멤버 더미 데이터 생성
            Member member1 = Member.builder()
                    .name("김철수")
                    .email("kim@test.com")
                    .password("password123")
                    .signUpDate(LocalDate.now().minusDays(30))
                    .bandCreateCount(2)
                    .bandJoinCount(5)
                    .likeCount(10)
                    .songAddCount(3)
                    .build();

            Member member2 = Member.builder()
                    .name("이영희")
                    .email("lee@test.com")
                    .password("password456")
                    .signUpDate(LocalDate.now().minusDays(20))
                    .bandCreateCount(1)
                    .bandJoinCount(3)
                    .likeCount(7)
                    .songAddCount(2)
                    .build();

            Member member3 = Member.builder()
                    .name("박민수")
                    .email("park@test.com")
                    .password("password789")
                    .signUpDate(LocalDate.now().minusDays(10))
                    .bandCreateCount(0)
                    .bandJoinCount(2)
                    .likeCount(5)
                    .songAddCount(1)
                    .build();

            memberRepository.saveAll(Arrays.asList(member1, member2, member3));

            // 감정밴드 더미 데이터 생성
            EmotionBand band1 = EmotionBand.builder()
                    .creator(member1)
                    .creatorName(member1.getName())
                    .emotion("기쁨")
                    .comment("오늘 정말 좋은 일이 있었어요!")
                    .endTime(LocalDateTime.now().plusDays(7))
                    .likeCount(5)
                    .peopleCount(3)
                    .songCount(2)
                    .build();

            EmotionBand band2 = EmotionBand.builder()
                    .creator(member2)
                    .creatorName(member2.getName())
                    .emotion("슬픔")
                    .comment("우울한 날씨에 맞춰 슬픈 노래를 들어보세요")
                    .endTime(LocalDateTime.now().plusDays(3))
                    .likeCount(3)
                    .peopleCount(2)
                    .songCount(1)
                    .build();

            EmotionBand band3 = EmotionBand.builder()
                    .creator(member1)
                    .creatorName(member1.getName())
                    .emotion("분노")
                    .comment("스트레스 받는 하루였지만 음악으로 해소해요")
                    .endTime(LocalDateTime.now().plusDays(5))
                    .likeCount(2)
                    .peopleCount(1)
                    .songCount(0)
                    .build();

            EmotionBand band4 = EmotionBand.builder()
                    .creator(member3)
                    .creatorName(member3.getName())
                    .emotion("평온")
                    .comment("차분하고 평화로운 마음으로 음악을 감상해보세요")
                    .endTime(LocalDateTime.now().plusDays(1))
                    .likeCount(8)
                    .peopleCount(4)
                    .songCount(3)
                    .build();

            EmotionBand band5 = EmotionBand.builder()
                    .creator(member3)
                    .creatorName(member3.getName())
                    .emotion("즐거움")
                    .comment("오늘은 너무 즐거운 해커톤 데이")
                    .endTime(LocalDateTime.now().minusDays(1))
                    .likeCount(999)
                    .peopleCount(99)
                    .songCount(10)
                    .build();

            emotionBandRepository.saveAll(Arrays.asList(band1, band2, band3, band4, band5));

            // 댓글 더미 데이터 생성
            Comment comment1 = Comment.builder()
                    .emotionBand(band1)
                    .creator(member2)
                    .creatorName(member2.getName())
                    .comment("정말 기분 좋은 밴드네요! 저도 참여하고 싶어요 :)")
                    .build();

            Comment comment2 = Comment.builder()
                    .emotionBand(band1)
                    .creator(member3)
                    .creatorName(member3.getName())
                    .comment("좋은 일이 있다니 축하해요! 어떤 일이었나요?")
                    .build();

            Comment comment3 = Comment.builder()
                    .emotionBand(band1)
                    .creator(member1)
                    .creatorName(member1.getName())
                    .comment("다들 참여해주셔서 감사합니다!")
                    .build();

            Comment comment4 = Comment.builder()
                    .emotionBand(band2)
                    .creator(member1)
                    .creatorName(member1.getName())
                    .comment("쿠잇!!!!!!!!!!!!")
                    .build();

            Comment comment5 = Comment.builder()
                    .emotionBand(band2)
                    .creator(member3)
                    .creatorName(member3.getName())
                    .comment("슬픈 노래 추천해주세요 ㅜㅜ")
                    .build();

            Comment comment6 = Comment.builder()
                    .emotionBand(band4)
                    .creator(member1)
                    .creatorName(member1.getName())
                    .comment("평온한 마음으로 음악을 듣는 게 최고예요")
                    .build();

            Comment comment7 = Comment.builder()
                    .emotionBand(band4)
                    .creator(member2)
                    .creatorName(member2.getName())
                    .comment("오늘은 토요일이다...")
                    .build();

            commentRepository.saveAll(Arrays.asList(
                    comment1, comment2, comment3, comment4, comment5, comment6, comment7
            ));

            log.info("더미 데이터 초기화 완료!");
            log.info("생성된 멤버 수: {}", memberRepository.count());
            log.info("생성된 감정밴드 수: {}", emotionBandRepository.count());
            log.info("생성된 댓글 수: {}", commentRepository.count());
        };
    }
}
