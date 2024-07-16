package br.com.events.band.business.command.address;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsLocationFeign;
import br.com.events.band.core.exception.address.LocationDoesntExistsException;
import br.com.events.band.data.FeignMockedException;
import br.com.events.band.data.io.address.request.AddressRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link CheckAddressCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CheckAddressCommandTest {

    @InjectMocks
    private CheckAddressCommand command;

    @Mock
    private MsLocationFeign msLocationFeign;

    @Test
    @DisplayName("execute - when any FeignException is thrown, then throws LocationDoesntExistsException")
    void executeWhenAnyFeignExceptionIsThrownThenThrowsLocationDoesntExistsException() {
        doThrow(new FeignMockedException(HttpStatus.NOT_FOUND.value(), MockConstants.STRING))
                .when(msLocationFeign)
                .validateIfAddressExists(anyLong(), anyString(), anyString());

        var address = AddressRequestMock.build();
        Assertions.assertThrows(
                LocationDoesntExistsException.class,
                () -> command.execute(address)
        );

        verify(msLocationFeign, times(1)).validateIfAddressExists(anyLong(), anyString(), anyString());
    }

    @Test
    @DisplayName("execute - when address is found, then throws no exception")
    void executeWhenAddressIsFoundThenNoExceptionIsThrown() {
        doNothing().when(msLocationFeign).validateIfAddressExists(anyLong(), anyString(), anyString());

        var address = AddressRequestMock.build();
        Assertions.assertDoesNotThrow(() -> command.execute(address));

        verify(msLocationFeign, times(1)).validateIfAddressExists(anyLong(), anyString(), anyString());
    }
}
