package br.com.events.band.business.use_case.band.impl;

import br.com.events.band.business.command.band.BuildBandResponseCommand;
import br.com.events.band.business.command.band.FindBandCommand;
import br.com.events.band.data.io.address.response.AddressResponseMock;
import br.com.events.band.data.io.band.criteria.FindBandsCriteria;
import br.com.events.band.data.io.band.response.BandResponseMock;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandsUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandsUseCaseImplTest {

    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private BuildBandResponseCommand buildBandResponseCommand;

    @InjectMocks
    private FindBandsUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called returns bands")
    void executeWhenCalledReturnsBands() {
        when(findBandCommand.byCriteria(any(FindBandsCriteria.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(BandTableMock.build())));
        var bandResponse = BandResponseMock.build();
        bandResponse.setAddress(AddressResponseMock.build());
        when(buildBandResponseCommand.execute(any(BandTable.class))).thenReturn(bandResponse);

        var response = useCase.execute(new FindBandsCriteria(), mock(Pageable.class));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getContent());
        Assertions.assertFalse(response.getContent().isEmpty());
        response.getContent().forEach(
                band -> {
                    Assertions.assertNotNull(band);
                    Assertions.assertNotNull(band.getAddress());
                }
        );
    }
}
