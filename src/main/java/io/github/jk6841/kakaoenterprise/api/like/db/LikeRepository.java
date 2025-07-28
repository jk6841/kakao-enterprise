package io.github.jk6841.kakaoenterprise.api.like.db;

import io.github.jk6841.kakaoenterprise.api.like.Like;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface LikeRepository extends R2dbcRepository<Like, Long> {
}
