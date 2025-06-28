package team3.kummit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import team3.kummit.domain.EmotionBand;
import team3.kummit.domain.Member;
import team3.kummit.domain.Song;
import team3.kummit.dto.EmotionBandCreateRequest;
import team3.kummit.repository.MemberRepository;

@SpringBootTest
@Transactional
class EmotionBandServiceTest {

    @Autowired
    EmotionBandService emotionBandService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testCreateEmotionBand_Success() {
        // Arrange
        Member creator = Member.builder().id(1L).bandCreateCount(0)
                .songAddCount(0).build();
        EmotionBandCreateRequest.SongDto songDto = new EmotionBandCreateRequest.SongDto();
        songDto.setTitle("Test Song");
        songDto.setArtist("Test Artist");
        songDto.setAlbumImageLink("https://test.com/image.jpg");
        songDto.setPreviewLink("https://test.com/preview.mp3");

        EmotionBandCreateRequest request = new EmotionBandCreateRequest();
        request.setEmotion("Happiness");
        request.setDescription("Test Description");
        request.setSong(songDto);

        EmotionBand savedEmotionBand = EmotionBand.builder()
                .id(1L)
                .creator(creator)
                .emotion("Happiness")
                .description("Test Description")
                .creatorName("Test Creator")
                .endTime(LocalDateTime.now().plusDays(1))
                .likeCount(0)
                .peopleCount(0)
                .songCount(1)
                .commentCount(0)
                .build();

        Song savedSong = Song.builder()
                .id(1L)
                .creator(creator)
                .title("Test Song")
                .artist("Test Artist")
                .albumImageLink("https://test.com/image.jpg")
                .previewLink("https://test.com/preview.mp3")
                .createdAt(LocalDateTime.now())
                .emotionBand(savedEmotionBand)
                .build();
        // Act
        memberRepository.save(creator);
        Long resultId = emotionBandService.createEmotionBand(1L, request);

        // Assert
        assertNotNull(resultId);
        assertEquals(1L, resultId);
    }

}
