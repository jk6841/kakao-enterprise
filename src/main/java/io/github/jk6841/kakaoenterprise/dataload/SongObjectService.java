package io.github.jk6841.kakaoenterprise.dataload;

import io.github.jk6841.kakaoenterprise.dataload.file.FileLineDeserializer;
import io.github.jk6841.kakaoenterprise.common.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SongObjectService {
    private final FileLineDeserializer fileLineDeserializer;
    private final SongFactory songFactory;

    public Mono<Song> convertFileLineToSong(String fileLine) {
        return fileLineDeserializer.deserialize(fileLine).map(songFactory::create);
    }
}
