package io.github.jk6841.kakaoenterprise.api.song.db.dto;

public record YearlyArtistAlbumCount(
        Integer year,
        String artist,
        Integer albumCount
) {
}
