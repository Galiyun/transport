package fun.riding4.transport.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.task")
@Data
public class TaskProperties {
    private int expireAfterSeconds;
}
