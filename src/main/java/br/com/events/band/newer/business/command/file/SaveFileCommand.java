package br.com.events.band.newer.business.command.file;

import br.com.events.band.newer.adapter.feign.MsFileFeign;
import br.com.events.band.newer.data.io.file.FileDTO;
import br.com.events.band.newer.data.io.file.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class SaveFileCommand {

    private final MsFileFeign msFileFeign;

    public FileDTO execute(
            String origin, String originUuid, FileType fileType, MultipartFile file
    ){
        return msFileFeign.uploadFile(origin, originUuid, fileType, file);
    }
}
