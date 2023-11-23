package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.MusicianTypePort;
import br.com.events.band.business.use_case.musician_type.FindMusicianTypeByCriteriaUseCase;
import br.com.events.band.data.io.musician_type.criteria.MusicianTypeCriteria;
import br.com.events.band.data.io.musician_type.response.MusicianTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/musician/type")
@RequiredArgsConstructor
public class MusicianTypeRestControllerV1 implements MusicianTypePort {

    private final FindMusicianTypeByCriteriaUseCase findMusicianTypeByCriteriaUseCase;

    @Override
    @GetMapping
    @PreAuthorize("hasAnyAuthority('BAND', 'CONTRACTOR')")
    public ResponseEntity<Page<MusicianTypeResponse>> findByCriteria(
            Pageable pageable, MusicianTypeCriteria criteria
    ) {
        var page = findMusicianTypeByCriteriaUseCase.findByCriteria(pageable, criteria);
        return ResponseEntity.ok(page);
    }
}
