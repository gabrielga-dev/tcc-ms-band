package br.com.events.band.business.command.contact;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.repository.ContactRepository;
import br.com.events.band.data.model.table.band.contact.ContactTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindContactCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindContactCommandTest {

    @InjectMocks
    private FindContactCommand command;

    @Mock
    private ContactRepository contactRepository;

    @Test
    @DisplayName("byUuid - when called return found contact")
    void byUuidWhenCalledReturnFoundContact() {
        var contact = ContactTableMock.build();
        when(contactRepository.findById(anyString())).thenReturn(Optional.of(contact));

        var returned = command.byUuid(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(contact, returned.get());

        verify(contactRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("byUuidAndBandUuid - when called return found contact")
    void byUuidAndBandUuidWhenCalledReturnFoundContact() {
        var contact = ContactTableMock.build();
        when(contactRepository.findByUuidAndBandUuid(anyString(), anyString())).thenReturn(Optional.of(contact));

        var returned = command.byUuidAndBandUuid(MockConstants.STRING, MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(contact, returned.get());

        verify(contactRepository, times(1)).findByUuidAndBandUuid(anyString(), anyString());
    }
}
