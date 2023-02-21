package br.com.events.band.domain.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the payment methods
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@RequiredArgsConstructor
public enum PaymentMethodType {

    CASH("Dinheiro"),
    CHECK("Cheque"),
    DEBIT_CARD("Cartão de débito"),
    CREDIT_CARD("Cartão de crédito"),
    BANK_TRANSFER("Transferência bancária"),
    PIX("PIX"),
    OTHER("Outro");

    private final String label;
}
