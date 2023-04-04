package br.com.events.band.application.controller.v1;

import br.com.events.band.domain.io.contact.createBandContact.rest.in.CreateBandContactRestForm;
import br.com.events.band.domain.mapper.contact.CreateBandContactMapper;
import br.com.events.band.domain.mapper.contact.DeleteBandContactMapper;
import br.com.events.band.infrastructure.controller.v1.ContactControllerV1Doc;
import br.com.events.band.infrastructure.useCase.contact.CreateBandContactUseCase;
import br.com.events.band.infrastructure.useCase.contact.DeleteBandContactUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/contact")
@RequiredArgsConstructor
public class ContactControllerV1 implements ContactControllerV1Doc {

    private final CreateBandContactUseCase createBandContactUseCase;
    private final DeleteBandContactUseCase deleteBandContactUseCase;

    @Override
    @PostMapping("/band/{uuid}")
    public ResponseEntity<Void> createBandContact(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid CreateBandContactRestForm bandRestForm
    ) {
        var mappedForm = CreateBandContactMapper.from(uuid, bandRestForm);

        createBandContactUseCase.execute(mappedForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
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
}
