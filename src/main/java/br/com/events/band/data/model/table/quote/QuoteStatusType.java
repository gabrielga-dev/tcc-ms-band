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

    NON_ANSWERED("Não Respondido"),
    HIRED("Aprovado"),
    DECLINED("Negado");


    private final String label;
}
