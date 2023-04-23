package fun.riding4.transport.config.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final DataBufferWriter bufferWriter;

    @Autowired
    public GlobalExceptionHandler(DataBufferWriter bufferWriter) {
        this.bufferWriter = bufferWriter;
        Hooks.onErrorDropped(e -> {
            log.error("Error dropped: {}", e.getMessage());
            throw ServiceException.from(ErrorCode.INTERNAL_SERVER_ERROR);
        });
    }

    @Override
    public Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable throwable) {
        HttpStatus status;
        CommonResponse commonResponse = new CommonResponse();

        if (throwable instanceof ServiceException) {
            log.error("ServiceException:{}, code:{}, message:{}", throwable, ((ServiceException) throwable).getCode(), throwable.getMessage());
            status = ((ServiceException) throwable).getHttpStatus();
            commonResponse.setCode(((ServiceException) throwable).getCode());
            commonResponse.setMessage(throwable.getMessage());
        } else {
            log.error("Unknown Throwable:", throwable);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            commonResponse = CommonResponse.from(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return bufferWriter.write(exchange.getResponse(), commonResponse);
    }
}