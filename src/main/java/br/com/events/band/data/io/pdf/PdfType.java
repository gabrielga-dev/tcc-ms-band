package br.com.events.band.data.io.pdf;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PdfType {

    PLAYLIST(1L),
    LINEUP(2L);

    private final Long id;
}
