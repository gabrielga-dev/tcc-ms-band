package br.com.events.band.business.use_case.band.impl;


import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.data.model.table.band.BandTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandNamesUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandNamesUseCaseImplTest {

    @Mock
    private FindBandCommand findBandCommand;

    @InjectMocks
    private FindBandNamesUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called, then return map with names")
    void executeWhenCalledThenReturnMapWithNames() {
        var band1 = new BandTable();
        band1.setUuid("1");
        band1.setName("1");
        var band2 = new BandTable();
        band2.setUuid("2");
        band2.setName("2");
        when(findBandCommand.findAllByUuid(anyList())).thenReturn(List.of(band1, band2));

        var returned = useCase.execute(List.of(MockConstants.STRING, MockConstants.STRING.repeat(2)));

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(band1.getName(), returned.get(band1.getUuid()));
        Assertions.assertEquals(band2.getName(), returned.get(band2.getUuid()));

        verify(findBandCommand, atMostOnce()).findAllByUuid(anyList());
    }
}
