package io.github.jk6841.kakaoenterprise.api.song.api.dto;

import io.github.jk6841.kakaoenterprise.common.paging.Paging;

import java.util.List;

public record AlbumStatResponse(
        List<AlbumStatItemDto> items,
        Paging paging
) {
}
