package br.com.events.band.business.command.band;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.BandRepository;
import br.com.events.band.data.io.band.criteria.AuthenticatedPersonBandsCriteriaMock;
import br.com.events.band.data.io.band.criteria.FindBandsCriteriaMock;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandCommandTest {

    @InjectMocks
    private FindBandCommand command;

    @Mock
    private BandRepository bandRepository;

    @Test
    @DisplayName("byUuidAndOwnerUuid - when called calls repository")
    void byUuidAndOwnerUuidWhenCalledCallsRepository() {
        var bandTable = BandTableMock.build();
        when(bandRepository.findByUuidAndOwnerUuid(anyString(), anyString())).thenReturn(Optional.of(bandTable));

        var returned = command.byUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(bandTable, returned.get());

        verify(bandRepository, times(1)).findByUuidAndOwnerUuid(MockConstants.STRING, MockConstants.STRING);
    }

    @Test
    @DisplayName("byUuid - when called calls repository")
    void byUuidWhenCalledCallsRepository() {
        var bandTable = BandTableMock.build();
        when(bandRepository.findById(anyString())).thenReturn(Optional.of(bandTable));

        var returned = command.byUuid(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(bandTable, returned.get());

        verify(bandRepository, times(1)).findById(MockConstants.STRING);
    }

    @Test
    @DisplayName("byPerson - when called calls repository")
    void byPersonWhenCalledCallsRepository() {
        var bandTable = BandTableMock.build();
        when(
                bandRepository.findByAuthenticatedPerson(
                        any(Pageable.class),
                        anyString(),
                        anyString(),
                        anyLong(),
                        anyString(),
                        anyString(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class)
                )
        ).thenReturn(new PageImpl<>(List.of(bandTable)));

        var criteria = AuthenticatedPersonBandsCriteriaMock.build();
        var returned = command.byPerson(MockConstants.STRING, criteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertEquals(1, returned.getContent().size());
        Assertions.assertEquals(bandTable, returned.getContent().get(0));

        verify(bandRepository, times(1)).findByAuthenticatedPerson(
                any(Pageable.class),
                anyString(),
                anyString(),
                anyLong(),
                anyString(),
                anyString(),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        );
    }

    @Test
    @DisplayName("byPersonUuid - when called calls repository")
    void byPersonUuidWhenCalledCallsRepository() {
        var bandTable = BandTableMock.build();
        when(bandRepository.findByOwnerUuid(anyString())).thenReturn(List.of(bandTable));

        var returned = command.byPersonUuid(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(bandTable, returned.get(0));

        verify(bandRepository, times(1)).findByOwnerUuid(MockConstants.STRING);
    }

    @Test
    @DisplayName("byCriteria - when called calls repository")
    void byCriteriaWhenCalledCallsRepository() {
        var bandTable = BandTableMock.build();
        when(
                bandRepository.findByCriteria(
                        any(Pageable.class),
                        eq(true),
                        anyString(),
                        anyLong(),
                        anyString(),
                        anyString()
                )
        ).thenReturn(new PageImpl<>(List.of(bandTable)));

        var criteria = FindBandsCriteriaMock.build();
        var returned = command.byCriteria(criteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertEquals(1, returned.getContent().size());
        Assertions.assertEquals(bandTable, returned.getContent().get(0));

        verify(bandRepository, times(1)).findByCriteria(
                any(Pageable.class),
                eq(true),
                anyString(),
                anyLong(),
                anyString(),
                anyString()
        );
    }

    @Test
    @DisplayName("findAllByUuid - when called calls repository")
    void findAllByUuidWhenCalledCallsRepository() {
        var bandTable = BandTableMock.build();
        when(bandRepository.findAllByUuid(anyList())).thenReturn(List.of(bandTable));

        var returned = command.findAllByUuid(List.of(MockConstants.STRING));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(bandTable, returned.get(0));

        verify(bandRepository, times(1)).findAllByUuid(anyList());
    }
}
