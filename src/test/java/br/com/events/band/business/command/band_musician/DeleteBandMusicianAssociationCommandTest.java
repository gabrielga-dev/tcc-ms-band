package br.com.events.band.business.command.band_musician;


import br.com.events.band.adapter.repository.BandMusicianRepository;
import br.com.events.band.data.model.table.band.BandMusicianTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link DeleteBandMusicianAssociationCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeleteBandMusicianAssociationCommandTest {

    @InjectMocks
    private DeleteBandMusicianAssociationCommand command;

    @Mock
    private BandMusicianRepository bandMusicianRepository;

    @Test
    @DisplayName("execute - when called calls repository")
    void executeWhenCalledCallsRepository() {
        doNothing().when(bandMusicianRepository).disassociateMusicianFromBand(anyString(), anyString());

        var table = BandMusicianTableMock.build();
        Assertions.assertDoesNotThrow(() -> command.execute(table));

        verify(bandMusicianRepository, times(1)).disassociateMusicianFromBand(anyString(), anyString());
    }

}
