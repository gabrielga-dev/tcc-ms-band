package br.com.events.band.business.use_case.contact.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.contact.SaveContactCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.data.io.contact.request.ContactRequestMock;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.band.contact.ContactTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateBandContactUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CreateBandContactUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private SaveContactCommand saveContactCommand;
    @InjectMocks
    private CreateBandContactUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNonExistenceException")
    void executeWhenBandIsNotFoundThenThrowsBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.empty());

        var request = ContactRequestMock.build();
        Assertions.assertThrows(BandNonExistenceException.class, () -> useCase.execute(MockConstants.STRING, request));

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING);
        verify(saveContactCommand, never()).execute(any(ContactTable.class));
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        bandFound.setActive(Boolean.FALSE);
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));

        var request = ContactRequestMock.build();
        Assertions.assertThrows(BandNonExistenceException.class, () -> useCase.execute(MockConstants.STRING, request));

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING);
        verify(saveContactCommand, never()).execute(any(ContactTable.class));
    }

    @Test
    @DisplayName("execute - when band is active, then save new contact")
    void executeWhenBandIsActiveThenSaveNewContact() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandFound));
        when(saveContactCommand.execute(any(ContactTable.class))).thenReturn(new ContactTable());

        var request = ContactRequestMock.build();
        var returned = useCase.execute(MockConstants.STRING, request);

        Assertions.assertNotNull(returned);

        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findBandCommand, atMostOnce()).byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING);
        verify(saveContactCommand, atMostOnce()).execute(any(ContactTable.class));
    }

}
