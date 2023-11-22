package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.file.UploadFileCommand;
import br.com.events.band.business.use_case.band.CreateBandUseCase;
import br.com.events.band.business.command.address.CheckAddressCommand;
import br.com.events.band.business.command.band.AssignBandToPersonCommand;
import br.com.events.band.business.command.band.SaveBandCommand;
import br.com.events.band.data.io.band.request.BandRequest;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import br.com.events.band.data.io.file.FileOriginType;
import br.com.events.band.data.io.file.FileType;
import br.com.events.band.data.model.table.band.BandTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateBandUseCaseImpl implements CreateBandUseCase {

    private final CheckAddressCommand checkAddressCommand;

    private final SaveBandCommand saveBandCommand;
    private final AssignBandToPersonCommand assignBandToPersonCommand;

    private final UploadFileCommand uploadFileCommand;

    @Override
    @Transactional
    public UuidHolderDTO execute(BandRequest bandForm, MultipartFile profilePicture) {
        checkAddressCommand.execute(bandForm.getAddress());

        var toSave = new BandTable(bandForm);
        toSave = saveBandCommand.execute(toSave);

        if (Objects.nonNull(profilePicture)) {
            var savedPicture = uploadFileCommand.execute(
                    FileOriginType.BAND.name(),
                    toSave.getUuid(),
                    FileType.IMAGE,
                    profilePicture
            );

            toSave.setProfilePicture(savedPicture);
            toSave = saveBandCommand.execute(toSave);
        }

        assignBandToPersonCommand.execute(toSave.getUuid());

        return new UuidHolderDTO(toSave.getUuid());
    }
}
