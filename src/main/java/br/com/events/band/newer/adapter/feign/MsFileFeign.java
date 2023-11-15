package br.com.events.band.newer.adapter.feign;

import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.newer.data.io.file.FileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface MsFileFeign {

    FileDTO uploadFile(
            String origin, String originUuid, FileType fileType, MultipartFile file
    );
}
