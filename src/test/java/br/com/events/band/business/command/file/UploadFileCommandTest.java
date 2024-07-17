package br.com.events.band.business.command.file;


import br.com.events.band.MockConstants;
import br.com.events.band.adapter.feign.MsFileFeign;
import br.com.events.band.data.io.file.FileDTOMock;
import br.com.events.band.data.io.file.FileType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link UploadFileCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class UploadFileCommandTest {

    @InjectMocks
    private UploadFileCommand command;

    @Mock
    private MsFileFeign msFileFeign;

    @Test
    @DisplayName("execute - when called calls feign client")
    void executeWhenCalledCallsFeignClient() {
        var file = FileDTOMock.build();
        when(msFileFeign.uploadFile(anyString(), anyString(), any(FileType.class), any(MultipartFile.class)))
                .thenReturn(file);
        var returned = command.execute(
                MockConstants.STRING, MockConstants.STRING, FileType.IMAGE, mock(MultipartFile.class)
        );

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(file, returned);

        verify(msFileFeign, times(1))
                .uploadFile(anyString(), anyString(), any(FileType.class), any(MultipartFile.class));
    }
}
