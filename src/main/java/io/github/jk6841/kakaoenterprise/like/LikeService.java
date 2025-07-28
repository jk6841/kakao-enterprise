package io.github.jk6841.kakaoenterprise.like;

import io.github.jk6841.kakaoenterprise.like.api.dto.TrendingSongsResponse;
import io.github.jk6841.kakaoenterprise.like.db.LikeCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeCustomRepository likeCustomRepository;

    public Mono<TrendingSongsResponse> getTrendingSongs(int size) {
        return likeCustomRepository.getTrendingSongs(size)
                .map(likedSong -> new TrendingSongsResponse.Song(
                        likedSong.likes(),
                        likedSong.id(),
                        likedSong.title(),
                        likedSong.artist(),
                        likedSong.releaseDate()
                ))
                .collectList()
                .map(TrendingSongsResponse::new);
    }
}
