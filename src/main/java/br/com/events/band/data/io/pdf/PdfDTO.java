package br.com.events.band.data.io.pdf;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PdfDTO {

    private final String fileName;
    private final byte[] fileBytes;
}
