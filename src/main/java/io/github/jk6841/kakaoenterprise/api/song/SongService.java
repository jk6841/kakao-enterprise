package io.github.jk6841.kakaoenterprise.api.song;

import io.github.jk6841.kakaoenterprise.api.song.api.dto.AlbumStatItemDto;
import io.github.jk6841.kakaoenterprise.api.song.api.dto.AlbumStatResponse;
import io.github.jk6841.kakaoenterprise.common.paging.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SongService {
    private final SongCustomRepository songCustomRepository;

    Mono<AlbumStatResponse> getAlbumStat(int page, int size) {
        return songCustomRepository.getYearlyArtistAlbumCountPage(page, size)
                .map(yearlyArtistAlbumCount -> new AlbumStatItemDto(yearlyArtistAlbumCount.year(), yearlyArtistAlbumCount.artist(), yearlyArtistAlbumCount.albumCount()))
                .collectList()
                .zipWith(songCustomRepository.getTotalYearlyArtistAlbumCount().map(total -> new Paging(page, size, total)))
                .map(tuple -> {
                    List<AlbumStatItemDto> albumStatItemDtos = tuple.getT1();
                    Paging paging = tuple.getT2();
                    return new AlbumStatResponse(albumStatItemDtos, paging);
                });
    }
}
