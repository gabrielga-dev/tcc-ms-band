package br.com.events.band.business.use_case.band;

import br.com.events.band.data.io.band.request.BandRequest;
import br.com.events.band.data.io.commom.UuidHolderDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CreateBandUseCase {

    UuidHolderDTO execute(BandRequest bandForm, MultipartFile profilePicture);
}
