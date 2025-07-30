package io.github.jk6841.kakaoenterprise.common.paging;

public record Paging(
        int page,
        int size,
        long total
) {
}
