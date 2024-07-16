package br.com.events.band.business.use_case.quote_request.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.business.command.quote.SaveQuoteCommand;
import br.com.events.band.business.command.quote.SendQuoteCreationMessageCommand;
import br.com.events.band.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.band.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.core.exception.band.BandOwnerException;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.data.io.quote_request.request.AcceptQuoteRequestRequestMock;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
import br.com.events.band.data.model.table.quote.QuoteTable;
import br.com.events.band.data.model.table.quote.QuoteTableMock;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTable;
import br.com.events.band.data.model.table.quote_request.QuoteRequestTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link AcceptQuoteRequestUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class AcceptQuoteRequestUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private SaveQuoteCommand saveQuoteCommand;
    @Mock
    private SaveQuoteRequestCommand saveQuoteRequestCommand;
    @Mock
    private SendQuoteCreationMessageCommand sendQuoteCreationMessageCommand;
    @InjectMocks
    private AcceptQuoteRequestUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when person is not band owner, then throws BandOwnerException")
    void executeWhenPersonIsNotBandOwnerThenThrowsBandOwnerException() {
        var quoteRequestFound = QuoteRequestTableMock.build();
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequestFound);

        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING.repeat(2));

        var request = AcceptQuoteRequestRequestMock.build();
        Assertions.assertThrows(
                BandOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, never()).byUuids(anyList());
        verify(saveQuoteCommand, never()).execute(any(QuoteTable.class));
        verify(saveQuoteRequestCommand, never()).execute(any(QuoteRequestTable.class));
        verify(sendQuoteCreationMessageCommand, never()).execute(any(QuoteTable.class));
    }

    @Test
    @DisplayName("execute - when no musician was found, then throws MusicianDoesNotExistException")
    void executeWhenNoMusicianWasFoundThenThrowsMusicianDoesNotExistException() {
        var quoteRequestFound = QuoteRequestTableMock.build();
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequestFound);

        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        when(findMusicianCommand.byUuids(anyList())).thenReturn(new ArrayList<>());

        var request = AcceptQuoteRequestRequestMock.build();
        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byUuids(anyList());
        verify(saveQuoteCommand, never()).execute(any(QuoteTable.class));
        verify(saveQuoteRequestCommand, never()).execute(any(QuoteRequestTable.class));
        verify(sendQuoteCreationMessageCommand, never()).execute(any(QuoteTable.class));
    }

    @Test
    @DisplayName("execute - when a musician is not active, then throws MusicianDoesNotExistException")
    void executeWhenOneMusicianIsNotActiveThenThrowsMusicianDoesNotExistException() {
        var quoteRequestFound = QuoteRequestTableMock.build();
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequestFound);

        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var musician1 = MusicianTableMock.build();
        var musician2 = MusicianTableMock.build();
        musician2.setActive(false);
        when(findMusicianCommand.byUuids(anyList())).thenReturn(new ArrayList<>(List.of(musician1, musician2)));

        var request = AcceptQuoteRequestRequestMock.build();
        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byUuids(anyList());
        verify(saveQuoteCommand, never()).execute(any(QuoteTable.class));
        verify(saveQuoteRequestCommand, never()).execute(any(QuoteRequestTable.class));
        verify(sendQuoteCreationMessageCommand, never()).execute(any(QuoteTable.class));
    }

    @Test
    @DisplayName("execute - when all musicians are active, then send quote creation message")
    void executeWhenAllMusiciansAreActiveThenSendQuoteCreationMessage() {
        var quoteRequestFound = QuoteRequestTableMock.build();
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequestFound);

        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var musician1 = MusicianTableMock.build();
        when(findMusicianCommand.byUuids(anyList())).thenReturn(new ArrayList<>(List.of(musician1)));

        when(saveQuoteCommand.execute(any(QuoteTable.class))).thenReturn(QuoteTableMock.build());

        when(saveQuoteRequestCommand.execute(any(QuoteRequestTable.class))).thenReturn(QuoteRequestTableMock.build());

        doNothing().when(sendQuoteCreationMessageCommand).execute(any(QuoteTable.class));

        var request = AcceptQuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findQuoteRequestCommand, atMostOnce()).findByUuidOrThrow(anyString());
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
        verify(findMusicianCommand, atMostOnce()).byUuids(anyList());
        verify(saveQuoteCommand, atMostOnce()).execute(any(QuoteTable.class));
        verify(saveQuoteRequestCommand, atMostOnce()).execute(any(QuoteRequestTable.class));
        verify(sendQuoteCreationMessageCommand, atMostOnce()).execute(any(QuoteTable.class));
    }

}
