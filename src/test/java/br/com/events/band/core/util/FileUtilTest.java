package br.com.events.band.core.util;

import br.com.events.band.MockConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for {@link FileUtil}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FileUtilTest {

    @Test
    @DisplayName("output - whe called return ResponseEntity")
    void outputWheCalledReturnResponseEntity(){
        var returned = FileUtil.output(new byte[10], MockConstants.STRING);
        Assertions.assertNotNull(returned);
    }
}
