package fun.riding4.transport.config.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;

    public static CommonResponse from(ErrorCode errorCode) {
        return new CommonResponse(errorCode.getCode(), errorCode.getMessage(), null);
    }

}

