package fun.riding4.transport.controller;


import fun.riding4.transport.model.TransportFileList;
import fun.riding4.transport.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author hym
 * @Date 2023-04-12 22:07
 * @Version 1.0
 * @Description
 */
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

    @GetMapping("/test")
    public String download() {
//        return Mono.just("download");
        return "download";
    }
}