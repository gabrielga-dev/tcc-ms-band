package br.com.events.band.business.use_case.musician_type.impl;

import br.com.events.band.business.command.musician_type.FindMusicianTypeCommand;
import br.com.events.band.data.io.musician_type.criteria.MusicianTypeCriteria;
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
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindMusicianTypeByCriteriaUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindMusicianTypeByCriteriaUseCaseImplTest {

    @Mock
    private FindMusicianTypeCommand findMusicianTypeCommand;
    @InjectMocks
    private FindMusicianTypeByCriteriaUseCaseImpl useCase;

    @Test
    @DisplayName("execute - when called, then return list")
    void executeWhenCalledThenReturnList() {
        var type1 = MusicianTypeTableMock.build();
        when(findMusicianTypeCommand.byCriteria(any(MusicianTypeCriteria.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(type1)));

        var returned = useCase.findByCriteria(mock(Pageable.class), new MusicianTypeCriteria());

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(1, returned.getContent().size());
        Assertions.assertEquals(type1.getName(), returned.getContent().get(0).getName());

        verify(findMusicianTypeCommand, atMostOnce()).byCriteria(any(MusicianTypeCriteria.class), any(Pageable.class));
    }
}
