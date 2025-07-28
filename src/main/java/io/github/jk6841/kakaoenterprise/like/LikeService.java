package io.github.jk6841.kakaoenterprise.like;

import io.github.jk6841.kakaoenterprise.exception.NotFoundSongException;
import io.github.jk6841.kakaoenterprise.like.api.dto.TrendingSongsResponse;
import io.github.jk6841.kakaoenterprise.like.db.LikeCustomRepository;
import io.github.jk6841.kakaoenterprise.like.db.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
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

    public Mono<Void> likeSong(long songId, long userId) {
        String now = LocalDateTime.now(ZoneOffset.UTC).toString();
        Like like = Like
                .builder()
                .songId(songId)
                .userId(userId)
                .dateTime(now)
                .build();
        return likeRepository
                .save(like)
                .onErrorResume(DuplicateKeyException.class, e -> Mono.empty())
                .onErrorResume(DataIntegrityViolationException.class, e -> Mono.error(NotFoundSongException::new))
                .then();
    }
}
