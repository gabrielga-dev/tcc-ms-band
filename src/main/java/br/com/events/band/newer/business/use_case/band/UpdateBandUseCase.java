package br.com.events.band.newer.business.use_case.band;

import br.com.events.band.newer.data.io.band.request.UpdateBandRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateBandUseCase {

    void execute(String bandUuid, UpdateBandRequest request, MultipartFile profilePicture);
}
