package io.github.jk6841.kakaoenterprise.api.like;

import io.github.jk6841.kakaoenterprise.api.like.api.dto.TrendingSongsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;

    @GetMapping("/trending-songs")
    public Mono<TrendingSongsResponse> getTrendingSongs(@RequestParam(defaultValue = "10") int size) {
        return likeService.getTrendingSongs(size);
    }

    @PutMapping("/songs/{songId}/likes")
    public Mono<Void> likeSong(@PathVariable long songId, @RequestHeader("USER-ID") long userId) {
        return  likeService.likeSong(songId, userId);
    }
}
