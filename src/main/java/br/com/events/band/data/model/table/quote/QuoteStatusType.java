package br.com.events.band.data.model.table.quote;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the quote status type
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@RequiredArgsConstructor
public enum QuoteStatusType {

    NOT_READ("Não lido"),
    READ("Lido"),
    APPROVED("Aprovado"),
    REJECTED("Rejeitado");


    private final String label;
}
