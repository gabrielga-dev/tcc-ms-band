package br.com.events.band.business.command.band;


import br.com.events.band.adapter.repository.BandRepository;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.band.BandTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SaveBandCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveBandCommandTest {

    @InjectMocks
    private SaveBandCommand command;

    @Mock
    private BandRepository bandRepository;

    @Test
    @DisplayName("execute - when called calls repository and return saved band")
    void executeWhenCalledCallsRepositoryAndReturnSavedBand() {
        var bandTable = BandTableMock.build();
        when(bandRepository.save(any(BandTable.class))).thenReturn(bandTable);

        var toSave = BandTableMock.build();
        var returned = command.execute(toSave);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(bandTable, returned);

        verify(bandRepository, times(1)).save(toSave);
    }

}
