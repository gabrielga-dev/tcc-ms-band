package br.com.events.band.business.use_case.contact.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.contact.FindContactCommand;
import br.com.events.band.business.command.contact.SaveContactCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandContactNonExistenceException;
import br.com.events.band.core.exception.band.BandNonExistenceException;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.data.io.contact.request.ContactRequestMock;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.band.contact.ContactTable;
import br.com.events.band.data.model.table.band.contact.ContactTableMock;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link UpdateBandContactUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class UpdateBandContactUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindContactCommand findContactCommand;
    @Mock
    private SaveContactCommand saveContactCommand;

    @InjectMocks
    private UpdateBandContactUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when contact is not found, then throws BandContactNonExistenceException")
    void executeWhenContactIsNotFoundThenThrowsBandContactNonExistenceException() {
        when(findContactCommand.byUuidAndBandUuid(anyString(), anyString())).thenReturn(Optional.empty());

        var request = ContactRequestMock.build();
        Assertions.assertThrows(
                BandContactNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findContactCommand, atMostOnce()).byUuidAndBandUuid(eq(MockConstants.STRING), eq(MockConstants.STRING));
        verify(authService, never()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        var contact = ContactTableMock.build();
        contact.setBand(BandTableMock.build());
        contact.getBand().setActive(Boolean.FALSE);
        when(findContactCommand.byUuidAndBandUuid(anyString(), anyString())).thenReturn(Optional.of(contact));

        var request = ContactRequestMock.build();
        Assertions.assertThrows(
                BandNonExistenceException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findContactCommand, atMostOnce()).byUuidAndBandUuid(eq(MockConstants.STRING), eq(MockConstants.STRING));
        verify(authService, never()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when person is not band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        var contact = ContactTableMock.build();
        contact.setBand(BandTableMock.build());
        contact.getBand().setOwnerUuid(MockConstants.STRING.repeat(2));
        when(findContactCommand.byUuidAndBandUuid(anyString(), anyString())).thenReturn(Optional.of(contact));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var request = ContactRequestMock.build();
        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findContactCommand, atMostOnce()).byUuidAndBandUuid(eq(MockConstants.STRING), eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }

    @Test
    @DisplayName("execute - when person is band owner, then update contact")
    void executeWhenPersonIsBandOwnerThenUpdateContact() {
        var contact = ContactTableMock.build();
        contact.setBand(BandTableMock.build());
        when(findContactCommand.byUuidAndBandUuid(anyString(), anyString())).thenReturn(Optional.of(contact));
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(saveContactCommand.execute(any(ContactTable.class))).thenReturn(new ContactTable());

        var request = ContactRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findContactCommand, atMostOnce()).byUuidAndBandUuid(eq(MockConstants.STRING), eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }
}
