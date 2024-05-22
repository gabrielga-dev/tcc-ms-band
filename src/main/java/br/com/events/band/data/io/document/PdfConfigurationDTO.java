package br.com.events.band.data.io.document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PdfConfigurationDTO {

    private final String template;
    private final boolean waterMark = true;
}
