package io.github.jk6841.kakaoenterprise.config.r2dbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;

import java.util.List;

@Configuration
public class R2dbcConfig {

    @Bean
    R2dbcCustomConversions r2dbcCustomConversions(ObjectConverter objectConverter) {
        return new R2dbcCustomConversions(CustomConversions.StoreConversions.NONE, List.of(objectConverter));
    }
}
