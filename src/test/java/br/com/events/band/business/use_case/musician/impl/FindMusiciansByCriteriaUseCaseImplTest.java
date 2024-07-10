package br.com.events.band.business.use_case.musician.impl;

import br.com.events.band.business.command.address.BuildAddressResponseCommand;
import br.com.events.band.business.command.musician.FindMusicianCommand;
import br.com.events.band.data.io.address.IAddress;
import br.com.events.band.data.io.address.response.AddressResponseMock;
import br.com.events.band.data.io.musician.criteria.MusicianCriteria;
import br.com.events.band.data.io.musician.criteria.MusicianCriteriaMock;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
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
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindMusiciansByCriteriaUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindMusiciansByCriteriaUseCaseImplTest {

    @Mock
    private FindMusicianCommand findMusicianCommand;
    @Mock
    private BuildAddressResponseCommand buildAddressResponseCommand;
    @InjectMocks
    private FindMusiciansByCriteriaUseCaseImpl useCase;

    @Test
    @DisplayName("")
    void executeWhenCalledThenReturnMusicians() {
        when(findMusicianCommand.byCriteria(any(Pageable.class), any(MusicianCriteria.class))).thenReturn(
                new PageImpl<>(List.of(MusicianTableMock.build()))
        );
        var address = AddressResponseMock.build();
        when(buildAddressResponseCommand.execute(any(IAddress.class))).thenReturn(address);

        var response = useCase.execute(mock(Pageable.class), MusicianCriteriaMock.build());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getContent());
        Assertions.assertFalse(response.getContent().isEmpty());
        Assertions.assertEquals(address, response.getContent().get(0).getAddress());

        verify(findMusicianCommand, atMostOnce()).byCriteria(any(Pageable.class), any(MusicianCriteria.class));
        verify(buildAddressResponseCommand, atMostOnce()).execute(any(IAddress.class));
    }
}
