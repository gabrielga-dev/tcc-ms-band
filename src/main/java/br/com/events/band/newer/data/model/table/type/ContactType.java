package br.com.events.band.newer.data.model.table.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the contact type
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@RequiredArgsConstructor
public enum ContactType {

    INSTAGRAM("Instagram"),
    FACEBOOK("Facebook"),
    WHATSAPP("Whatsapp"),
    LINKEDIN("Linkedin"),
    WEBSITE("Website"),
    OTHER("Outro");

    private final String label;
}
