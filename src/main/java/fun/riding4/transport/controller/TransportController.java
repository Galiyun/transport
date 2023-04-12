package fun.riding4.transport.controller;


import fun.riding4.transport.model.TransportFileList;
import fun.riding4.transport.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @Author hym
 * @Date 2023-04-12 22:07
 * @Version 1.0
 * @Description
 */
@Controller
@RequestMapping("/api")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // input multiple files to use mongodb to save them, then generate a len 4 random code return
    public Mono<String> upload(@RequestPart("files") Flux<MultipartFile> files) {
        return transportService.upload(files);
    }

    @GetMapping("/files/{code}")
    public Mono<TransportFileList> getFiles(@PathVariable String code) {
        return null;
    }

    @GetMapping("/download/{code}/{filename}")
    public Mono<ResponseEntity<Resource>> download(@PathVariable String code,
                                                   @PathVariable String filename,
                                                   ServerWebExchange serverWebExchange) throws IOException {
        return null;
    }
}