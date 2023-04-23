package fun.riding4.transport.controller;


import fun.riding4.transport.model.TransportFileList;
import fun.riding4.transport.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @PostMapping(value = "/files/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> upload(@RequestPart("files") Flux<FilePart> files) {
        return transportService.upload(files).map(TransportFileList::getCode);
    }

    @GetMapping("/files/{code}")
    public Mono<TransportFileList> getFilesByCode(@PathVariable String code) {
        return transportService.getFiles(code);
    }

    @GetMapping("/files/download/{objectId}")
    public Mono<Void> downloadFile(@PathVariable String objectId, ServerHttpResponse response) {
        return transportService.downloadFile(response, objectId);
    }
}