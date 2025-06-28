package team3.kummit.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team3.kummit.domain.Comment;
import team3.kummit.domain.EmotionBand;
import team3.kummit.domain.Member;
import team3.kummit.domain.Song;
import team3.kummit.repository.CommentRepository;
import team3.kummit.repository.EmotionBandRepository;
import team3.kummit.repository.MemberRepository;
import team3.kummit.repository.SongRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final EmotionBandRepository emotionBandRepository;
    private final CommentRepository commentRepository;
    private final SongRepository songRepository;

    @Bean
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
                    .name("박태희")
                    .email("kim@test.com")
                    .password("password123")
                    .signUpDate(LocalDate.now().minusDays(30))
                    .bandCreateCount(2)
                    .bandJoinCount(5)
                    .likeCount(10)
                    .songAddCount(3)
                    .build();

            Member member2 = Member.builder()
                    .name("소일전")
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
                    .description("오늘 정말 좋은 일이 있었어요!")
                    .endTime(LocalDateTime.now().plusDays(7))
                    .likeCount(5)
                    .peopleCount(3)
                    .songCount(2)
                    .commentCount(3)
                    .build();

            EmotionBand band2 = EmotionBand.builder()
                    .creator(member2)
                    .creatorName(member2.getName())
                    .emotion("슬픔")
                    .description("우울한 날씨에 맞춰 슬픈 노래를 들어보세요")
                    .endTime(LocalDateTime.now().plusDays(3))
                    .likeCount(3)
                    .peopleCount(2)
                    .songCount(1)
                    .commentCount(2)
                    .build();

            EmotionBand band3 = EmotionBand.builder()
                    .creator(member1)
                    .creatorName(member1.getName())
                    .emotion("분노")
                    .description("스트레스 받는 하루였지만 음악으로 해소해요")
                    .endTime(LocalDateTime.now().plusDays(5))
                    .likeCount(2)
                    .peopleCount(1)
                    .songCount(0)
                    .commentCount(0)
                    .build();

            EmotionBand band4 = EmotionBand.builder()
                    .creator(member3)
                    .creatorName(member3.getName())
                    .emotion("평온")
                    .description("차분하고 평화로운 마음으로 음악을 감상해보세요")
                    .endTime(LocalDateTime.now().plusDays(1))
                    .likeCount(8)
                    .peopleCount(4)
                    .songCount(3)
                    .commentCount(2)
                    .build();

            EmotionBand band5 = EmotionBand.builder()
                    .creator(member3)
                    .creatorName(member3.getName())
                    .emotion("즐거움")
                    .description("오늘은 너무 즐거운 해커톤 데이")
                    .endTime(LocalDateTime.now().minusDays(1))
                    .likeCount(999)
                    .peopleCount(99)
                    .songCount(10)
                    .commentCount(0)
                    .build();

            emotionBandRepository.saveAll(Arrays.asList(band1, band2, band3, band4, band5));

            //음악
            List<Song> songs = List.of(
                    Song.builder()
                            .creator(member1)
                            .creatorName(member1.getName())
                            .emotionBand(band1)
                            .title("Girl Of My Dreams")
                            .artist("Juice WRLD, SUGA & 방탄소년단")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Video126/v4/9d/00/59/9d005967-3895-8c85-7949-4ac3e0ba9fb6/21UM1IM55795.crop.jpg/100x100bb.jpg")
                            .previewLink(null)
                            .build(),

                    Song.builder()
                            .creator(member1)
                            .creatorName(member1.getName())
                            .emotionBand(band1)
                            .title("Dreamers (Music from the FiFA World Cup Qatar 2022 Official Soundtrack)")
                            .artist("정국 & 방탄소년단")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/03/c2/c0/03c2c0d5-bb76-e143-847f-52ff6c4c808f/197089992972.png/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview112/v4/f8/50/c7/f850c704-f96a-ffde-dc3d-8d27d81bf988/mzaf_7734350799442212815.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member2)
                            .creatorName(member2.getName())
                            .emotionBand(band1)
                            .title("SUGA's Interlude")
                            .artist("Halsey, SUGA & 방탄소년단")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music125/v4/09/50/2f/09502f77-c2c9-0ae0-7e21-945a6bc80119/19UMGIM75722.rgb.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview221/v4/14/b4/b6/14b4b67c-d127-c624-1092-f29a11a552ef/mzaf_10313807235220944931.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member3)
                            .creatorName(member3.getName())
                            .emotionBand(band2)
                            .title("Love Me Again")
                            .artist("뷔")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/5e/b7/12/5eb712b8-331f-bb2d-d628-cb1d965f869f/196922581403_Cover.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview116/v4/77/4b/d8/774bd840-b98a-21ed-b813-5b6c3c0cdee2/mzaf_9188777297194981539.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member2)
                            .creatorName(member2.getName())
                            .emotionBand(band2)
                            .title("Sweet Night")
                            .artist("뷔")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music126/v4/34/d8/eb/34d8ebc6-451c-40f5-cf7a-936e36a6f778/8809717452520.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview126/v4/8c/bc/aa/8cbcaa6e-2914-ef28-8d3a-e6d9f70f5267/mzaf_16932685891180730213.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member3)
                            .creatorName(member3.getName())
                            .emotionBand(band3)
                            .title("on the street (solo version)")
                            .artist("j-hope")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music126/v4/d2/05/0f/d2050f71-95a3-1bad-daaf-b8922e0918d8/196922839696_Cover.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview122/v4/b8/7a/c0/b87ac0de-d2db-ff8c-2434-aa99d9fc2523/mzaf_10739315547877403513.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member2)
                            .creatorName(member2.getName())
                            .emotionBand(band3)
                            .title("Winter Bear")
                            .artist("뷔")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music126/v4/9a/2f/65/9a2f65a2-75a8-a0e7-7cbd-641bdddd0a64/194491211226_Cover.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview126/v4/40/ba/97/40ba97a4-2eb0-c49e-7bd4-94c6b72e3a60/mzaf_4010340737680245731.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member1)
                            .creatorName(member1.getName())
                            .emotionBand(band4)
                            .title("FRI(END)S")
                            .artist("뷔")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/cf/d0/5b/cfd05b16-1638-2b3c-b8fd-7d539bb373dc/196922869839_Cover.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview122/v4/d6/de/9b/d6de9ba5-e2ad-7220-c2b2-e8f70be99a54/mzaf_911547786802068614.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member3)
                            .creatorName(member3.getName())
                            .emotionBand(band4)
                            .title("Killin' It Girl")
                            .artist("j-hope & GloRilla")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/c8/9e/31/c89e3138-deb0-2f40-c9d7-0704ff78419b/198704553134_Cover.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview211/v4/66/ae/3f/66ae3f02-6b3b-c460-f70f-4e5ee139bc6c/mzaf_12313826851799150477.plus.aac.p.m4a")
                            .build(),

                    Song.builder()
                            .creator(member1)
                            .creatorName(member1.getName())
                            .emotionBand(band5)
                            .title("Slow Dancing")
                            .artist("뷔")
                            .albumImageLink("https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/93/51/e0/9351e0b4-a565-4232-d797-f60ca3aba82f/196922580031_Cover.jpg/100x100bb.jpg")
                            .previewLink("https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview116/v4/c0/b1/f5/c0b1f501-bfcc-d055-1b61-d773321169b8/mzaf_1938202482014025895.plus.aac.p.m4a")
                            .build()
            );


            songRepository.saveAll(songs);



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
