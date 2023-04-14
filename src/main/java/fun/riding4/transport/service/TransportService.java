package fun.riding4.transport.service;

import fun.riding4.transport.model.TransportFile;
import fun.riding4.transport.model.TransportFileList;
import fun.riding4.transport.repository.TransportFileListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
@Slf4j
public class TransportService {
    private final ReactiveGridFsTemplate gridFsTemplate;
    private final TransportFileListRepository transportFileListRepository;

    private static final Random RANDOM = new Random();
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyz0123456789";

    public TransportService(ReactiveGridFsTemplate gridFsTemplate, TransportFileListRepository transportFileListRepository) {
        this.gridFsTemplate = gridFsTemplate;
        this.transportFileListRepository = transportFileListRepository;
    }

    public Mono<TransportFileList> upload(Flux<FilePart> fileParts) {
        return fileParts.flatMap(filePart -> this.gridFsTemplate.store(filePart.content(), filePart.filename())
                        .map(objectId -> TransportFile.builder()
                                .name(filePart.filename())
                                .objectId(objectId.toHexString())
                                .build()))
                .collectList()
                .map(transportFiles -> TransportFileList.builder()
                        .code(generateRandomCode(4))
                        .files(transportFiles)
                        .build())
                .flatMap(transportFileListRepository::save)
                .doOnError(e -> log.error("upload error", e));
    }

    public Mono<TransportFileList> getFiles(String code) {
        return transportFileListRepository.findById(code);
    }

    private static String generateRandomCode(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int index = RANDOM.nextInt(CHARSET.length());
            char randomChar = CHARSET.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }


}
