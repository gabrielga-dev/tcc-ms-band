package br.com.events.band.business.use_case.musician_type.impl;

import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.data.model.table.musician.MusicianTypeTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindAllMusicianTypeUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindAllMusicianTypeUseCaseImplTest {

    @Mock
    private FindMusicianTypeCommand findMusicianTypeCommand;
    @InjectMocks
    private FindAllMusicianTypeUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called, then return mapped and sorted list")
    void executeWhenCalledThenReturnMappedAndSortedList() {
        var type1 = MusicianTypeTableMock.build();
        type1.setName("1");
        var type2 = MusicianTypeTableMock.build();
        type2.setName("2");
        when(findMusicianTypeCommand.findAll()).thenReturn(List.of(type2, type1));

        var returned = useCase.execute();

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(2, returned.size());
        Assertions.assertEquals(type1.getName(), returned.get(0).getName());
        Assertions.assertEquals(type2.getName(), returned.get(1).getName());

        verify(findMusicianTypeCommand, atMostOnce()).findAll();
    }

}
