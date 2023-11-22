package br.com.events.band.business.use_case.musician;

import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.musician.request.MusicianRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * This interface dictates that is needed a {@link MusicianRequest} object to create, and it will return
 *  a {@link UuidHolderDTO} object
 * a new musician
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateMusicianUseCase {

    UuidHolderDTO execute(MultipartFile profilePicture, MusicianRequest request, String bandUuid);
}
