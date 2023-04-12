package fun.riding4.transport.service;

import fun.riding4.transport.model.TransportFile;
import fun.riding4.transport.model.TransportFileList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Author hym
 * @Date 2023-04-12 22:09
 * @Version 1.0
 * @Description
 */
@Service
@Slf4j
public class TransportService {
    private final GridFsTemplate gridFsTemplate;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TransportService(GridFsTemplate gridFsTemplate, MongoTemplate mongoTemplate) {
        this.gridFsTemplate = gridFsTemplate;
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<String> upload(Flux<MultipartFile> files) {
        return files.doOnNext(file -> {
            // use gridFsTemplate to save file
            try {
                var objectId = gridFsTemplate.store(file.getInputStream(), Objects.requireNonNull(file.getOriginalFilename()));
                objectId.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                // todo return error message
            }
        }).then(Mono.just("success"));

    }

}
