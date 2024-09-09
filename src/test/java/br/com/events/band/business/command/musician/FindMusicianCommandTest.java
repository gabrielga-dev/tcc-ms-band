package br.com.events.band.business.command.musician;

import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.MusicianRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindMusicianCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindMusicianCommandTest {

    @InjectMocks
    private FindMusicianCommand command;

    @Mock
    private MusicianRepository musicianRepository;

    @Test
    @DisplayName("byUuid - when called returns found result")
    void byUuidWhenCalledReturnsFoundResult() {
        var musician = MusicianTableMock.build();
        when(musicianRepository.findById(anyString())).thenReturn(Optional.of(musician));

        var result = command.byUuid(MockConstants.STRING);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(musician, result.get());

        verify(musicianRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("byUuids - when called returns found result")
    void byUuidsWhenCalledReturnsFoundResult() {
        var musician = MusicianTableMock.build();
        when(musicianRepository.findByUuidInList(anyList())).thenReturn(List.of(musician));

        var result = command.byUuids(List.of(MockConstants.STRING));

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(musician, result.get(0));

        verify(musicianRepository, times(1)).findByUuidInList(anyList());
    }

    @Test
    @DisplayName("byCpf - when called returns found result")
    void byCpfWhenCalledReturnsFoundResult() {
        var musician = MusicianTableMock.build();
        when(musicianRepository.findByCpf(anyString())).thenReturn(Optional.of(musician));

        var result = command.byCpf(MockConstants.STRING);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(musician, result.get());

        verify(musicianRepository, times(1)).findByCpf(anyString());
    }

    @Test
    @DisplayName("byCriteria - when called returns found result")
    void byCriteriaWhenCalledReturnsFoundResult() {
        var musician = MusicianTableMock.build();
        when(
                musicianRepository.findByCriteria(
                        anyBoolean(),
                        anyString(),
                        anyString(),
                        anyString(),
                        anyLong(),
                        anyString(),
                        anyString(),
                        anyString(),
                        any(Pageable.class)
                )
        ).thenReturn(new PageImpl<>(List.of(musician)));

        var criteria = MusicianCriteriaMock.build();
        var result = command.byCriteria(mock(Pageable.class), criteria);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getContent());
        Assertions.assertFalse(result.getContent().isEmpty());
        Assertions.assertNotNull(result.getContent().get(0));
        Assertions.assertEquals(musician, result.getContent().get(0));

        verify(musicianRepository, times(1)).findByCriteria(
                anyBoolean(),
                anyString(),
                anyString(),
                anyString(),
                anyLong(),
                anyString(),
                anyString(),
                anyString(),
                any(Pageable.class)
        );
    }
}
