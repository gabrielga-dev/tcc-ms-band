package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.band.BuildBandResponseCommand;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.data.io.address.response.AddressResponseMock;
import br.com.events.band.data.io.band.criteria.AuthenticatedPersonBandsCriteria;
import br.com.events.band.data.io.band.criteria.AuthenticatedPersonBandsCriteriaMock;
import br.com.events.band.data.io.band.response.BandResponse;
import br.com.events.band.data.model.table.band.BandTable;
import br.com.events.band.data.model.table.band.BandTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindAuthenticatedPersonBandsUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindAuthenticatedPersonBandsUseCaseImplTest {

    @Mock
    private AuthService authService;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private BuildBandResponseCommand buildBandResponseCommand;
    @InjectMocks
    private FindAuthenticatedPersonBandsUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called, then return a page with bands")
    void executeWhenCalledThenReturnPageWithBands() {
        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);
        var bandFound = BandTableMock.build();
        when(findBandCommand.byPerson(anyString(), any(AuthenticatedPersonBandsCriteria.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(bandFound)));
        var bandUpdated = new BandResponse(BandTableMock.build());
        bandUpdated.setAddress(AddressResponseMock.build());
        when(buildBandResponseCommand.execute(any(BandTable.class))).thenReturn(bandUpdated);

        var criteria = AuthenticatedPersonBandsCriteriaMock.build();
        var returned = useCase.execute(criteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        returned.getContent().forEach(
                band -> {
                    Assertions.assertNotNull(band.getAddress());
                    Assertions.assertEquals(bandUpdated.getAddress().getCity(), band.getAddress().getCity());
                }
        );
    }
}
