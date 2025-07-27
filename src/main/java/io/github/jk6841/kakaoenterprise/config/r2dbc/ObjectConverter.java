package io.github.jk6841.kakaoenterprise.config.r2dbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@WritingConverter
public class ObjectConverter implements Converter<Object, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convert(Object source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
