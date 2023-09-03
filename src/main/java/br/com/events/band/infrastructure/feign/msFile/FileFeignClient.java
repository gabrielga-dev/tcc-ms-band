package br.com.events.band.infrastructure.feign.msFile;

import br.com.events.band.application.config.feign.MyEventFeignClientConfiguration;
import br.com.events.band.domain.io.feign.msFile.uploadFile.in.FileTypeFileClient;
import br.com.events.band.domain.io.feign.msFile.uploadFile.out.UploadFileFileClientResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "file-ms-file",
        url = "${feign.client.ms.file.url}",
        configuration = MyEventFeignClientConfiguration.class
)
public interface FileFeignClient {

    @PostMapping(value = "/v1/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadFileFileClientResult uploadFile(
            @RequestParam String origin,
            @RequestParam String originUuid,
            @RequestParam FileTypeFileClient fileType,
            @RequestPart("file") MultipartFile file
    );
}
