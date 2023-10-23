package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.io.UuidHolderDTO;
import br.com.events.band.domain.io._new.musician.dto.MusicianValidationDto;
import br.com.events.band.domain.io.feign.msFile.uploadFile.in.FileTypeFileClient;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.domain.type.FileOriginType;
import br.com.events.band.domain.type.MethodValidationType;
import br.com.events.band.infrastructure.feign.msFile.FileFeignClient;
import br.com.events.band.infrastructure.process.musician.MusicianMethodValidator;
import br.com.events.band.infrastructure.useCase.musician.UploadMusicianAvatarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UploadMusicianAvatarUseCaseImpl implements UploadMusicianAvatarUseCase {

    private final MusicianRepository musicianRepository;
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
                FileTypeFileClient.IMAGE,
                file
        );

        musician.setAvatarUuid(response.getUuid());
        musician.setUpdateDate(LocalDateTime.now());

        musicianRepository.save(musician);

        return new UuidHolderDTO(response.getUuid());
    }
}
