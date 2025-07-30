package io.github.jk6841.kakaoenterprise.api.song;

import io.github.jk6841.kakaoenterprise.ApiApplication;
import io.github.jk6841.kakaoenterprise.api.song.api.dto.AlbumStatItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureWebTestClient
class SongControllerTest {
    @MockitoBean
    private SongService songService;

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("query param의 size의 default 값은 50")
    @Test
    void testGetAlbumStatImplicitSize() {
        // given
        BDDMockito
                .given(songService.getAlbumStat(anyInt(), anyInt()))
                .willReturn(Mono.empty());

        // when
        webTestClient
                .get()
                .uri("/stats/albums/year-artist?page=1")
                .exchange();

        // then
        verify(songService).getAlbumStat(1, 50);
    }

    @DisplayName("query param의 page의 default 값은 1")
    @Test
    void testGetAlbumStatImplicitPage() {
        // given
        BDDMockito
                .given(songService.getAlbumStat(anyInt(), anyInt()))
                .willReturn(Mono.empty());

        // when
        webTestClient
                .get()
                .uri("/stats/albums/year-artist?size=30")
                .exchange();

        // then
        verify(songService).getAlbumStat(1, 30);
    }

    @DisplayName("query param의 size 제한 초과된 경우 HTTP 400 리턴")
    @Test
    void testGetAlbumStatBadRequestTooLargeSize() {
        webTestClient
                .get()
                .uri("/stats/albums/year-artist?page=1&size=10000")
                .exchange()
                .expectStatus().isBadRequest();
    }
}