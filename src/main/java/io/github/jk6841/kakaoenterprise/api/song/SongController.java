package io.github.jk6841.kakaoenterprise.api.song;

import io.github.jk6841.kakaoenterprise.api.song.api.dto.AlbumStatResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class SongController {
    private final SongService songService;

    @GetMapping("/stats/albums/year-artist")
    Mono<AlbumStatResponse> getAlbumStat(@RequestParam(defaultValue = "1") int page,
                                         @Max(1000) @Min(10) @RequestParam(defaultValue = "50") int size) {
        return songService.getAlbumStat(page, size);
    }
}
