package io.github.jk6841.kakaoenterprise.dataload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
public class SchedulerConfig {
    @Bean
    Scheduler objectScheduler() {
        return Schedulers.parallel();
    }

    @Bean
    Scheduler dbScheduler() {
        return Schedulers.boundedElastic();
    }
}
