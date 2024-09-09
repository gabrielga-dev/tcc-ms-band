package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.file.UploadFileCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.musician.FindPersonMusicianCommand;
import br.com.events.band.business.command.musician.SaveMusicianCommand;
import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.business.use_case.musician.UpdateMusicianUseCase;
import br.com.events.band.core.exception.ResourceAlreadyDeactivatedException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.core.exception.musician.MusicianHasAnAccountException;
import br.com.events.band.data.io.file.FileOriginType;
import br.com.events.band.data.io.file.FileType;
import br.com.events.band.data.io.musician.request.MusicianRequest;
import br.com.events.band.data.io.musician.request.UpdateMusicianRequestMessage;
import br.com.events.band.data.io.person.response.PersonResponse;
import br.com.events.band.data.model.table.musician.MusicianTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UpdateMusicianUseCaseImpl implements UpdateMusicianUseCase {

    private final AuthService authService;
    private final FindMusicianCommand findMusicianCommand;
    private final FindPersonMusicianCommand findPersonMusicianCommand;
    private final FindMusicianTypeCommand findMusicianTypeCommand;
    private final UploadFileCommand uploadFileCommand;
    private final SaveMusicianCommand saveMusicianCommand;

    @Override
    public void execute(String musicianUuid, MusicianRequest request, MultipartFile profilePicture) {
        var musician = findMusicianCommand.byUuid(musicianUuid).orElseThrow(MusicianDoesNotExistException::new);

        findPersonMusicianCommand.byCpf(musician.getCpf())
                .ifPresentOrElse(
                        this::isSamePerson,
                        () -> this.isMusiciansBandOwner(musician)
                );

        musician.update(request);

        if (Objects.nonNull(profilePicture)) {
            var savedPicture = uploadFileCommand.execute(
                    FileOriginType.MUSICIAN.name(),
                    musicianUuid,
                    FileType.IMAGE,
                    profilePicture
            );

            musician.setProfilePicture(savedPicture);
        }

        var types = findMusicianTypeCommand.byUuid(request.getTypeUuids());
        musician.updateTypes(types);

        saveMusicianCommand.execute(musician);
    }

    private void isSamePerson(PersonResponse person) {
        if (!authService.getAuthenticatedPerson().getCpf().equals(person.getCpf())) {
            throw new MusicianHasAnAccountException();
        }
    }

    private void isMusiciansBandOwner(MusicianTable musician) {
        if (!authService.getAuthenticatedPersonUuid().equals(musician.getBandThatInserted().getOwnerUuid())) {
            throw new BandOwnerException();
        } else if (!musician.isActive()) {
            throw new ResourceAlreadyDeactivatedException();
        }
    }

    @Override
    public void execute(UpdateMusicianRequestMessage request) {
        var musician = findMusicianCommand.byCpf(request.getCpf())
                .orElseThrow(MusicianDoesNotExistException::new);

        musician.setFirstName(request.getFirstName());
        musician.setLastName(request.getLastName());
        musician.setEmail(request.getEmail());
        musician.setPersonUuid(request.getPersonUuid());
        musician.setUpdateDate(LocalDateTime.now());

        saveMusicianCommand.execute(musician);
    }
}
