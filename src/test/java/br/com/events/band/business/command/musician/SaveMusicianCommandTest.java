package br.com.events.band.business.command.musician;


import br.com.events.band.adapter.repository.MusicianRepository;
import br.com.events.band.data.model.table.musician.MusicianTable;
import br.com.events.band.data.model.table.musician.MusicianTableMock;
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
 * Tests for {@link SaveMusicianCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveMusicianCommandTest {

    @InjectMocks
    private SaveMusicianCommand command;

    @Mock
    private MusicianRepository musicianRepository;

    @Test
    @DisplayName("execute - when called return saved musician")
    void executeWhenCalledReturnSavedMusician() {
        var mockedSave = MusicianTableMock.build();
        when(musicianRepository.save(any(MusicianTable.class))).thenReturn(mockedSave);

        var toSave = MusicianTableMock.build();
        var saved = command.execute(toSave);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(mockedSave, saved);

        verify(musicianRepository, times(1)).save(any(MusicianTable.class));
    }

}
