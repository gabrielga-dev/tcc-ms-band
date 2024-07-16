package br.com.events.band.business.command.contact;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link DeleteContactCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeleteContactCommandTest {

    @InjectMocks
    private DeleteContactCommand command;

    @Mock
    private ContactRepository contactRepository;

    @Test
    @DisplayName("execute - when called calls repository")
    void executeWhenCalledCallsRepository() {
        doNothing().when(contactRepository).deleteById(anyString());

        Assertions.assertDoesNotThrow(() -> command.execute(MockConstants.STRING));

        verify(contactRepository, times(1)).deleteById(anyString());
    }
}
