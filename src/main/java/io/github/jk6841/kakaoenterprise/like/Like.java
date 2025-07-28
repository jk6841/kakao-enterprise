package io.github.jk6841.kakaoenterprise.like;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@EqualsAndHashCode
@Getter
@ToString
@Table(name = "llike")
public class Like {
    @Id
    private Long id;
    private Long songId;
    private Long userId;
    private String dateTime;
}
