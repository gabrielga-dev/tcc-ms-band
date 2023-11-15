package br.com.events.band.newer.adapter.feign.client;

import br.com.events.band.newer.adapter.feign.MsFileFeign;
import br.com.events.band.newer.adapter.feign.client.config.MyEventFeignClientConfiguration;
import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.newer.data.io.file.FileDTO;
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
public interface MsFileFeignClient extends MsFileFeign {

    @PostMapping(value = "/v1/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FileDTO uploadFile(
            @RequestParam String origin,
            @RequestParam String originUuid,
            @RequestParam FileType fileType,
            @RequestPart("file") MultipartFile file
    );
}
