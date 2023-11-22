package br.com.events.band.business.use_case.musician;

import br.com.events.band.data.io.musician.request.MusicianRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateMusicianUseCase {

    void execute(String musicianUuid, MusicianRequest request, MultipartFile profilePicture);
}
