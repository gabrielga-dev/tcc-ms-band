package br.com.events.band.domain.io.musician.uploadAvatar.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class UploadMusicianAvatarRequest {

    private String musicianUuid;
    private MultipartFile file;
}
