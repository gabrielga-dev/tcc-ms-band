package br.com.events.band.business.command.band;


import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.address.response.AddressResponseMock;
import br.com.events.band.data.model.table.band.BandAddressTable;
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
 * Tests for {@link BuildBandResponseCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class BuildBandResponseCommandTest {

    @InjectMocks
    private BuildBandResponseCommand command;

    @Mock
    private BuildAddressResponseCommand buildAddressResponseCommand;

    @Test
    @DisplayName("execute - when called return BandResponse")
    void executeWhenCalledReturnBandResponse() {
        var mockedAddress = AddressResponseMock.build();
        when(buildAddressResponseCommand.execute(any(BandAddressTable.class))).thenReturn(mockedAddress);

        var bandTable = BandTableMock.build();
        var returned = command.execute(bandTable);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(bandTable.getUuid(), returned.getUuid());
        Assertions.assertEquals(mockedAddress, returned.getAddress());

        verify(buildAddressResponseCommand, times(1)).execute(any(IAddress.class));
    }
}
