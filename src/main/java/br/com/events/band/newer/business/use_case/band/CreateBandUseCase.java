package br.com.events.band.newer.business.use_case.band;

import br.com.events.band.newer.data.io.band.request.BandRequest;
import br.com.events.band.newer.data.io.commom.UuidHolderDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CreateBandUseCase {

    UuidHolderDTO execute(BandRequest bandForm, MultipartFile profilePicture);
}
