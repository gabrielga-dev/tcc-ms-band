package br.com.events.band.data.io.pdf;

import br.com.events.band.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PdfDTOMock {

    public static PdfDTO build(){
        return new PdfDTO(MockConstants.STRING, new byte[10]);
    }
}
