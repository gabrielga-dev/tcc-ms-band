package br.com.events.band.business.command.music;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.MusicRepository;
import br.com.events.band.business.service.AuthService;
import br.com.events.band.data.io.music.criteria.MusicCriteriaMock;
import br.com.events.band.data.model.table.music.MusicTableMock;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindMusicCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindMusicCommandTest {

    @InjectMocks
    private FindMusicCommand command;

    @Mock
    private MusicRepository musicRepository;
    @Mock
    private AuthService authService;

    @Test
    @DisplayName("byUuid - when called return found result")
    void byUuidWhenCalledReturnFoundResult() {
        var music = MusicTableMock.build();
        when(musicRepository.findById(anyString())).thenReturn(Optional.of(music));

        var returned = command.byUuid(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(music, returned.get());

        verify(musicRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("byUuids - when called return found result")
    void byUuidsWhenCalledReturnFoundResult() {
        var music = MusicTableMock.build();
        when(musicRepository.findAllByUuids(anyList())).thenReturn(List.of(music));

        var returned = command.byUuids(List.of(MockConstants.STRING));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertNotNull(returned.get(0));
        Assertions.assertEquals(music, returned.get(0));

        verify(musicRepository, times(1)).findAllByUuids(anyList());
    }

    @Test
    @DisplayName("byBandUuidAndCriteria - when called return found result")
    void byBandUuidAndCriteriaWhenCalledReturnFoundResult() {
        var music = MusicTableMock.build();
        when(
                musicRepository.findByCriteria(
                        anyString(),
                        anyString(),
                        anyString(),
                        anyString(),
                        anyString(),
                        any(Pageable.class)
                )
        ).thenReturn(new PageImpl<>(List.of(music)));

        when(authService.getAuthenticatedPersonUuid()).thenReturn(MockConstants.STRING);

        var criteria = MusicCriteriaMock.build();
        var returned = command.byBandUuidAndCriteria(
                MockConstants.STRING, criteria, mock(Pageable.class)
        );

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertNotNull(returned.getContent().get(0));
        Assertions.assertEquals(music, returned.getContent().get(0));

        verify(musicRepository, times(1)).findByCriteria(
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                any(Pageable.class)
        );
    }

    @Test
    @DisplayName("byCriteria - when called return found result")
    void byCriteriaWhenCalledReturnFoundResult() {
        var music = MusicTableMock.build();
        when(
                musicRepository.findByCriteria(
                        anyString(),
                        anyString(),
                        anyString(),
                        any(Pageable.class)
                )
        ).thenReturn(new PageImpl<>(List.of(music)));

        var criteria = MusicCriteriaMock.build();
        var returned = command.byCriteria(criteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertNotNull(returned.getContent().get(0));
        Assertions.assertEquals(music, returned.getContent().get(0));

        verify(musicRepository, times(1)).findByCriteria(
                anyString(),
                anyString(),
                anyString(),
                any(Pageable.class)
        );
    }
}
