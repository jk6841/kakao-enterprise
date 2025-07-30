package io.github.jk6841.kakaoenterprise.api.like;

import io.github.jk6841.kakaoenterprise.api.exception.NotFoundSongException;
import io.github.jk6841.kakaoenterprise.api.like.api.dto.TrendingSongsResponse;
import io.github.jk6841.kakaoenterprise.api.like.db.LikeCustomRepository;
import io.github.jk6841.kakaoenterprise.api.like.db.LikeRepository;
import io.github.jk6841.kakaoenterprise.api.like.db.dto.LikedSong;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private LikeCustomRepository likeCustomRepository;

    @InjectMocks
    private LikeService likeService;

    @Test
    void testTrendingSongs() {
        // given
        var size = 10;
        BDDMockito
                .given(likeCustomRepository.getTrendingSongs(anyInt()))
                .willReturn(
                        Flux.just(
                                new LikedSong(30, 302L, "FAMOUS", "ALLDAY PROJECT", "2025-06-23"),
                                new LikedSong(28, 3300L, "JUMP", "BLACKPINK", "2025-07-11"),
                                new LikedSong(22, 605L, "Dirty Work", "Aespa", "2025-06-27"),
                                new LikedSong(20, 224L, "How Sweet", "NewJeans", "2024-05-24"),
                                new LikedSong(19, 903L, "Die With A Smile", "Lady Gaga & Bruno Mars", "2024-08-16")
                        )
                );

        // when, then
        StepVerifier
                .create(likeService.getTrendingSongs(size))
                .assertNext(response -> {
                    var songs = response.songs();
                    assertThat(songs.get(0)).isEqualTo(new TrendingSongsResponse.Song(30, 302L, "FAMOUS", "ALLDAY PROJECT", "2025-06-23"));
                    assertThat(songs.get(1)).isEqualTo(new TrendingSongsResponse.Song(28, 3300L, "JUMP", "BLACKPINK", "2025-07-11"));
                    assertThat(songs.get(2)).isEqualTo(new TrendingSongsResponse.Song(22, 605L, "Dirty Work", "Aespa", "2025-06-27"));
                    assertThat(songs.get(3)).isEqualTo(new TrendingSongsResponse.Song(20, 224L, "How Sweet", "NewJeans", "2024-05-24"));
                    assertThat(songs.get(4)).isEqualTo(new TrendingSongsResponse.Song(19, 903L, "Die With A Smile", "Lady Gaga & Bruno Mars", "2024-08-16"));
                })
                .verifyComplete();
    }

    @DisplayName("save 시 DuplicateKeyException이 발생해도 정상 리턴")
    @Test
    void testLikeSongDuplicateKeyException() {
        // given
        BDDMockito
                .given(likeRepository.save(any()))
                .willReturn(Mono.error(new DuplicateKeyException("")));

        // when, then
        StepVerifier
                .create(likeService.likeSong(1L, 2L))
                .verifyComplete();
    }

    @DisplayName("save 시 DataIntegrityViolationException 발생하면 NotFoundSongException throw")
    @Test
    void testLikeSongNotFoundSongException() {
        // given
        BDDMockito
                .given(likeRepository.save(any()))
                .willReturn(Mono.error(new DataIntegrityViolationException("")));

        // when, then
        StepVerifier
                .create(likeService.likeSong(1L, 2L))
                .verifyErrorMatches(throwable -> throwable instanceof NotFoundSongException);
    }
}