package br.com.events.band.application.controller.v1;

import br.com.events.band.domain.io.contact.createBandContact.rest.in.CreateBandContactRestForm;
import br.com.events.band.domain.io.contact.listBandContact.rest.out.ListBandContactRestResult;
import br.com.events.band.domain.io.contact.updateBandContact.rest.in.UpdateBandContactRestForm;
import br.com.events.band.domain.mapper.contact.CreateBandContactMapper;
import br.com.events.band.domain.mapper.contact.DeleteBandContactMapper;
import br.com.events.band.domain.mapper.contact.ListBandContactMapper;
import br.com.events.band.domain.mapper.contact.UpdateBandContactMapper;
import br.com.events.band.infrastructure.controller.v1.ContactControllerV1Doc;
import br.com.events.band.infrastructure.useCase.contact.CreateBandContactUseCase;
import br.com.events.band.infrastructure.useCase.contact.DeleteBandContactUseCase;
import br.com.events.band.infrastructure.useCase.contact.ListBandContactUseCase;
import br.com.events.band.infrastructure.useCase.contact.UpdateBandContactUseCase;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/contact")
@RequiredArgsConstructor
public class ContactControllerV1 implements ContactControllerV1Doc {

    private final CreateBandContactUseCase createBandContactUseCase;
    private final DeleteBandContactUseCase deleteBandContactUseCase;
    private final UpdateBandContactUseCase updateBandContactUseCase;
    private final ListBandContactUseCase listBandContactUseCase;

    @Override
    @PostMapping("/band/{uuid}")
    public ResponseEntity<ListBandContactRestResult> createBandContact(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid CreateBandContactRestForm bandRestForm
    ) {
        var mappedForm = CreateBandContactMapper.from(uuid, bandRestForm);

        var response = createBandContactUseCase.execute(mappedForm);

        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("band/{bandUuid}/contact/{contactUuid}")
    public ResponseEntity<Void> removeBandContact(
            @PathVariable("bandUuid") String bandUuid,
            @PathVariable("contactUuid") String contactUuid
    ) {
        var mappedForm = DeleteBandContactMapper.from(bandUuid, contactUuid);

        deleteBandContactUseCase.execute(mappedForm);

        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("band/{bandUuid}/contact/{contactUuid}")
    public ResponseEntity<Void> updateBandContact(
            @PathVariable("bandUuid") String bandUuid,
            @PathVariable("contactUuid") String contactUuid,
            @RequestBody @Valid UpdateBandContactRestForm form
    ) {
        var mappedForm = UpdateBandContactMapper.from(bandUuid, contactUuid, form);

        updateBandContactUseCase.execute(mappedForm);

        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("band/{bandUuid}")
    public ResponseEntity<List<ListBandContactRestResult>> listBandContact(
            @PathVariable("bandUuid") String bandUuid
    ) {
        var result = listBandContactUseCase.execute(bandUuid);
        var mappedResult = ListBandContactMapper.fromUseCaseResult(result);

        return ResponseEntity.ok(mappedResult);
    }
}
