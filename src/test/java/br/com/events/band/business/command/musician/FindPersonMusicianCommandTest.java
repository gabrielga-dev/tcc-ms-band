package br.com.events.band.business.command.musician;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsAuthFeign;
import br.com.events.band.core.exception.BusinessException;
import br.com.events.band.data.io.person.response.PersonResponseMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindPersonMusicianCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindPersonMusicianCommandTest {

    @InjectMocks
    private FindPersonMusicianCommand command;

    @Mock
    private MsAuthFeign msAuthFeign;

    @Test
    @DisplayName("byCpf - when feign throws an exception that is not fault of client then throws exception")
    void byCpfWhenFeignThrowsAnExceptionThatIsNotFaultOfClientThenThrowsException() {
        var mockedException = mock(BusinessException.class);
        when(mockedException.isClientFault()).thenReturn(false);

        when(msAuthFeign.findPersonInformationByCpf(anyString())).thenThrow(mockedException);

        Assertions.assertThrows(
                BusinessException.class,
                () -> command.byCpf(MockConstants.STRING)
        );

        verify(msAuthFeign, times(1)).findPersonInformationByCpf(anyString());
    }

    @Test
    @DisplayName("byCpf - when feign throws an exception that is fault of client then return empty optional")
    void byCpfWhenFeignThrowsAnExceptionThatIsFaultOfClientThenReturnEmptyOptional() {
        var mockedException = mock(BusinessException.class);
        when(mockedException.isClientFault()).thenReturn(true);

        when(msAuthFeign.findPersonInformationByCpf(anyString())).thenThrow(mockedException);

        var result = command.byCpf(MockConstants.STRING);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());

        verify(msAuthFeign, times(1)).findPersonInformationByCpf(anyString());
    }

    @Test
    @DisplayName("byCpf - when feign throws no exception then return fulfilled optional")
    void byCpfWhenFeignThrowsNoExceptionThenReturnFulfilledOptional() {
        var person = PersonResponseMock.build();
        when(msAuthFeign.findPersonInformationByCpf(anyString())).thenReturn(person);

        var result = command.byCpf(MockConstants.STRING);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(person, result.get());

        verify(msAuthFeign, times(1)).findPersonInformationByCpf(anyString());
    }
}
