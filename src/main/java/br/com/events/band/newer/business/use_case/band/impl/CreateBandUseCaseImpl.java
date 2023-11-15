package br.com.events.band.newer.business.use_case.band.impl;

import br.com.events.band.newer.business.command.band.AssignBandToPersonCommand;
import br.com.events.band.newer.business.use_case.band.CreateBandUseCase;
import br.com.events.band.newer.business.command.address.CheckAddressCommand;
import br.com.events.band.newer.business.command.band.SaveBandCommand;
import br.com.events.band.newer.business.command.file.SaveFileCommand;
import br.com.events.band.newer.data.io.band.request.BandRequest;
import br.com.events.band.newer.data.io.file.FileType;
import br.com.events.band.newer.data.table.BandTable;
import br.com.events.band.older.domain.io.UuidHolderDTO;
import br.com.events.band.newer.data.io.file.FileOriginType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateBandUseCaseImpl implements CreateBandUseCase {

    private final CheckAddressCommand checkAddressCommand;

    private final SaveBandCommand saveBandCommand;
    private final AssignBandToPersonCommand assignBandToPersonCommand;

    private final SaveFileCommand saveFileCommand;

    @Override
    @Transactional
    public UuidHolderDTO execute(BandRequest bandForm, MultipartFile profilePicture) {
        checkAddressCommand.execute(bandForm.getAddress());

        var toSave = new BandTable(bandForm);
        toSave = saveBandCommand.execute(toSave);

        var savedPicture = saveFileCommand.execute(
                FileOriginType.BAND.name(),
                toSave.getUuid(),
                FileType.IMAGE,
                profilePicture
        );

        toSave.setProfilePictureUuid(savedPicture.getUuid());
        toSave = saveBandCommand.execute(toSave);

        assignBandToPersonCommand.execute(toSave.getUuid());

        return new UuidHolderDTO(toSave.getUuid());
    }
}
