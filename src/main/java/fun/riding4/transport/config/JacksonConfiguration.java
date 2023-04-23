package fun.riding4.transport.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.ZoneOffset;
import java.util.TimeZone;

@Configuration
@NoArgsConstructor
public class JacksonConfiguration {

    public static ObjectMapper defaultObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        configureObjectMapperBuilder(builder);
        return builder.build();
    }

    public static ObjectMapper ignoreNullObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        configureObjectMapperBuilder(builder);
        ObjectMapper mapper = builder.build();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    private static void configureObjectMapperBuilder(Jackson2ObjectMapperBuilder builder) {
        builder.timeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        builder.serializerByType(Long.class, ToStringSerializer.instance);
        builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
        builder.serializationInclusion(JsonInclude.Include.ALWAYS);
    }

    @Bean
    @ConditionalOnClass({Jackson2ObjectMapperBuilder.class})
    public Jackson2ObjectMapperBuilderCustomizer getJackson2ObjectMapperBuilderCustomizer() {
        return JacksonConfiguration::configureObjectMapperBuilder;
    }
}