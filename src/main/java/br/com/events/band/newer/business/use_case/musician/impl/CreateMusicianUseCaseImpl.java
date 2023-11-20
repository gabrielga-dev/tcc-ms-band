package br.com.events.band.newer.business.use_case.musician.impl;

import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.command.file.UploadFileCommand;
import br.com.events.band.newer.business.command.musician.FindMusicianCommand;
import br.com.events.band.newer.business.command.musician.SaveMusicianCommand;
import br.com.events.band.newer.business.use_case.musician.CreateMusicianUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.exception.band.BandNotFoundException;
import br.com.events.band.newer.core.exception.band.BandOwnerException;
import br.com.events.band.newer.core.exception.musician.MusicianBelowAgeException;
import br.com.events.band.newer.core.exception.musician.MusicianExistsException;
import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.commom.UuidHolderDTO;
import br.com.events.band.newer.data.io.file.FileOriginType;
import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.newer.data.io.musician.request.MusicianRequest;
import br.com.events.band.newer.data.model.table.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Objects;

/**
 * This class implements the feature that creates new musicians
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateMusicianUseCaseImpl implements CreateMusicianUseCase {

    @Value("${minimun.musician.age}")
    private Integer minimumAge;

    private final FindMusicianCommand findMusicianCommand;
    private final FindBandCommand findBandCommand;
    private final SaveMusicianCommand saveMusicianCommand;
    private final UploadFileCommand uploadFileCommand;

    @Override
    @Transactional
    public UuidHolderDTO execute(MultipartFile profilePicture, MusicianRequest request, String bandUuid) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNotFoundException::new);

        if (!band.isActive()) {
            throw new BandNonExistenceException();
        }
        if (!band.getOwnerUuid().equals(AuthUtil.getAuthenticatedPersonUuid())) {
            throw new BandOwnerException();
        }

        findMusicianCommand.byCpf(request.getCpf()).ifPresent(
                m -> {
                    throw new MusicianExistsException();
                }
        );

        if (request.getAge() < minimumAge) {
            throw new MusicianBelowAgeException(request.getAge(), minimumAge);
        }

        var toSave = new MusicianTable(request);
        toSave = saveMusicianCommand.execute(toSave);

        if (Objects.nonNull(profilePicture)) {
            var savedPicture = uploadFileCommand.execute(
                    FileOriginType.MUSICIAN.name(),
                    toSave.getUuid(),
                    FileType.IMAGE,
                    profilePicture
            );

            toSave.setProfilePictureUuid(savedPicture.getUuid());
            toSave = saveMusicianCommand.execute(toSave);
        }

        return new UuidHolderDTO(toSave.getUuid());
    }
}
