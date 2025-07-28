package io.github.jk6841.kakaoenterprise.like;

import io.github.jk6841.kakaoenterprise.like.api.dto.TrendingSongsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;

    @RequestMapping("/trending-songs")
    public Mono<TrendingSongsResponse> getTrendingSongs(@RequestParam(defaultValue = "10") int size) {
        return likeService.getTrendingSongs(size);
    }
}
