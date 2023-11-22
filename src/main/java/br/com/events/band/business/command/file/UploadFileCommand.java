package br.com.events.band.business.command.file;

import br.com.events.band.adapter.feign.MsFileFeign;
import br.com.events.band.data.io.file.FileDTO;
import br.com.events.band.data.io.file.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadFileCommand {

    private final MsFileFeign msFileFeign;

    public FileDTO execute(
            String origin, String originUuid, FileType fileType, MultipartFile file
    ){
        return msFileFeign.uploadFile(origin, originUuid, fileType, file);
    }
}
