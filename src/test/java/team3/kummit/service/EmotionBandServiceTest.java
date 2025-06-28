package team3.kummit.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team3.kummit.domain.EmotionBand;
import team3.kummit.domain.Member;
import team3.kummit.domain.Song;
import team3.kummit.dto.EmotionBandCreateRequest;
import team3.kummit.repository.EmotionBandRepository;
import team3.kummit.repository.SongRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class EmotionBandServiceTest {

    @Mock
    private EmotionBandRepository emotionBandRepository;

    @Mock
    private SongRepository songRepository;

    @Mock
    private EmotionBandLikeService emotionBandLikeService;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private EmotionBandService emotionBandService;

    public EmotionBandServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the createEmotionBand method by simulating valid input.
     * Ensures that the EmotionBand and Song entities are successfully persisted
     * and that a valid ID is returned.
     */
    @Test
    void testCreateEmotionBand_Success() {
        // Arrange
        Member creator = Member.builder().id(1L).build();
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

        when(emotionBandRepository.save(any(EmotionBand.class))).thenReturn(savedEmotionBand);
        when(songRepository.save(any(Song.class))).thenReturn(savedSong);

        // Act
        Long resultId = emotionBandService.createEmotionBand(creator, request);

        // Assert
        assertNotNull(resultId);
        assertEquals(1L, resultId);
        verify(emotionBandRepository, times(1)).save(any(EmotionBand.class));
        verify(songRepository, times(1)).save(any(Song.class));
    }

    /**
     * Tests the createEmotionBand method with invalid inputs.
     * Verifies that the method throws a NullPointerException when input data is incomplete.
     */
    @Test
    void testCreateEmotionBand_InvalidInput() {
        // Arrange
        Member creator = Member.builder().id(1L).build();
        EmotionBandCreateRequest invalidRequest = new EmotionBandCreateRequest();

        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> emotionBandService.createEmotionBand(creator, invalidRequest));
        verify(emotionBandRepository, never()).save(any(EmotionBand.class));
        verify(songRepository, never()).save(any(Song.class));
    }
}