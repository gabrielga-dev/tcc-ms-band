package br.com.events.band.business.command.contact;

import br.com.events.band.adapter.repository.ContactRepository;
import br.com.events.band.data.model.table.band.contact.ContactTable;
import br.com.events.band.data.model.table.band.contact.ContactTableMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SaveContactCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveContactCommandTest {

    @InjectMocks
    private SaveContactCommand command;

    @Mock
    private ContactRepository contactRepository;

    @Test
    @DisplayName("execute - when called return saved contact")
    void executeWhenCalledReturnSavedContact() {
        var contact = ContactTableMock.build();
        when(contactRepository.save(any(ContactTable.class))).thenReturn(contact);

        var toSave = ContactTableMock.build();
        var returned = command.execute(toSave);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(contact, returned);

        verify(contactRepository, times(1)).save(eq(toSave));
    }
}
