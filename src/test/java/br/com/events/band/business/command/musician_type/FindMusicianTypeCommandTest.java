package br.com.events.band.business.command.musician_type;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.MusicianTypeRepository;
import br.com.events.band.data.io.musician_type.criteria.MusicianTypeCriteriaMock;
import br.com.events.band.data.model.table.musician.MusicianTypeTableMock;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindMusicianTypeCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindMusicianTypeCommandTest {

    @InjectMocks
    private FindMusicianTypeCommand command;

    @Mock
    private MusicianTypeRepository musicianTypeRepository;

    @Test
    @DisplayName("findAll - when called return found result")
    void findAllWhenCalledReturnFoundResult() {
        var musicianType = MusicianTypeTableMock.build();
        when(musicianTypeRepository.findAll()).thenReturn(List.of(musicianType));

        var returned = command.findAll();

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertNotNull(returned.get(0));
        Assertions.assertEquals(musicianType, returned.get(0));

        verify(musicianTypeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("byUuid - when called return found result")
    void byUuidWhenCalledReturnFoundResult() {
        var musicianType = MusicianTypeTableMock.build();
        when(musicianTypeRepository.findAllByUuid(anyList())).thenReturn(List.of(musicianType));

        var returned = command.byUuid(List.of(MockConstants.STRING));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertNotNull(returned.get(0));
        Assertions.assertEquals(musicianType, returned.get(0));

        verify(musicianTypeRepository, times(1)).findAllByUuid(anyList());
    }

    @Test
    @DisplayName("byCriteria - when called return found result")
    void byCriteriaWhenCalledReturnFoundResult() {
        var musicianType = MusicianTypeTableMock.build();
        when(
                musicianTypeRepository.findByCriteria(
                        anyString(),
                        any(Pageable.class)
                )
        ).thenReturn(new PageImpl<>(List.of(musicianType)));

        var criteria = MusicianTypeCriteriaMock.build();
        var returned = command.byCriteria(criteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertNotNull(returned.getContent().get(0));
        Assertions.assertEquals(musicianType, returned.getContent().get(0));

        verify(musicianTypeRepository, times(1)).findByCriteria(
                anyString(),
                any(Pageable.class)
        );
    }
}
