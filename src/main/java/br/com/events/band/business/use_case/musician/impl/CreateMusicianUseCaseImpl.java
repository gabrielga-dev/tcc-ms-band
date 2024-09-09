package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.file.UploadFileCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.musician.CreateMusicianUseCase;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandNotFoundException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianBelowAgeException;
import br.com.events.band.core.exception.musician.MusicianExistsException;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.file.FileOriginType;
import br.com.events.band.data.io.file.FileType;
import br.com.events.band.data.io.musician.request.MusicianRequest;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    private final AuthService authService;
    private final FindMusicianCommand findMusicianCommand;
    private final FindBandCommand findBandCommand;
    private final SaveMusicianCommand saveMusicianCommand;
    private final FindMusicianTypeCommand findMusicianTypeCommand;
    private final UploadFileCommand uploadFileCommand;
    private final AssociateCreatedMusicianUseCaseImpl associateCreatedMusicianUseCaseImpl;

    @Override
    public UuidHolderDTO execute(MultipartFile profilePicture, MusicianRequest request, String bandUuid) {
        var band = findBandCommand.byUuid(bandUuid).orElseThrow(BandNotFoundException::new);

        if (!band.isActive()) {
            throw new BandNonExistenceException();
        } else if (!band.getOwnerUuid().equals(authService.getAuthenticatedPersonUuid())) {
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

        var types = findMusicianTypeCommand.byUuid(request.getTypeUuids());
        var toSave = new MusicianTable(request, band, types);
        toSave = saveMusicianCommand.execute(toSave);

        if (Objects.nonNull(profilePicture)) {
            var savedPicture = uploadFileCommand.execute(
                    FileOriginType.MUSICIAN.name(),
                    toSave.getUuid(),
                    FileType.IMAGE,
                    profilePicture
            );

            toSave.setProfilePicture(savedPicture);
            toSave = saveMusicianCommand.execute(toSave);
        }

        associateCreatedMusicianUseCaseImpl.execute(bandUuid, toSave.getCpf());

        return new UuidHolderDTO(toSave.getUuid());
    }
}
