package br.com.events.band.newer.business.use_case.band.impl;

import br.com.events.band.newer.business.command.address.CheckAddressCommand;
import br.com.events.band.newer.business.command.band.FindBandCommand;
import br.com.events.band.newer.business.command.band.SaveBandCommand;
import br.com.events.band.newer.business.command.file.UploadFileCommand;
import br.com.events.band.newer.business.use_case.band.UpdateBandUseCase;
import br.com.events.band.newer.core.exception.band.BandNonExistenceException;
import br.com.events.band.newer.core.util.AuthUtil;
import br.com.events.band.newer.data.io.band.request.UpdateBandRequest;
import br.com.events.band.newer.data.io.file.FileOriginType;
import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.newer.core.exception.band.BandNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UpdateBandUseCaseImpl implements UpdateBandUseCase {

    private final FindBandCommand findBandCommand;
    private final SaveBandCommand saveBandCommand;

    private final CheckAddressCommand checkAddressCommand;

    private final UploadFileCommand uploadFileCommand;

    @Override
    public void execute(String bandUuid, UpdateBandRequest request, MultipartFile profilePicture) {
        var band = findBandCommand.byUuidAndOwnerUuid(
                bandUuid, AuthUtil.getAuthenticatedPersonUuid()
        ).orElseThrow(BandNotFoundException::new);

        if (!band.isActive()) {
            throw new BandNonExistenceException();
        }

        checkAddressCommand.execute(request.getAddress());

        band.update(request);

        if (Objects.nonNull(profilePicture)) {
            var savedPicture = uploadFileCommand.execute(
                    FileOriginType.BAND.name(),
                    bandUuid,
                    FileType.IMAGE,
                    profilePicture
            );

            band.setProfilePictureUuid(savedPicture.getUuid());
        }

        saveBandCommand.execute(band);
    }
}
