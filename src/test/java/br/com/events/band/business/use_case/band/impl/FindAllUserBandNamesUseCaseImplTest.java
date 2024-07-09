package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.data.model.table.band.BandTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindAllUserBandNamesUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
public class FindAllUserBandNamesUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;

    @InjectMocks
    private FindAllUserBandNamesUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called, then return map with names")
    void executeWhenCalledThenReturnMapWithNames() {
        var band1 = new BandTable();
        band1.setUuid("1");
        band1.setName("1");
        var band2 = new BandTable();
        band2.setUuid("2");
        band2.setName("2");
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        when(findBandCommand.byPersonUuid(anyString())).thenReturn(List.of(band1, band2));

        var returned = useCase.execute();

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(band1.getName(), returned.get(band1.getUuid()));
        Assertions.assertEquals(band2.getName(), returned.get(band2.getUuid()));

        verify(findBandCommand, atMostOnce()).byPersonUuid(eq(MockConstants.STRING));
        verify(authService, atMostOnce()).getAuthenticatedPersonUuid();
    }
}
