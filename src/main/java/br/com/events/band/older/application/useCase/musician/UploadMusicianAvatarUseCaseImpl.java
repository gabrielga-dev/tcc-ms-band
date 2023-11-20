package br.com.events.band.older.application.useCase.musician;

import br.com.events.band.newer.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.older.domain.io.UuidHolderDTO;
import br.com.events.band.older.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.newer.adapter.repository.jpa.MusicianJpaRepository;
import br.com.events.band.newer.data.io.file.FileOriginType;
import br.com.events.band.older.domain.type.MethodValidationType;
import br.com.events.band.older.infrastructure.feign.msFile.FileFeignClient;
import br.com.events.band.older.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.older.infrastructure.useCase.musician.UploadMusicianAvatarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UploadMusicianAvatarUseCaseImpl implements UploadMusicianAvatarUseCase {

    private final MusicianJpaRepository musicianRepository;
    private final FileFeignClient fileFeignClient;

    private final MusicianMethodValidator musicianMethodValidator;

    @Override
    public UuidHolderDTO execute(String musicianUuid, MultipartFile file) {
        var musician = musicianRepository.findById(musicianUuid).orElseThrow(MusicianDoesNotExistException::new);
        var toValidate = new MusicianValidationDto(
                musician.getBand().getUuid(), MethodValidationType.UPLOAD_FILE, musicianUuid
        );
        musicianMethodValidator.callProcesses(toValidate);

        var response = fileFeignClient.uploadFile(
                FileOriginType.MUSICIAN.name(),
                musicianUuid,
                FileType.IMAGE,
                file
        );

        musician.setProfilePictureUuid(response.getUuid());
        musician.setUpdateDate(LocalDateTime.now());

        musicianRepository.save(musician);

        return new UuidHolderDTO(response.getUuid());
    }
}
