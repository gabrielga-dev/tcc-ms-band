package br.com.events.band.data.io.music.criteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MusicCriteria {

    private final String name;
    private final String author;
    private final String artist;
}
