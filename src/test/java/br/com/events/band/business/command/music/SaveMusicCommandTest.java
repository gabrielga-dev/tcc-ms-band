package br.com.events.band.business.command.music;


import br.com.events.band.adapter.repository.MusicRepository;
import br.com.events.band.data.model.table.music.MusicTable;
import br.com.events.band.data.model.table.music.MusicTableMock;
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
 * Tests for {@link SaveMusicCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveMusicCommandTest {

    @InjectMocks
    private SaveMusicCommand command;

    @Mock
    private MusicRepository musicRepository;

    @Test
    @DisplayName("execute - when called return saved music")
    void executeWhenCalledReturnSavedMusic() {
        var mockedSaved = MusicTableMock.build();
        when(musicRepository.save(any(MusicTable.class))).thenReturn(mockedSaved);

        var toSave = MusicTableMock.build();
        var saved = command.execute(toSave);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(mockedSaved, saved);

        verify(musicRepository, times(1)).save(any(MusicTable.class));
    }
}
