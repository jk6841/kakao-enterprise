package io.github.jk6841.kakaoenterprise.like.db;

import io.github.jk6841.kakaoenterprise.like.Like;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface LikeRepository extends R2dbcRepository<Like, Long> {
}
