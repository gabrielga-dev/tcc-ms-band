package br.com.events.band.adapter.port.rest.v1;

import br.com.events.band.adapter.port.ContactPort;
import br.com.events.band.business.use_case.contact.CreateBandContactUseCase;
import br.com.events.band.data.io.contact.request.ContactRequest;
import br.com.events.band.business.use_case.contact.DeleteBandContactUseCase;
import br.com.events.band.business.use_case.contact.ListBandContactUseCase;
import br.com.events.band.business.use_case.contact.UpdateBandContactUseCase;
import br.com.events.band.data.io.contact.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/contact")
@RequiredArgsConstructor
public class ContactRestControllerV1 implements ContactPort {

    private final CreateBandContactUseCase createBandContactUseCase;
    private final DeleteBandContactUseCase deleteBandContactUseCase;
    private final UpdateBandContactUseCase updateBandContactUseCase;
    private final ListBandContactUseCase listBandContactUseCase;

    @Override
    @PostMapping("/band/{uuid}")
    public ResponseEntity<ContactResponse> createBandContact(
            @PathVariable("uuid") String bandUuid,
            @RequestBody ContactRequest request
    ) {
        var response = createBandContactUseCase.execute(bandUuid, request);

        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("band/{bandUuid}/contact/{contactUuid}")
    public ResponseEntity<Void> removeBandContact(
            @PathVariable("bandUuid") String bandUuid,
            @PathVariable("contactUuid") String contactUuid
    ) {
        deleteBandContactUseCase.execute(bandUuid, contactUuid);

        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("band/{bandUuid}/contact/{contactUuid}")
    public ResponseEntity<Void> updateBandContact(
            @PathVariable("bandUuid") String bandUuid,
            @PathVariable("contactUuid") String contactUuid,
            @RequestBody ContactRequest request
    ) {
        updateBandContactUseCase.execute(bandUuid, contactUuid, request);

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("band/{bandUuid}")
    public ResponseEntity<List<ContactResponse>> listBandContact(
            @PathVariable("bandUuid") String bandUuid
    ) {
        var result = listBandContactUseCase.execute(bandUuid);

        return ResponseEntity.ok(result);
    }
}
