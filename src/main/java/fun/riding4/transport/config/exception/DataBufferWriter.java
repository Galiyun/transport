package fun.riding4.transport.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.riding4.transport.config.JacksonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author alina.cui
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DataBufferWriter {
    private static final ObjectMapper OBJECT_MAPPER = JacksonConfiguration.defaultObjectMapper();

    public <T> Mono<Void> write(ServerHttpResponse httpResponse, T object) {
        return httpResponse
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = httpResponse.bufferFactory();
                    try {
                        return bufferFactory.wrap(OBJECT_MAPPER.writeValueAsBytes(object));
                    } catch (Exception ex) {
                        log.warn("Fail to write response body dataBuffer in DataBufferWriter.", ex);
                        return bufferFactory.wrap(new byte[0]);
                    }
                }));
    }
}
