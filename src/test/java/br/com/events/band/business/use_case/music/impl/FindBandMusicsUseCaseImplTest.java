package br.com.events.band.business.use_case.music.impl;

import br.com.events.band.MockConstants;
import br.com.events.band.business.command.music.FindMusicCommand;
import br.com.events.band.data.io.music.criteria.MusicCriteria;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandMusicsUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandMusicsUseCaseImplTest {

    @Mock
    private FindMusicCommand findMusicCommand;

    @InjectMocks
    private FindBandMusicsUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called return band's musics")
    void executeThenReturnBandsMusics() {
        var music = MusicTableMock.build();
        when(findMusicCommand.byBandUuidAndCriteria(anyString(), any(MusicCriteria.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(music)));

        var returned = useCase.execute(MockConstants.STRING, MusicCriteriaMock.build(), mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertEquals(music.getUuid(), returned.getContent().get(0).getUuid());


        verify(findMusicCommand, atMostOnce()).byBandUuidAndCriteria(
                anyString(), any(MusicCriteria.class), any(Pageable.class)
        );
    }

}
