package io.github.jk6841.kakaoenterprise;

import io.github.jk6841.kakaoenterprise.dataload.SongObjectService;
import io.github.jk6841.kakaoenterprise.dataload.db.DbSaveService;
import io.github.jk6841.kakaoenterprise.dataload.file.FileLineReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@SpringBootApplication
public class DataLoadApplication implements ApplicationRunner {
    private final FileLineReader fileLineReader;

    private final SongObjectService songObjectService;
    private final Scheduler objectScheduler;

    private final DbSaveService dbSaveService;
    private final Scheduler dbScheduler;

    private final int batchInsertSize;

    public DataLoadApplication(FileLineReader fileLineReader,
                               SongObjectService songObjectService,
                               Scheduler objectScheduler,
                               DbSaveService dbSaveService,
                               Scheduler dbScheduler,
                               @Value("${app.dataload.batch.size}") int batchInsertSize) {
        this.fileLineReader = fileLineReader;
        this.songObjectService = songObjectService;
        this.objectScheduler = objectScheduler;
        this.dbSaveService = dbSaveService;
        this.dbScheduler = dbScheduler;
        this.batchInsertSize = batchInsertSize;
    }

    @Override
    public void run(ApplicationArguments args) {
        Instant start = Instant.now();
        fileLineReader.readLines()
                .publishOn(objectScheduler)
                .flatMap(songObjectService::convertFileLineToSong)
                .publishOn(dbScheduler)
                .buffer(batchInsertSize)
                .flatMap(dbSaveService::insertInBatch)
                .blockLast();
        Instant end = Instant.now();
        log.info("Finished. ({} ms)", Duration.between(start, end).toMillis());
        objectScheduler.dispose();
        dbScheduler.dispose();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DataLoadApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
