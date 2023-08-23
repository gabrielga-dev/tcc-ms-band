package br.com.events.band.application.useCase.musician;

import br.com.events.band.application.process.exception.MusicianDoesNotExistException;
import br.com.events.band.domain.io.feign.msFile.uploadFile.in.FileTypeFileClient;
import br.com.events.band.domain.io.musician.uploadAvatar.UploadMusicianAvatarTranslator;
import br.com.events.band.domain.io.musician.uploadAvatar.in.UploadMusicianAvatarRequest;
import br.com.events.band.domain.io.musician.uploadAvatar.out.UploadMusicianAvatarResult;
import br.com.events.band.domain.repository.MusicianRepository;
import br.com.events.band.domain.type.FileOriginType;
import br.com.events.band.infrastructure.feign.msFile.FileFeignClient;
import br.com.events.band.infrastructure.process.musician.uploadAvatar.UploadMusicianAvatarValidationCaller;
import br.com.events.band.infrastructure.useCase.musician.UploadMusicianAvatarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UploadMusicianAvatarUseCaseImpl implements UploadMusicianAvatarUseCase {

    private final UploadMusicianAvatarValidationCaller uploadMusicianAvatarValidationCaller;
    private final MusicianRepository musicianRepository;
    private final FileFeignClient fileFeignClient;

    @Override
    public UploadMusicianAvatarResult execute(UploadMusicianAvatarRequest param) {
        uploadMusicianAvatarValidationCaller.callProcesses(param);

        var response = fileFeignClient.uploadFile(
                FileOriginType.MUSICIAN.name(),
                param.getMusicianUuid(),
                FileTypeFileClient.IMAGE,
                param.getFile()
        ).getBody();

        var result = UploadMusicianAvatarTranslator.from(response);

        var musician = musicianRepository.findById(param.getMusicianUuid())
                        .orElseThrow(MusicianDoesNotExistException::new);

        musician.setAvatarUuid(result.getFileUuid());
        musician.setUpdateDate(LocalDateTime.now());

        musicianRepository.save(musician);

        return result;
    }
}
