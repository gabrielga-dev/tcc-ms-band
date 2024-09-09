package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.core.exception.musician.MusicianDoesNotExistException;
import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.address.response.AddressResponseMock;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
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
 * Tests for {@link FindMusicianByUuidUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindMusicianByUuidUseCaseImplTest {

    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private BuildAddressResponseCommand buildAddressResponseCommand;
    @InjectMocks
    private FindMusicianByUuidUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when musician is not found, then throws MusicianDoesNotExistException")
    void executeWhenMusicianIsNotFoundThenThrowsMusicianDoesNotExistException() {
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                MusicianDoesNotExistException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findMusicianCommand, atMostOnce()).byCpf(MockConstants.STRING);
        verify(buildAddressResponseCommand, never()).execute(any(IAddress.class));
    }

    @Test
    @DisplayName("execute - when musician is found, then return musician")
    void executeWhenMusicianIsFoundThenReturnMusician() {
        var musicianFound = MusicianTableMock.build();
        when(findMusicianCommand.byUuid(anyString())).thenReturn(Optional.of(musicianFound));
        var address = AddressResponseMock.build();
        when(buildAddressResponseCommand.execute(any(IAddress.class))).thenReturn(address);

        var response = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(musicianFound.getUuid(), response.getUuid());
        Assertions.assertEquals(address, response.getAddress());

        verify(findMusicianCommand, atMostOnce()).byCpf(MockConstants.STRING);
        verify(buildAddressResponseCommand, atMostOnce()).execute(any(IAddress.class));
    }
}
