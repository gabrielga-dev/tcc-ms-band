package br.com.events.band.adapter.feign;

import br.com.events.band.data.io.file.FileDTO;
import br.com.events.band.data.io.file.FileType;
import org.springframework.web.multipart.MultipartFile;

public interface MsFileFeign {

    FileDTO uploadFile(
            String origin, String originUuid, FileType fileType, MultipartFile file
    );
}
