package io.github.jk6841.kakaoenterprise.api.song;

import io.github.jk6841.kakaoenterprise.api.song.api.dto.AlbumStatItemDto;
import io.github.jk6841.kakaoenterprise.api.song.db.dto.YearlyArtistAlbumCount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {
    @Mock
    private SongCustomRepository songCustomRepository;

    @InjectMocks
    private SongService songService;

    @Test
    void testGetAlbumStat() {
        // given
        int page = 3;
        int size = 4;
        int total = 3000;
        BDDMockito
                .given(songCustomRepository.getYearlyArtistAlbumCountPage(anyInt(), anyInt()))
                .willReturn(Flux.just(
                        new YearlyArtistAlbumCount(2023, "NewJeans", 1),
                        new YearlyArtistAlbumCount(2023, "NMIXX", 3),
                        new YearlyArtistAlbumCount(2020, "Aespa", 2),
                        new YearlyArtistAlbumCount(2025, "IVE", 1)
                ));
        BDDMockito
                .given(songCustomRepository.getTotalYearlyArtistAlbumCount())
                .willReturn(Mono.just(total));

        // when, then
        StepVerifier
                .create(songService.getAlbumStat(page, size))
                .assertNext(response -> {
                    var items = response.items();
                    assertThat(items.size()).isEqualTo(4);
                    assertThat(items).containsExactly(
                            new AlbumStatItemDto(2023, "NewJeans", 1),
                            new AlbumStatItemDto(2023, "NMIXX", 3),
                            new AlbumStatItemDto(2020, "Aespa", 2),
                            new AlbumStatItemDto(2025, "IVE", 1)
                    );

                    var paging = response.paging();
                    assertThat(paging.page()).isEqualTo(page);
                    assertThat(paging.size()).isEqualTo(size);
                    assertThat(paging.total()).isEqualTo(total);
                })
                .verifyComplete();

    }
}