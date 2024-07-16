package br.com.events.band.business.use_case.quote_request.impl;


import br.com.events.band.adapter.feign.MsEventFeign;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.band.business.command.quote_request.SendQuoteRequestCreationErrorMessageCommand;
import br.com.events.band.business.command.quote_request.SendQuoteRequestEmailMessageCommand;
import br.com.events.band.data.io.event.response.EventProfileResponse;
import br.com.events.band.data.io.event.response.EventProfileResponseMock;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequest;
import br.com.events.band.data.io.quote_request.request.QuoteRequestRequestMock;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.band.BandTableMock;
import br.com.events.band.data.model.table.music.MusicTableMock;
import br.com.events.band.data.model.table.musician.MusicianTypeTableMock;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateQuoteRequestUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CreateQuoteRequestUseCaseImplTest {

    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private FindMusicCommand findMusicCommand;
    @Mock
    private FindMusicianTypeCommand findMusicianTypeCommand;
    @Mock
    private SaveQuoteRequestCommand saveQuoteRequestCommand;
    @Mock
    private MsEventFeign msEventFeign;
    @Mock
    private SendQuoteRequestEmailMessageCommand sendQuoteRequestEmailMessageCommand;
    @Mock
    private SendQuoteRequestCreationErrorMessageCommand sendQuoteRequestEmailMessage;
    @InjectMocks
    private CreateQuoteRequestUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when band is not found, then throws BandNonExistenceException")
    void executeWhenBandIsNotFoundThenThrowsBandNonExistenceException() {
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.empty());
        doNothing().when(sendQuoteRequestEmailMessage).execute(any(QuoteRequestRequest.class));

        var request = QuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(request)
        );

        verify(findBandCommand, atMostOnce()).byUuid(anyString());
        verify(findMusicCommand, never()).byUuids(anyList());
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveQuoteRequestCommand, never()).execute(any(QuoteRequestTable.class));
        verify(msEventFeign, never()).findProfile(anyString());
        verify(sendQuoteRequestEmailMessageCommand, never())
                .execute(any(QuoteRequestRequest.class), any(EventProfileResponse.class), any(BandTable.class));
        verify(sendQuoteRequestEmailMessage, atMostOnce()).execute(any(QuoteRequestRequest.class));
    }

    @Test
    @DisplayName("execute - when band is not active, then throws BandNonExistenceException")
    void executeWhenBandIsNotActiveThenThrowsBandNonExistenceException() {
        var bandFound = BandTableMock.build();
        bandFound.setActive(false);
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        doNothing().when(sendQuoteRequestEmailMessage).execute(any(QuoteRequestRequest.class));

        var request = QuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(request)
        );

        verify(findBandCommand, atMostOnce()).byUuid(anyString());
        verify(findMusicCommand, never()).byUuids(anyList());
        verify(findMusicianTypeCommand, never()).byUuid(anyList());
        verify(saveQuoteRequestCommand, never()).execute(any(QuoteRequestTable.class));
        verify(msEventFeign, never()).findProfile(anyString());
        verify(sendQuoteRequestEmailMessageCommand, never())
                .execute(any(QuoteRequestRequest.class), any(EventProfileResponse.class), any(BandTable.class));
        verify(sendQuoteRequestEmailMessage, atMostOnce()).execute(any(QuoteRequestRequest.class));
    }

    @Test
    @DisplayName("execute - when band is active, send quote creation message")
    void executeWhenBandIsActiveThenSendQuoteCreationMessage() {
        var bandFound = BandTableMock.build();
        when(findBandCommand.byUuid(anyString())).thenReturn(Optional.of(bandFound));
        var music = MusicTableMock.build();
        when(findMusicCommand.byUuids(anyList())).thenReturn(new ArrayList<>(List.of(music)));
        var musicianType = MusicianTypeTableMock.build();
        when(findMusicianTypeCommand.byUuid(anyList())).thenReturn(new ArrayList<>(List.of(musicianType)));
        when(saveQuoteRequestCommand.execute(any(QuoteRequestTable.class))).thenReturn(QuoteRequestTableMock.build());
        when(msEventFeign.findProfile(anyString())).thenReturn(EventProfileResponseMock.build());
        doNothing().when(sendQuoteRequestEmailMessageCommand)
                .execute(any(QuoteRequestRequest.class), any(EventProfileResponse.class), any(BandTable.class));

        var request = QuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(request)
        );

        verify(findBandCommand, atMostOnce()).byUuid(anyString());
        verify(findMusicCommand, atMostOnce()).byUuids(anyList());
        verify(findMusicianTypeCommand, atMostOnce()).byUuid(anyList());
        verify(saveQuoteRequestCommand, atMostOnce()).execute(any(QuoteRequestTable.class));
        verify(msEventFeign, atMostOnce()).findProfile(anyString());
        verify(sendQuoteRequestEmailMessageCommand, atMostOnce())
                .execute(any(QuoteRequestRequest.class), any(EventProfileResponse.class), any(BandTable.class));
        verify(sendQuoteRequestEmailMessage, never()).execute(any(QuoteRequestRequest.class));
    }
}
